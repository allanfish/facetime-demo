package com.qycloud.oatos.convert.domain.logic;

import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.oatos.convert.PDFUtil;
import com.qycloud.oatos.convert.Pdf2SwfThread;
import com.qycloud.oatos.convert.SwfTools;
import com.qycloud.oatos.convert.util.ConfigUtil;

/**
 * swf转换任务
 * @author yang
 *
 */
public class SwfConvertTask extends ConvertTask {

	public SwfConvertTask(DocConvertDTO docDTO) {
		super(docDTO);
	}

	@Override
	protected void doConvert(DocConvertDTO docDTO) throws Exception {
		int start = docDTO.getStartPage();
		if (start <= 0) {
			start = 1;
		}
		// 开始页数
		start = ((start - 1) / CommConstants.PDF_SPLIT_PAGES) * CommConstants.PDF_SPLIT_PAGES + 1;
		// 结束页数
		int end = start + CommConstants.PDF_SPLIT_PAGES - 1;

		String pdfPath = docDTO.getDiskRootPath() + docDTO.getPdfPath();
		Integer pageCount = docDTO.getPageCount();
		if (pageCount == null || pageCount < 1) {
			// 获取pdf总页数
			pageCount = PDFUtil.getPageCount(pdfPath);
			// 更新总页数至数据库
			docDTO.setPageCount(pageCount);
			updatePageCount(docDTO);

			if (start > pageCount) {
				return ;
			}
		}
		// swf文件地址
		String swfPath = docDTO.getDiskRootPath() + DiskUtil.getSplitSwfPath(docDTO.getTargetPath(), start);
		// 转换pdf为swf
		SwfTools.pdfToSwf(pdfPath, swfPath, start, end);
		
		if (pageCount > 0 && start + CommConstants.PDF_SPLIT_PAGES <= pageCount) {
			// 开启一个线程，转换所有页数
			docDTO.setStartPage(start);
			Pdf2SwfThread thread = new Pdf2SwfThread(docDTO);
			thread.start();
		}
	}

	/**
	 * 更新文件总页数到数据库
	 * @param doc
	 */
	private void updatePageCount(DocConvertDTO doc) {
		if (doc.getFileId() != null) {
			if (CommConstants.FILE_TYPE_SHAREDISK.equals(doc.getFileType())
					|| CommConstants.FILE_TYPE_ONLINEDISK.equals(doc.getFileType())) {
				// 企业网盘或个人网盘文件
				FileDTO fileDTO = new FileDTO();
				fileDTO.setFileId(doc.getFileId());
				fileDTO.setPageCount(doc.getPageCount());
				fileDTO.setType(doc.getFileType());
				String postData = PojoMapper.toJson(fileDTO);
				XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(ServerInnerUrl.updateFilePageCount), doc.getToken(), postData);
			} else if (CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(doc.getFileType())) {
				// 视频会议文档
				ConferenceDocDTO docDTO = new ConferenceDocDTO();
				docDTO.setFileId(doc.getFileId());
				docDTO.setPageCount(doc.getPageCount());
				String postData = PojoMapper.toJson(docDTO);
				XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(ServerInnerUrl.updateConferenceDocPageCount), doc.getToken(), postData);
			}
		}
	}

}
