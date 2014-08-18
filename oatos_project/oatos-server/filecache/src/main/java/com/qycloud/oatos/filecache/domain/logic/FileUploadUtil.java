package com.qycloud.oatos.filecache.domain.logic;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;

import com.conlect.oatos.dto.client.ConferenceDocDTO;
import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.FileUploadResultDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.SendFileDTO;
import com.conlect.oatos.dto.client.ShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.client.UserIconDTO;
import com.conlect.oatos.dto.client.UserInfoDTO;
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
import com.conlect.oatos.utils.CallBack;
import com.qycloud.oatos.filecache.util.ConferenceDocConverter;
import com.qycloud.oatos.filecache.util.ConfigUtil;
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
	 * @param token
	 */
	public static void upload(final File file, final String path, final String token) {
		upload(file, path, token, null);
	}

	/**
	 * 上传文件至文件服务器
	 * @param file
	 * @param path
	 * @param token
	 * @param callBack
	 */
	public static void upload(final File file, final String path, final String token, final CallBack callBack) {
		Thread thread = new Thread(){

			@Override
			public void run() {
				// 保存文件至文件服务器
				try {
					StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
					url.append(RESTurl.fileUpload);

					HttpEntity postData = new InputStreamEntity(new FileInputStream(file), -1);
					Map<String, String> headers = new HashMap<String, String>();
					headers.put(RESTurl.UserTokenkey, token);
					headers.put(RESTurl.filePath, path);
					String postResult = proxy.post(url.toString(), postData, headers);
					if (callBack != null) {
						callBack.goBack(postResult);
					}
				} catch (Exception ex) {
					Logs.getLogger().error(path, ex);
					if (callBack != null) {
						callBack.goBack(ErrorType.error500.name());
					}
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
	 * @param targetFile
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static String handleUpload(File targetFile, FileUploadDTO bean, String token) throws Exception {
		String result = CommConstants.OK_MARK;
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
			// 个人网盘文件
			result = handlePersonalFileUpload(targetFile, bean, token);
		} else if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			// 企业网盘文件
			result = handleShareFileUpload(targetFile, bean, token);
		} else if (CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(bean.getType())) {
			// 会议文档
			result = handleConferenceDocUpload(targetFile, bean, token);
		} else if (MessageType.InstantFile.equals(bean.getType())
				|| MessageType.OfflineFile.equals(bean.getType())
				|| MessageType.ShareInstantFile.equals(bean.getType())) {
			// 聊天发送文件
			sendFile(targetFile, bean, token);
		} else if (CommConstants.FILE_TYPE_ENTLOGO.equals(bean.getType())) {
			// 企业logo
			result = handleEntLogoUpload(targetFile, bean, token);
		} else if (CommConstants.FILE_TYPE_SHAREFILE_THUMB.equals(bean.getType())) {
			// 企业网盘文件封面
			result = handleShareFileThumbUpload(bean, token);
		} else if (CommConstants.FILE_TYPE_PRIVATEFILE_THUMB.equals(bean.getType())) {
			// 个人网盘文件封面
			result = handlePersonalFileThumbUpload(bean, token);
		} else if (CommConstants.FILE_TYPE_ICON.equals(bean.getType())) {
			// 用户头像上传
			result = handleUserIconUpload(targetFile, bean, token);
		}
		return result;
	}
	
	/**
	 * 添加个人网盘文件
	 * @param bean
	 * @param token
	 * @return
	 */
	private static String handlePersonalFileUpload(File file, final FileUploadDTO bean, final String token) {
		final NetworkFileDTO fileDTO = bean.toPrivateFileDTO();
		// 生成文件缩略图
		String thumb = null;
		if (CommonUtil.isImage(fileDTO.getName())) {
			String thumbPath = DiskUtil.getThumbPath(fileDTO);
			thumb = ImageUtil.createThumb(file, thumbPath);
			fileDTO.setThumb(thumb);
		}
		String filePath = DiskUtil.getTargetFilePath(bean);
		// 保存文件至文件服务器
		upload(file, filePath, token, new CallBack() {
			
			@Override
			public void goBack(String result) {
				if (CommConstants.OK_MARK.equals(result)) {
					// 保存数据库记录
					String r = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addPersonalFile), token, PojoMapper.toJson(fileDTO));
					// 发送文件上传完成消息给界面
					sendFileUploadDoneMessage(bean, r, token);
				}
			}
		});

		// 保存文件缩略图至文件服务器
		if (thumb != null) {
			File thumbFile = new File(DiskUtil.ONLINEDISK_PATH, thumb);
			FileUploadUtil.upload(thumbFile, thumb, token);
		}
		return CommConstants.OK_MARK;
	}
	
	/**
	 * 添加企业网盘文件
	 * @param bean
	 * @param token
	 * @return
	 */
	private static String handleShareFileUpload(File file, final FileUploadDTO bean, final String token) {
		final ShareFileUpdateDTO fileDTO = bean.toEnterpriseFileDTO();
		// 生成文件缩略图
		String thumb = null;
		if (CommonUtil.isImage(fileDTO.getName())) {
			String thumbPath = DiskUtil.getThumbPath(fileDTO);
			thumb = ImageUtil.createThumb(file, thumbPath);
		}
		String filePath = DiskUtil.getTargetFilePath(bean);
		// 保存文件至文件服务器
		upload(file, filePath, token, new CallBack() {
			
			@Override
			public void goBack(String result) {
				if (CommConstants.OK_MARK.equals(result)) {
					// 保存数据库记录
					String r = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addShareFile), token, PojoMapper.toJson(fileDTO));
					// 发送文件上传完成消息给界面
					String re = sendFileUploadDoneMessage(bean, r, token);
					if (CommConstants.OK_MARK.equals(re)) {
						// 发送企业网盘更新消息
						ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(bean.getUserId(), bean.getEntId());
						ShareFileNewDTO fileNew = new ShareFileNewDTO(bean.getFolderId(), fileDTO.getName(), fileDTO.getGuid(), ShareFileNewMessageDTO.Operation.UploadFile);
						fileNewDTO.getFiles().add(fileNew);
						// send update version message
						FileProxy.sendShareFileNewMessage(token, fileNewDTO);
					}
				}
			}
		});

		// 保存文件缩略图至文件服务器
		if (thumb != null) {
			File thumbFile = new File(DiskUtil.ONLINEDISK_PATH, thumb);
			FileUploadUtil.upload(thumbFile, thumb, token);
		}
		return CommConstants.OK_MARK;
	}
	
	/**
	 * 添加会议文档
	 * @param bean
	 * @param token
	 * @return
	 */
	private static String handleConferenceDocUpload(File file, final FileUploadDTO bean, final String token) {
		String filePath = DiskUtil.getTargetFilePath(bean);
		// 保存文件至文件服务器
		upload(file, filePath, token, new CallBack() {
			
			@Override
			public void goBack(String result) {
				if (CommConstants.OK_MARK.equals(result)) {
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
					String re = proxy.post(ConfigUtil.getServiceUrl(RESTurl.addConferenceDoc), token, postData);
					try {
						// 开始转换会议文档
						ConferenceDocDTO docDTO = PojoMapper.fromJsonAsObject(re, ConferenceDocDTO.class);
						ConferenceDocConverter converter = new ConferenceDocConverter(docDTO, token);
						converter.start();
						re = CommConstants.OK_MARK;
					} catch (Exception ex) {
						Logs.getLogger().error(re, ex);
					}
				}
			}
		});

		return CommConstants.OK_MARK;
	}

	/**
	 * 聊天发送文件
	 * @param tempFile
	 * @param uploadBean
	 * @param token
	 * @return
	 */
	private static String sendFile(File tempFile, FileUploadDTO uploadBean, String token) {
		String result = null;
		Long sendId = uploadBean.getUserId();
		long receiverId = uploadBean.getReceiverId();
		try {
			// 取发送者信息
			UserInfoDTO sender = null;
			try {
				String str = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getUserProfile), token, String.valueOf(sendId));
				if (str != null && !str.startsWith(CommConstants.ERROR_MARK)) {
					sender = PojoMapper.fromJsonAsObject(str, UserInfoDTO.class);
				}
			} catch (Exception ex) {
			}
			NetworkFileDTO fileDTO = new NetworkFileDTO();
			fileDTO.setName(uploadBean.getFileName());
			fileDTO.setGuid(uploadBean.getGuidName());
			fileDTO.setSize(CommonUtil.getFileSizeKb(uploadBean.getFileSize()));
			if (sender != null) {
				// 保存文件至发送者个人网盘
				fileDTO.setUserId(sendId);
				fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
				String filePath = DiskUtil.getFilePath(fileDTO);
				// 保存文件至本地
				File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);
				FileUtil.copyFile(tempFile, file);
				// 保存文件至文件服务器
				FileUploadUtil.upload(file, filePath, token);
			} else {
				fileDTO.setType(CommConstants.FILE_TYPE_TEMP);
			}

			if (MessageType.InstantFile.equals(uploadBean.getType())) {
				// 取接收者信息
				UserInfoDTO receiver = null;
				try {
					String str = proxy.post(ConfigUtil.getServiceUrl(RESTurl.getUserProfile), token, String.valueOf(receiverId));
					if (str != null && !str.startsWith(CommConstants.ERROR_MARK)) {
						receiver = PojoMapper.fromJsonAsObject(str, UserInfoDTO.class);
					}
				} catch (Exception ex) {
				}
				if (receiver != null) {
					// 保存文件至接收者个人网盘
					fileDTO.setUserId(receiverId);
					fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
					String filePath = DiskUtil.getFilePath(fileDTO);
					// 保存文件至本地
					File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);
					FileUtil.copyFile(tempFile, file);
					// 保存文件至文件服务器
					FileUploadUtil.upload(file, filePath, token);
				}
			}

			if (CommonUtil.isImage(uploadBean.getFileName())) {
				String thumbPath = DiskUtil.getThumbPath(fileDTO);
				String thumb = ImageUtil.createThumb(tempFile, thumbPath);
				fileDTO.setThumb(thumb);
			}

			SendFileDTO sendFileDTO = new SendFileDTO(sendId, receiverId, uploadBean.getType());
			sendFileDTO.setFileDTO(fileDTO);
			String postData = PojoMapper.toJson(sendFileDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(ServerInnerUrl.sendFile), token, postData);
			if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
				MessageDTO messageDTO = PojoMapper.fromJsonAsObject(result, MessageDTO.class);
				FileProxy.sendMessage(messageDTO, token);
				result = CommConstants.OK_MARK;
			}
		}
		catch (Exception ex) {
			Logs.getLogger().error(uploadBean.toString(), ex);
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
	private static String handleEntLogoUpload(File file, final FileUploadDTO bean, String token) throws Exception {
		String filePath = ImageUtil.createEnterpriseLogo(DiskUtil.DISK_TEMP_DIR + bean.getGuidName(), bean.getEntId());
		File logo = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		// 上传logo至文件服务器
		upload(logo, filePath, token);
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
		String filePath = DiskUtil.getFilePath(fromId, guidName, CommConstants.FILE_TYPE_TEMP);
		String thumbPath = DiskUtil.getThumbPath(fromId, guidName, type);
		// 创建缩略图
		String thumb = ImageUtil.createThumb(filePath, thumbPath);
		File thumbFile = new File(DiskUtil.ONLINEDISK_PATH, thumb);
		if (thumbFile.exists()) {
			// 上传thumb至文件服务器
			upload(thumbFile, filePath, token);
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
	 */
	private static String handleUserIconUpload(File file, FileUploadDTO bean, String token) {
		String dir = DiskUtil.getUserIconDir(bean.getUserId());
		String filePath = dir + bean.getGuidName();

		File targetFile = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		file.renameTo(targetFile);

		UserIconDTO iconDTO = new UserIconDTO();
		iconDTO.setUserId(bean.getUserId());
		iconDTO.setPath(filePath);
		String postJson = PojoMapper.toJson(iconDTO);

		String result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.changeUserIcon), token, postJson);

		if (CommConstants.OK_MARK.equals(result)) {
			// 上传icon至文件服务器
			upload(targetFile, filePath, token);
		}

		return result;
	}

	/**
	 * 发送文件上传完成消息
	 * @param uploadBean
	 * @param result
	 * @return
	 */
	private static String sendFileUploadDoneMessage(FileUploadDTO uploadBean, String result, String token) {
		try {
			Long fileId = null;
			try {
				fileId = Long.parseLong(result);
				result = CommConstants.OK_MARK;
			} catch (Exception ex) {
			}
			
			FileUploadResultDTO resultDTO = new FileUploadResultDTO(fileId, uploadBean.getFileName(), uploadBean.getType(), result);

			MessageDTO msg = new MessageDTO();
			msg.setMessageType(MessageType.FileUploadDone);
			msg.setReceiver(uploadBean.getUserId());
			msg.setMessageBody(PojoMapper.toJson(resultDTO));
			msg.setSender(0L);

			FileProxy.sendMessage(msg, token);
			
		} catch (Exception ex) {
			Logs.getLogger().error(uploadBean.toString(), ex);
		}
		return result;
	}

}
