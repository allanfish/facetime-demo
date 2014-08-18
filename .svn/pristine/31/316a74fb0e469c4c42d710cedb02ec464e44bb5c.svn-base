package com.qycloud.oatos.filemanager.domain.logic;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.ShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.file.ImageUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.oatos.filemanager.util.ConfigUtil;
import com.qycloud.oatos.filemanager.util.FileProxy;
import com.qycloud.oatos.filemanager.util.Logs;

/**
 * 文件上传服务逻辑
 * @author yang
 *
 */
@Service
public class FileUploadLogic {

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * 文件上传
	 * @param request
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	public String fileUpload(HttpServletRequest request, String filePath) throws Exception {
		String fileDir = filePath.substring(0, filePath.lastIndexOf(DiskUtil.FILE_SEPARATOR));
		File dir = new File(DiskUtil.ONLINEDISK_PATH, fileDir);
		if (!dir.mkdir()) {
			dir.mkdirs();
		}

		// 保存文件
		File targetFile = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		FileUtil.copyInputStreamToFile(request.getInputStream(), targetFile);

		return CommConstants.OK_MARK;
	}

	/**
	 * 网盘文件同步上传
	 * @param request
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public String syncFileUpload(HttpServletRequest request, String postData, String token) throws Exception {
		FileUploadDTO bean = PojoMapper.fromJsonAsObject(postData, FileUploadDTO.class);
		String fileName = CommonUtil.ASCII2String(bean.getFileNameASCII());
		bean.setFileName(fileName);
		String result = CommConstants.OK_MARK;
		// 保存文件
		File targetFile = DiskUtil.getTargetFile(bean);
		FileUtil.copyInputStreamToFile(request.getInputStream(), targetFile);
		// 修改数据库中记录
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			result = syncShareFile(bean, targetFile, token);
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
			result = syncPersonalFile(bean, targetFile, token);
		}

		return result;
	}

	/**
	 * 个人网盘文件同步
	 * @param bean
	 * @param targetFile
	 * @param token
	 * @return
	 */
	private String syncPersonalFile(FileUploadDTO bean, File targetFile, String token) {
		NetworkFileDTO fileDTO = bean.toPrivateFileDTO();
		// 创建文件缩略图
		if (CommonUtil.isImage(fileDTO.getName()) && bean.getFileId() == null) {
			String thumbPath = DiskUtil.getThumbPath(fileDTO.getUserId(), fileDTO.getGuid(), CommConstants.FILE_TYPE_ONLINEDISK);
			String thumb = ImageUtil.createThumb(targetFile, thumbPath);
			fileDTO.setThumb(thumb);
		}
		String postData = PojoMapper.toJson(fileDTO);
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.syncPersonalFile), token, postData);
		if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
			try {
				NetworkFileDTO resultDto = PojoMapper.fromJsonAsObject(result, NetworkFileDTO.class);
				long fileId = resultDto.getFileId();
				if (bean.getFileId() != null) {
					bean.setGuidName(bean.getOldGuid());
					File oldFile = DiskUtil.getTargetFile(bean);
					if (oldFile.exists()) {
						// 移动原文件至历史目录
						String historyFilePath = DiskUtil.getHistoryFilePath(bean.getUserId(), fileId, bean.getOldGuid(), CommConstants.FILE_TYPE_ONLINEDISK);
						File historyFile = new File(DiskUtil.ONLINEDISK_PATH, historyFilePath);
						oldFile.renameTo(historyFile);
					}
				}

			} catch (Exception ex) {
				result = ErrorType.error500.name();
				Logs.getLogger().error(bean.toString(), ex);
			}
		}
		return result;
	}
	
	/**
	 * 企业网盘文件同步
	 * @param bean
	 * @param targetFile
	 * @param token
	 * @return
	 */
	private String syncShareFile(FileUploadDTO bean, File targetFile, String token) {
		ShareFileUpdateDTO fileDTO = bean.toEnterpriseFileDTO();
		// 创建文件缩略图
		if (CommonUtil.isImage(fileDTO.getName()) && bean.getFileId() == null) {
			String thumbPath = DiskUtil.getThumbPath(fileDTO.getEnterpriseId(), fileDTO.getGuid(), CommConstants.FILE_TYPE_SHAREDISK);
			String thumb = ImageUtil.createThumb(targetFile, thumbPath);
			fileDTO.setThumb(thumb);
		}

		String postData = PojoMapper.toJson(fileDTO);
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.syncEnterpriseFile), token, postData);
		if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
			try {
				ShareFileUpdateDTO resultDto = PojoMapper.fromJsonAsObject(result, ShareFileUpdateDTO.class);
				long fileId = resultDto.getFileId();
				ShareFileNewMessageDTO.Operation operation = ShareFileNewMessageDTO.Operation.UploadFile;
				if (bean.getFileId() != null) {
					operation = ShareFileNewMessageDTO.Operation.EditFile;

					bean.setGuidName(bean.getOldGuid());
					File oldFile = DiskUtil.getTargetFile(bean);
					if (oldFile.exists()) {
						// 移动原文件至历史目录
						String historyFilePath = DiskUtil.getHistoryFilePath(bean.getEntId(), fileId, bean.getOldGuid(), CommConstants.FILE_TYPE_SHAREDISK);
						File historyFile = new File(DiskUtil.ONLINEDISK_PATH, historyFilePath);
						oldFile.renameTo(historyFile);
					}
				}

				// 发送企业网盘更新消息
				ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(bean.getUserId(), fileDTO.getEnterpriseId());
				ShareFileNewDTO fDTO = new ShareFileNewDTO(fileId, bean.getFolderId(), bean.getFileName(), bean.getGuidName(), operation);
				fileNewDTO.getFiles().add(fDTO);
				FileProxy.sendShareFileNewMessage(token, fileNewDTO);
			} catch (Exception ex) {
				result = ErrorType.error500.name();
				Logs.getLogger().error(bean.toString(), ex);
			}
		}
		return result;
	}

}
