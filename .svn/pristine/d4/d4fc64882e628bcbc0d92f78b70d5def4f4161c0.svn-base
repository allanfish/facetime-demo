package com.qycloud.oatos.filecache.util;

import java.io.File;

import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ViewFileResultDTO;
import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.DocConvertPriority;
import com.conlect.oatos.dto.status.DocConvertType;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DocConvertUrl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;

/**
 * open office converter
 * 
 * @author yang
 * 
 */
public class DocConverter {

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * 文件转换
	 * @param docDTO
	 * @param token
	 * @return
	 */
	public static String convert(DocConvertDTO docDTO, String token) {
		File targetFile = new File(docDTO.getDiskRootPath(), docDTO.getTargetPath());
		if (DocConvertType.DOC_TO_SWF.equals(docDTO.getConvertType())) {
			String swfPath = DiskUtil.getSplitSwfPath(docDTO.getTargetPath(), docDTO.getStartPage());
			targetFile = new File(docDTO.getDiskRootPath(), swfPath);
			if (docDTO.getPageCount() == null) {
				// 从数据库中取文件页数
				docDTO.setPageCount(getPageCount(docDTO, token));
			}
		}
		String re = "";
		boolean doConvert = true;
		if (targetFile.exists()) {
			// 目标文件已经存在
			re = CommConstants.OK_MARK;
			doConvert = false;
			if (DocConvertType.DOC_TO_SWF.equals(docDTO.getConvertType())) {
				doConvert = true;
				if (docDTO.getPageCount() != null && docDTO.getPageCount() > 0) {
					// 需要取文件页数
					doConvert = false;
				}
			}
		}
		if (doConvert) {
			File sourceFile = new File(docDTO.getDiskRootPath(), docDTO.getSourcePath());
			if (sourceFile.exists()) {
				String postData = PojoMapper.toJson(docDTO);
				StringBuilder postURL = new StringBuilder(
						ConfigUtil.getValue(MyConst.DocConvertService));
				postURL.append(DocConvertUrl.ConvertService);
				postURL.append("/");
				postURL.append(DocConvertUrl.docConvert);
				re = proxy.post(postURL.toString(), token, postData);
				if (DocConvertType.DOC_TO_SWF.equals(docDTO.getConvertType())
						&& CommConstants.OK_MARK.equals(re)) {
					// 从数据库中取文件页数
					docDTO.setPageCount(getPageCount(docDTO, token));
				}
			} else {
				// 源文件不存在
				re = ErrorType.errorFileNotFound.name();
			}
		}
		return re;
	}

	/**
	 * 检查文本文件编码
	 * 
	 * @param filePath
	 */
	public static void checkTextCharset(String filePath, String token) {
		StringBuilder postURL = new StringBuilder(
				ConfigUtil.getValue(MyConst.DocConvertService));
		postURL.append(DocConvertUrl.ConvertService);
		postURL.append("/");
		postURL.append(DocConvertUrl.checkTextCharset);
		proxy.post(postURL.toString(), token, filePath);
	}

	/**
	 * 从数据库中取文件页数
	 * @param docDTO
	 * @param token
	 * @return
	 */
	private static Integer getPageCount(DocConvertDTO docDTO, String token) {
		Integer pageCount = null;
		if (docDTO.getPageCount() != null && docDTO.getPageCount() > 0) {
			pageCount = docDTO.getPageCount();
		} else {
			String postData = String.valueOf(docDTO.getFileId());
			if (CommConstants.FILE_TYPE_SHAREDISK.equals(docDTO.getFileType())) {
				String r = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getShareFileById), token, postData);
				ShareFileDTO fileDTO = PojoMapper.fromJsonAsObject(r, ShareFileDTO.class);
				pageCount = fileDTO.getPageCount();
			} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(docDTO.getFileType())) {
				String r = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getFileById), token, postData);
				NetworkFileDTO fileDTO = PojoMapper.fromJsonAsObject(r, NetworkFileDTO.class);
				pageCount = fileDTO.getPageCount();
			} else if (CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(docDTO.getFileType())) {
				String r = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getConferenceDocById), token, postData);
				ConferenceDocDTO fileDTO = PojoMapper.fromJsonAsObject(r, ConferenceDocDTO.class);
				pageCount = fileDTO.getPageCount();
			}
			docDTO.setPageCount(pageCount);
		}
		return pageCount;
	}

	/**
	 * 转换视频会议文档
	 * @param docDTO
	 * @param token
	 * @return
	 */
	public static ViewFileResultDTO convertDocToSwf(ConferenceDocDTO docDTO, String token) {
		ViewFileResultDTO resultDTO = new ViewFileResultDTO();
		
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFileId(docDTO.getFileId());
		if (docDTO.getUserId() != null) {
			fileDTO.setUserId(docDTO.getUserId());
		}
		if (docDTO.getEnterpriseId() != null) {
			fileDTO.setEnterpriseId(docDTO.getEnterpriseId());
		}
		fileDTO.setGuid(docDTO.getGuid());
		fileDTO.setType(docDTO.getType());
		fileDTO.setFolderId(docDTO.getConferenceId());
		if (docDTO.getDiskFileId() != null) {
			fileDTO.setFileId(docDTO.getDiskFileId());
		}

		String pdfPath = null;
		String swfPath = null;
		
		if (docDTO.getDiskFileId() != null) {
			fileDTO.setFileId(docDTO.getDiskFileId());
			Long fromId = null;
			if (CommConstants.FILE_TYPE_SHAREDISK.equals(docDTO.getType())) {
				fromId = docDTO.getEnterpriseId();
			} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(docDTO.getType())) {
				fromId = docDTO.getUserId();
			}
			if (fromId != null) {
				pdfPath = DiskUtil.getPdfFilePath(fileDTO);
				
				swfPath = DiskUtil.getSwfFilePath(fileDTO);
			}
		} else {
			pdfPath = DiskUtil.getConferencePdfPath(docDTO.getEnterpriseId(), docDTO.getConferenceId(), docDTO.getGuid());
			swfPath = DiskUtil.getConferenceSwfPath(docDTO.getEnterpriseId(), docDTO.getConferenceId(), docDTO.getGuid());
		}
		
		DocConvertDTO convertDTO = new DocConvertDTO(docDTO);
		convertDTO.setConvertType(DocConvertType.DOC_TO_SWF);
		convertDTO.setPriorty(DocConvertPriority.CONFERENCE_DOC);

		convertDTO.setTargetPath(swfPath);
		boolean s = true;
		if (CommonUtil.isPdf(docDTO.getGuid())) {
			// pdf
		} else if (CommonUtil.isConvertToPdfSupported(docDTO.getGuid())) {
			convertDTO.setPdfPath(pdfPath);
		} else {
			s = false;
		}
		if (s) {
			try {
				String filePath = FileProxy.getFilePath(fileDTO, token);
				convertDTO.setSourcePath(filePath);
				
				String re = convert(convertDTO, token);
				resultDTO.setMessage(re);
				if (CommConstants.OK_MARK.equals(re)) {
					resultDTO.setPageCount(convertDTO.getPageCount());
					String url = DiskUtil.getSplitSwfPath(swfPath, 1);
					resultDTO.setUrl(url);
				}
			} catch (Exception ex) {
				Logs.getLogger().error(ex);
				resultDTO.setMessage(ErrorType.error500.name());
			}
		} else {
			resultDTO.setMessage(ErrorType.errorNotSupported.name());
		}

		return resultDTO;
	}

}
