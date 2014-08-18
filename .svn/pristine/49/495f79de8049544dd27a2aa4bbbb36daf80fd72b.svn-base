package com.qycloud.oatos.filecache.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.FileUploadResultDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.SendFileDTO;
import com.conlect.oatos.dto.client.SendFileResultDTO;
import com.conlect.oatos.dto.client.ShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.file.ImageUtil;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.oatos.filecache.util.ConferenceDocConverter;
import com.qycloud.oatos.filecache.util.ConfigUtil;
import com.qycloud.oatos.filecache.util.DocConverter;
import com.qycloud.oatos.filecache.util.FileProxy;
import com.qycloud.oatos.filecache.util.Logs;

public class FileUploadUtil {

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();
	
	/**
	 * 上传文件至文件服务器
	 * @param file
	 * @param path
	 * @param fileType
	 * @param token
	 */
	public static void upload(final String path, final String fileType, final String token) {
		upload(path, null, fileType, token);
	}

	/**
	 * 上传文件至文件服务器
	 * @param file
	 * @param path
	 * @param fileId
	 * @param fileType
	 * @param token
	 */
	public static void upload(final String path, final Long fileId, final String fileType, final String token) {
		Thread thread = new Thread(){

			@Override
			public void run() {
				// 保存文件至文件服务器
				try {
					StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
					url.append(RESTurl.fileUpload);
					
					File file = new File(DiskUtil.ONLINEDISK_PATH, path);

					HttpEntity postData = new InputStreamEntity(new FileInputStream(file), -1);
					
					Map<String, String> headers = new HashMap<String, String>();
					headers.put(RESTurl.UserTokenkey, token);
					headers.put(RESTurl.filePath, path);
					if (fileId != null) {
						headers.put(RESTurl.fileId, String.valueOf(fileId));
					}
					headers.put(RESTurl.type, fileType);

					XhrProxy.getInstance().post(url.toString(), postData, headers);
				} catch (Exception ex) {
					Logs.getLogger().error(path, ex);
				}

			}};
			thread.start();
	}

	/**
	 * 文件开始上传前，检查网盘空间，权限，同名文件
	 * @param bean
	 * @param token
	 * @return
	 */
	public static String checkFileUpload(FileUploadDTO bean, String token) {
		String result = CommConstants.OK_MARK;
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
			// 个人网盘文件
			NetworkFileDTO fileDTO = bean.toPrivateFileDTO();
			String restUrl = RESTurl.checkPersonalFileUpload;
			String postData = PojoMapper.toJson(fileDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(restUrl), token, postData);
		} else if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			// 企业网盘文件
			ShareFileUpdateDTO shareFileDTO = bean.toEnterpriseFileDTO();
			String restUrl = RESTurl.checkShareFileUpload;
			String postData = PojoMapper.toJson(shareFileDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(restUrl), token, postData);
		} else if (CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(bean.getType())) {
			// 会议文档
			ConferenceDocDTO docDTO = new ConferenceDocDTO();
			docDTO.setConferenceId(bean.getFolderId());
			docDTO.setName(bean.getFileName());
			docDTO.setGuid(bean.getGuidName());
			docDTO.setSize(CommonUtil.getFileSizeKb(bean.getFileSize()));

			String restUrl = RESTurl.checkConferenceDocUpload;
			String postData = PojoMapper.toJson(docDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(restUrl), token, postData);
		}
		return result;
	}

	/**
	 * 文件上传完成后，更新数据库记录
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static String handleUpload(FileUploadDTO bean, String token) throws Exception {
		String result = CommConstants.OK_MARK;
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
			// 个人网盘文件
			result = handlePersonalFileUpload(bean, token);
		} else if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			// 企业网盘文件
			result = handleShareFileUpload(bean, token);
		} else if (CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(bean.getType())) {
			// 会议文档
			result = handleConferenceDocUpload(bean, token);
		} else if (MessageType.InstantFile.equals(bean.getType())
				|| MessageType.OfflineFile.equals(bean.getType())
				|| MessageType.ShareInstantFile.equals(bean.getType())) {
			// 聊天发送文件
			sendFile(bean, token);
		} else if (CommConstants.FILE_TYPE_ENTLOGO.equals(bean.getType())) {
			// 企业logo
			result = handleEntLogoUpload(bean, token);
		} else if (CommConstants.FILE_TYPE_SHAREFILE_THUMB.equals(bean.getType())) {
			// 企业网盘文件封面
			result = handleShareFileThumbUpload(bean, token);
		} else if (CommConstants.FILE_TYPE_PRIVATEFILE_THUMB.equals(bean.getType())) {
			// 个人网盘文件封面
			result = handlePersonalFileThumbUpload(bean, token);
		} else if (CommConstants.FILE_TYPE_ICON.equals(bean.getType())) {
			// 用户头像上传
			result = handleUserIconUpload(bean, token);
		}
		return result;
	}
	
	/**
	 * 添加个人网盘文件
	 * @param bean
	 * @param token
	 * @return
	 * @throws IOException 
	 */
	private static String handlePersonalFileUpload(FileUploadDTO bean, String token) throws IOException {
		NetworkFileDTO fileDTO = bean.toPrivateFileDTO();
		// 生成文件缩略图
		String thumb = null;
		File tempFile = DiskUtil.getTempFile(bean.getGuidName());
		if (CommonUtil.isImage(fileDTO.getName())) {
			String thumbPath = DiskUtil.getThumbPath(fileDTO);
			thumb = ImageUtil.createThumb(tempFile, thumbPath);
			fileDTO.setThumb(thumb);
		}

		// 保存数据库记录
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addPersonalFile), token, PojoMapper.toJson(fileDTO));
		
		// 发送文件上传完成消息给界面
		Long fileId = sendFileUploadDoneMessage(bean, result, token);
		
		// 插数据库成功
		if (fileId != null) {
			// 移动临时文件至目标目录
			File targetFile = DiskUtil.getTargetFile(bean);
			tempFile.renameTo(targetFile);
			String filePath = DiskUtil.getTargetFilePath(bean);
			// 保存文件至文件服务器
			upload(filePath, fileId, CommConstants.FILE_TYPE_ONLINEDISK, token);
			// 转换
			DocConverter.toSwf(fileDTO, token);

			result = CommConstants.OK_MARK;
		}

		// 保存文件缩略图至文件服务器
		if (thumb != null) {
			upload(thumb, CommConstants.FILE_TYPE_PRIVATEFILE_THUMB, token);
		}
		return result;
	}
	
	/**
	 * 添加企业网盘文件
	 * @param bean
	 * @param token
	 * @return
	 * @throws IOException 
	 */
	private static String handleShareFileUpload(FileUploadDTO bean, String token) throws IOException {
		ShareFileUpdateDTO fileDTO = bean.toEnterpriseFileDTO();
		// 生成文件缩略图
		String thumb = null;
		File tempFile = DiskUtil.getTempFile(bean.getGuidName());
		if (CommonUtil.isImage(fileDTO.getName())) {
			String thumbPath = DiskUtil.getThumbPath(fileDTO);
			thumb = ImageUtil.createThumb(tempFile, thumbPath);
		}
		
		// 保存数据库记录
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addShareFile), token, PojoMapper.toJson(fileDTO));
		// 发送文件上传完成消息给界面
		Long fileId = sendFileUploadDoneMessage(bean, result, token);
		
		// 插数据库成功
		if (fileId != null) {
			// 移动临时文件至目标目录
			File targetFile = DiskUtil.getTargetFile(bean);
			tempFile.renameTo(targetFile);
			String filePath = DiskUtil.getTargetFilePath(bean);
			// 保存文件至文件服务器
			upload(filePath, fileId, CommConstants.FILE_TYPE_SHAREDISK, token);
			// 转换
			DocConverter.toSwf(fileDTO, token);

			// 发送企业网盘更新消息
			ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(bean.getUserId(), bean.getEntId());
			ShareFileNewDTO fileNew = new ShareFileNewDTO(bean.getFolderId(), fileDTO.getName(), fileDTO.getGuid(), ShareFileNewMessageDTO.Operation.UploadFile);
			fileNewDTO.getFiles().add(fileNew);
			// send update version message
			FileProxy.sendShareFileNewMessage(token, fileNewDTO);

			result = CommConstants.OK_MARK;
		}

		// 保存文件缩略图至文件服务器
		if (thumb != null) {
			upload(thumb, CommConstants.FILE_TYPE_SHAREFILE_THUMB, token);
		}
		return result;
	}
	
	/**
	 * 添加会议文档
	 * @param bean
	 * @param token
	 * @return
	 */
	private static String handleConferenceDocUpload(final FileUploadDTO bean, final String token) {
		// 保存数据库记录
		ConferenceDocDTO fileDTO = new ConferenceDocDTO();
		fileDTO.setConferenceId(bean.getFolderId());
		fileDTO.setName(bean.getFileName());
		fileDTO.setGuid(bean.getGuidName());
		fileDTO.setSize(CommonUtil.getFileSizeKb(bean.getFileSize()));
		fileDTO.setType(CommConstants.FILE_TYPE_CONFERENCE_DOC);
		fileDTO.setEnterpriseId(bean.getEntId());
		// add file in db
		String postData = PojoMapper.toJson(fileDTO);
		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addConferenceDoc), token, postData);

		try {
			ConferenceDocDTO docDTO = PojoMapper.fromJsonAsObject(result, ConferenceDocDTO.class);

			// 移动临时文件至目标目录
			File tempFile = DiskUtil.getTempFile(bean.getGuidName());
			File targetFile = DiskUtil.getTargetFile(bean);
			tempFile.renameTo(targetFile);
			// 保存文件至文件服务器
			String filePath = DiskUtil.getTargetFilePath(bean);
			upload(filePath, docDTO.getFileId(), CommConstants.FILE_TYPE_CONFERENCE_DOC, token);
			
			// 开始转换会议文档
			ConferenceDocConverter converter = new ConferenceDocConverter(docDTO, token);
			converter.start();

			result = CommConstants.OK_MARK;
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * 聊天发送文件
	 * @param tempFile
	 * @param bean
	 * @param token
	 * @return
	 */
	private static String sendFile(FileUploadDTO bean, String token) {
		NetworkFileDTO fileDTO = new NetworkFileDTO();
		fileDTO.setName(bean.getFileName());
		fileDTO.setGuid(bean.getGuidName());
		fileDTO.setSize(CommonUtil.getFileSizeKb(bean.getFileSize()));
		SendFileDTO sendFileDTO = new SendFileDTO(bean.getUserId(), bean.getReceiverId(), bean.getType());
		sendFileDTO.setFileDTO(fileDTO);
		String postData = PojoMapper.toJson(sendFileDTO);
		String result = proxy.post(ConfigUtil.getServiceUrl(ServerInnerUrl.sendFile), token, postData);
		try {
			MessageDTO messageDTO = PojoMapper.fromJsonAsObject(result, MessageDTO.class);
			SendFileResultDTO resultDTO = PojoMapper.fromJsonAsObject(messageDTO.getMessageBody(), SendFileResultDTO.class);

			File tempFile = DiskUtil.getTempFile(bean.getGuidName());

			// 保存发送者的文件
			INetworkFileDTO sendFile = resultDTO.getFileDTO();
			if (CommConstants.FILE_TYPE_ONLINEDISK.equals(sendFile.getType())) {
				// 复制临时文件至目标文件夹
				String filePath = DiskUtil.getFilePath(sendFile);
				File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);
				FileUtil.copyFile(tempFile, file);
				// 保存文件至文件服务器
				upload(filePath, sendFile.getFileId(), sendFile.getType(), token);
				// 转换
				DocConverter.toSwf(sendFile, token);
			}

			// 保存接收者的文件
			INetworkFileDTO receiveFile = resultDTO.getReceiveFileDTO();
			if (CommConstants.FILE_TYPE_ONLINEDISK.equals(receiveFile.getType())) {
				// 复制临时文件至目标文件夹
				String filePath = DiskUtil.getFilePath(receiveFile);
				File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);
				FileUtil.copyFile(tempFile, file);
				// 保存文件至文件服务器
				upload(filePath, sendFile.getFileId(), receiveFile.getType(), token);
				// 转换
				DocConverter.toSwf(receiveFile, token);
			}

			// 保存临时文件至文件服务器
			if (CommConstants.FILE_TYPE_TEMP.equals(sendFile.getType())
					|| CommConstants.FILE_TYPE_TEMP.equals(receiveFile.getType())) {
				String filePath = DiskUtil.getTempFilePath(bean.getGuidName());
				upload(filePath, CommConstants.FILE_TYPE_TEMP, token);
			}

			// 发送消息
			FileProxy.sendMessage(messageDTO, token);
			result = CommConstants.OK_MARK;
		} catch (Exception ex) {
			result = ErrorType.error500.name();
		}
		return result;
	}
	
	/**
	 * 企业logo上传
	 * @param file
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	private static String handleEntLogoUpload(final FileUploadDTO bean, String token) throws Exception {
		String tempFilePath = DiskUtil.getTempFilePath(bean.getGuidName());
		String filePath = ImageUtil.createEnterpriseLogo(tempFilePath, bean.getEntId());
		// 上传logo至文件服务器
		upload(filePath, CommConstants.FILE_TYPE_ENTLOGO, token);

		String result = CommConstants.OK_MARK + "$" + filePath;
		return result;
	}

	/**
	 * 个人网盘文件封面
	 * @param bean
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private static String handlePersonalFileThumbUpload(final FileUploadDTO bean, String token) throws Exception {
		return handleFileThumbUpload(bean.getUserId(), bean.getGuidName(), bean.getType(), token);
	}

	/**
	 * 企业网盘文件封面
	 * @param bean
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private static String handleShareFileThumbUpload(final FileUploadDTO bean, String token) throws Exception {
		return handleFileThumbUpload(bean.getEntId(), bean.getGuidName(), bean.getType(), token);
	}
	
	/**
	 * 创建缩略图
	 * @param fromId
	 * @param guidName
	 * @param type
	 * @param token
	 * @return
	 * @throws Exception
	 */
	private static String handleFileThumbUpload(long fromId, String guidName, String type, String token) throws Exception {
		String result = "";
		String tempFilePath = DiskUtil.getTempFilePath(guidName);
		String thumbPath = DiskUtil.getThumbPath(fromId, guidName, type);
		// 创建缩略图
		String thumb = ImageUtil.createThumb(tempFilePath, thumbPath);
		File thumbFile = new File(DiskUtil.ONLINEDISK_PATH, thumb);
		if (thumbFile.exists()) {
			// 上传thumb至文件服务器
			upload(thumb, type, token);
			result = CommConstants.OK_MARK + "$" + thumb;
		} else {
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * 用户头像上传
	 * @param file
	 * @param bean
	 * @param token
	 * @return
	 * @throws IOException 
	 */
	private static String handleUserIconUpload(FileUploadDTO bean, String token) throws IOException {
		String dir = DiskUtil.getUserIconDir(bean.getUserId());
		String filePath = dir + bean.getGuidName();
		
		UserIconDTO iconDTO = new UserIconDTO();
		iconDTO.setUserId(bean.getUserId());
		iconDTO.setPath(filePath);
		String postJson = PojoMapper.toJson(iconDTO);

		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.changeUserIcon), token, postJson);
		if (CommConstants.OK_MARK.equals(result)) {
			// 移动临时文件至目标目录
			File tempFile = DiskUtil.getTempFile(bean.getGuidName());
			File targetFile = new File(DiskUtil.ONLINEDISK_PATH, filePath);
			// 缩放头像至标准大小
			ImageUtil.resizeImage(tempFile, targetFile, ImageUtil.IMAGE_SIZE_96, ImageUtil.IMAGE_SIZE_96);

			// 上传icon至文件服务器
			upload(filePath, CommConstants.FILE_TYPE_ICON, token);
		}
		
		return result;
	}

	/**
	 * 发送文件上传完成消息
	 * @param uploadBean
	 * @param result
	 * @return
	 */
	private static Long sendFileUploadDoneMessage(FileUploadDTO uploadBean, String result, String token) {
		Long fileId = null;
		try {
			String r = null;
			try {
				fileId = Long.parseLong(result);
				r = CommConstants.OK_MARK;
			} catch (Exception ex) {
				r = result;
			}
			
			FileUploadResultDTO resultDTO = new FileUploadResultDTO(fileId, uploadBean.getFileName(), uploadBean.getType(), r);

			MessageDTO msg = new MessageDTO();
			msg.setMessageType(MessageType.FileUploadDone);
			msg.setReceiver(uploadBean.getUserId());
			msg.setMessageBody(PojoMapper.toJson(resultDTO));
			msg.setSender(0L);

			FileProxy.sendMessage(msg, token);
			
		} catch (Exception ex) {
			Logs.getLogger().error(uploadBean.toString(), ex);
		}
		return fileId;
	}

}
