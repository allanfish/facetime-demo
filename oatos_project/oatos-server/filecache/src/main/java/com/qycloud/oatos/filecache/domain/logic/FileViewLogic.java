package com.qycloud.oatos.filecache.domain.logic;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.ShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;
import com.conlect.oatos.dto.client.ViewFileDTO;
import com.conlect.oatos.dto.client.ViewFileResultDTO;
import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.DocConvertPriority;
import com.conlect.oatos.dto.status.DocConvertType;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FilePermission;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.ImageUtil;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.oatos.filecache.util.ConfigUtil;
import com.qycloud.oatos.filecache.util.DocConverter;
import com.qycloud.oatos.filecache.util.FileProxy;
import com.qycloud.oatos.filecache.util.FileRecordThread;
import com.qycloud.oatos.filecache.util.Logs;

/**
 * 文件浏览服务
 * @author yang
 *
 */
@Service
public class FileViewLogic {

	private final static Logger logger = Logs.getLogger();

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * 浏览图片，取图片尺寸及地址
	 * @param postData
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	public String viewFileAsImage(String postData, String token) throws Exception {
		ViewFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, ViewFileDTO.class);
		ViewFileResultDTO resultDTO = new ViewFileResultDTO(fileDTO);
		resultDTO.setCurrentPage(fileDTO.getCurrentPage());
		// check permission
		String check = CommConstants.OK_MARK;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType()) && fileDTO.isCheckPermission()) {
			check = FileProxy.checkPermission(fileDTO.getUserId(), fileDTO.getFolderId(), FilePermission.Read, token);
		}
		if (CommConstants.OK_MARK.equals(check)) {
			if (CommonUtil.isImage(fileDTO.getGuid())) {
				// image file
				String filePath = FileProxy.getFilePath(fileDTO, token);
				File image = new File(DiskUtil.ONLINEDISK_PATH, filePath);
				if (image.exists()) {
					int width = 0;
					int height = 0;
					try {
						BufferedImage imageFile = ImageIO.read(image);
						width = imageFile.getWidth();
						height = imageFile.getHeight();
					}
					catch (Exception ex) {
						width = -1;
						height = -1;
					}
					resultDTO.setWidth(width);
					resultDTO.setHeight(height);
					resultDTO.setUrl(filePath);
					if (fileDTO.getThumb() == null || "".equals(fileDTO.getThumb())) {
						String thumbPath = DiskUtil.getThumbPath(fileDTO);
						String thumb = ImageUtil.createThumb(image, thumbPath);
						resultDTO.setThumb(thumb);
					}
				} else {
					resultDTO.setMessage(ErrorType.errorFileNotFound.name());
				}
			} else {
				resultDTO.setMessage(ErrorType.errorNotSupported.name());
			}
		} else {
			resultDTO.setMessage(check);
		}
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())
				&& CommConstants.OK_MARK.equals(resultDTO.getMessage())) {
			new FileRecordThread(fileDTO, token);
		}
		return PojoMapper.toJson(resultDTO);
	}

	/**
	 * 将文件转成html
	 * @param postData
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	public String viewFileAsHtml(String postData, String token) throws Exception {
		ViewFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, ViewFileDTO.class);
		ViewFileResultDTO resultDTO = new ViewFileResultDTO(fileDTO);
		// check permission
		String check = CommConstants.OK_MARK;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType()) && fileDTO.isCheckPermission()) {
			check = FileProxy.checkPermission(fileDTO.getUserId(), fileDTO.getFolderId(), FilePermission.Read, token);
		}
		if (CommConstants.OK_MARK.equals(check)) {
			String filePath = FileProxy.getFilePath(fileDTO, token);
			
			if (CommonUtil.isHtml(fileDTO.getGuid())) {
				resultDTO.setUrl(filePath);
			} else if (CommonUtil.isViewAsTEXTSupported(fileDTO.getGuid())) {
				String realFilePath = DiskUtil.ONLINEDISK_PATH + filePath;
				DocConverter.checkTextCharset(realFilePath, token);
				resultDTO.setUrl(filePath);
			} else if (CommonUtil.isConvertToHtmlSupported(fileDTO.getGuid())) {
				String htmlPath = DiskUtil.getHtmlFilePath(fileDTO);
				
				DocConvertDTO convertDTO = new DocConvertDTO(fileDTO);
				convertDTO.setConvertType(DocConvertType.DOC_TO_HTML);
				convertDTO.setSourcePath(filePath);
				convertDTO.setTargetPath(htmlPath);
				convertDTO.setPriorty(DocConvertPriority.FILE_VIEW);
				String re = DocConverter.convert(convertDTO, token);
				resultDTO.setMessage(re);
				if (CommConstants.OK_MARK.equals(re)) {
					resultDTO.setUrl(htmlPath);
					if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())
							&& CommConstants.OK_MARK.equals(resultDTO.getMessage())) {
						new FileRecordThread(fileDTO, token);
					}
				}
				
			} else {
				resultDTO.setMessage(ErrorType.errorNotSupported.name());
			}
		} else {
			resultDTO.setMessage(check);
		}
		return PojoMapper.toJson(resultDTO);
	}

	/**
	 * 将文件转成pdf
	 * @param postData
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	public String viewFileAsPdf(String postData, String token) throws Exception {
		ViewFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, ViewFileDTO.class);
		ViewFileResultDTO resultDTO = new ViewFileResultDTO(fileDTO);
		// check permission
		String check = CommConstants.OK_MARK;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType()) && fileDTO.isCheckPermission()) {
			check = FileProxy.checkPermission(fileDTO.getUserId(), fileDTO.getFolderId(), FilePermission.Read, token);
		}
		if (CommConstants.OK_MARK.equals(check)) {
			String filePath = FileProxy.getFilePath(fileDTO, token);
			if (CommonUtil.isPdf(fileDTO.getGuid())) {
				// pdf
				resultDTO.setUrl(filePath);
			} else if (CommonUtil.isConvertToPdfSupported(fileDTO.getGuid())) {
				String pdfPath = DiskUtil.getPdfFilePath(fileDTO);
				
				DocConvertDTO convertDTO = new DocConvertDTO(fileDTO);
				convertDTO.setConvertType(DocConvertType.DOC_TO_PDF);
				convertDTO.setSourcePath(filePath);
				convertDTO.setTargetPath(pdfPath);
				convertDTO.setPriorty(DocConvertPriority.FILE_VIEW);
				String re = DocConverter.convert(convertDTO, token);
				resultDTO.setMessage(re);
				if (CommConstants.OK_MARK.equals(re)) {
					resultDTO.setUrl(pdfPath);
					if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())
							&& CommConstants.OK_MARK.equals(resultDTO.getMessage())) {
						new FileRecordThread(fileDTO, token);
					}
				}
			} else {
				resultDTO.setMessage(ErrorType.errorNotSupported.name());
			}
		} else {
			resultDTO.setMessage(check);
		}
		return PojoMapper.toJson(resultDTO);
	}

	/**
	 * 将文档转成swf
	 * @param postData
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	public String viewFileAsSwf(String postData, String token) throws Exception {
		ViewFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, ViewFileDTO.class);
		ViewFileResultDTO resultDTO = new ViewFileResultDTO(fileDTO);
		resultDTO.setCurrentPage(fileDTO.getCurrentPage());
		
		String filePath = FileProxy.getFilePath(fileDTO, token);
		
		String swfPath = DiskUtil.getSwfFilePath(fileDTO);

		DocConvertDTO convertDTO = new DocConvertDTO(fileDTO);
		convertDTO.setConvertType(DocConvertType.DOC_TO_SWF);
		convertDTO.setSourcePath(filePath);
		convertDTO.setTargetPath(swfPath);
		convertDTO.setPriorty(DocConvertPriority.FILE_VIEW);
		
		boolean s = true;
		if (CommonUtil.isPdf(fileDTO.getGuid())) {
			// pdf
			convertDTO.setPdfPath(filePath);
		} else if (CommonUtil.isConvertToPdfSupported(fileDTO.getGuid())) {
			String pdfPath = DiskUtil.getPdfFilePath(fileDTO);
			convertDTO.setPdfPath(pdfPath);
		} else {
			s = false;
			
		}
		if (s) {
			String re = DocConverter.convert(convertDTO, token);
			resultDTO.setMessage(re);
			if (CommConstants.OK_MARK.equals(re)) {
				resultDTO.setPageCount(convertDTO.getPageCount());
				String url = DiskUtil.getSplitSwfPath(swfPath, fileDTO.getCurrentPage());
				resultDTO.setUrl(url);
				if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())
						&& CommConstants.OK_MARK.equals(resultDTO.getMessage())
						&& fileDTO.getCurrentPage() == 1) {
					new FileRecordThread(fileDTO, token);
				}
			}
		} else {
			resultDTO.setMessage(ErrorType.errorNotSupported.name());
		}
		return PojoMapper.toJson(resultDTO);
	}

	/**
	 * 编辑文件，将文件转成html
	 * @param postData
	 * @param token
	 * @return
	 * @throws Exception 
	 */
	public String editFileAsHtml(String postData, String token) throws Exception {
		ViewFileDTO fileDTO = PojoMapper.fromJsonAsObject(postData, ViewFileDTO.class);
		String result = null;
		ViewFileResultDTO resultDTO = new ViewFileResultDTO(fileDTO);
		// check permission and status
		boolean check = true;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())) {
			String r = proxy.post(ConfigUtil.getServiceUrl(ServerInnerUrl.checkEditShareFile), token,
					PojoMapper.toJson(fileDTO));
			if (!CommConstants.OK_MARK.equals(r)) {
				check = false;
				resultDTO.setMessage(r);
			}
		}
		
		if (check) {
			String filePath = FileProxy.getFilePath(fileDTO, token);
			String htmlPath = null;
			try {
				if (CommonUtil.isHtml(fileDTO.getGuid())) {
					htmlPath = filePath;
				} else if (CommonUtil.isEditAsHtmlSupported(fileDTO.getGuid())) {
					htmlPath = DiskUtil.getHtmlFilePath(fileDTO);
					DocConvertDTO convertDTO = new DocConvertDTO(fileDTO);
					convertDTO.setConvertType(DocConvertType.DOC_TO_HTML);
					convertDTO.setSourcePath(filePath);
					convertDTO.setTargetPath(htmlPath);
					convertDTO.setPriorty(DocConvertPriority.EDIT_HTML);
					String re = DocConverter.convert(convertDTO, token);
					resultDTO.setMessage(re);
				} else {
					resultDTO.setMessage(ErrorType.errorNotSupported.name());
				}
				if (CommConstants.OK_MARK.equals(resultDTO.getMessage())) {
					FileInputStream is = new FileInputStream(DiskUtil.ONLINEDISK_PATH + htmlPath);
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					StringBuilder buffer = new StringBuilder();
					char[] chars = new char[1024];
					while (reader.read(chars) != -1) {
						buffer.append(new String(chars)); // 将读到的内容添加到 buffer 中
						chars = new char[1024];
					}
					
					is.close();
					reader.close();
					
					resultDTO.setContent(buffer.toString());
					
					if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())) {
						// send update version message
						ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(fileDTO.getUserId(),
								fileDTO.getFromId());
						ShareFileNewDTO newFileDTO = new ShareFileNewDTO(fileDTO.getFolderId(),
								fileDTO.getName(), fileDTO.getGuid(), Operation.Lock);
						newFileDTO.setFileId(fileDTO.getFileId());
						fileNewDTO.getFiles().add(newFileDTO);
						FileProxy.sendShareFileNewMessage(token, fileNewDTO);
					}
				}
			}
			catch (Exception ex) {
				logger.error("", ex);
				resultDTO.setMessage(ErrorType.error500.name());
			}
		}
		result = PojoMapper.toJson(resultDTO);
		return result;
	}
}
