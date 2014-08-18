package com.qycloud.oatos.filecache.domain.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.RecordVoiceDTO;
import com.conlect.oatos.dto.client.ShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;
import com.conlect.oatos.dto.client.TakePictureDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.MessageStatus;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.file.ImageUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.conlect.oatos.utils.CallBack;
import com.qycloud.oatos.filecache.util.ConfigUtil;
import com.qycloud.oatos.filecache.util.FileProxy;

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
	 * 保存截图至个人网盘
	 * @param request
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public String screenshot(HttpServletRequest request, final long userId, final String token) throws Exception {
		String guidName = UUID.randomUUID().toString().toLowerCase() + "." + CommConstants.FILE_FORMAT_JPG;
		String path = DiskUtil.getPersonalDiskDir(userId);
		File dir = new File(DiskUtil.ONLINEDISK_PATH, path);
		if (!dir.mkdir()) {
			dir.mkdirs();
		}
		String filePath = path + guidName;
		// 保存文件至本地
		File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		long size = FileUtil.copyStream(request.getInputStream(), new FileOutputStream(file));

		final NetworkFileDTO fileDTO = new NetworkFileDTO();
		fileDTO.setUserId(userId);
		fileDTO.setGuid(guidName);
		fileDTO.setName("screen" + "." + CommConstants.FILE_FORMAT_JPG);
		fileDTO.setSize(CommonUtil.getFileSizeKb(size));
		fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);

		// 生成图片的缩略图
		String thumbPath = DiskUtil.getThumbPath(fileDTO);
		String thumb = ImageUtil.createThumb(file, thumbPath);

		fileDTO.setThumb(thumb);

		// 保存文件至文件服务器
		FileUploadUtil.upload(file, filePath, token, new CallBack() {
			
			@Override
			public void goBack(String result) {
				// 保存数据库记录
				if (CommConstants.OK_MARK.equals(result)) {
					String postData = PojoMapper.toJson(fileDTO);
					proxy.post(ConfigUtil.getServiceUrl(RESTurl.screenshot), token, postData);
				}
			}
		});

		// 保存文件缩略图至文件服务器
		if (thumb != null) {
			File thumbFile = new File(DiskUtil.ONLINEDISK_PATH, thumb);
			FileUploadUtil.upload(thumbFile, thumb, token);
		}

		String result = "OK$" + filePath;
		return result;
	}

	/**
	 * 拍照并保存
	 * @param request
	 * @param postData
	 * @return
	 * @throws Exception 
	 */
	public String tackPictureAndSave(HttpServletRequest request, String postData, final String token) throws Exception {
		final TakePictureDTO pictureDTO = PojoMapper.fromJsonAsObject(postData, TakePictureDTO.class);

		String d = null;
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(pictureDTO.getType())) {
			d = DiskUtil.getPersonalDiskDir(pictureDTO.getUserId());
		} else if (CommConstants.FILE_TYPE_SHAREDISK.equals(pictureDTO.getType())) {
			d = DiskUtil.getShareDiskDir(pictureDTO.getEnterpriseId());
		} else {
			d = DiskUtil.DISK_TEMP_DIR;
		}
		File dir = new File(DiskUtil.ONLINEDISK_PATH, d);
		if (!dir.mkdir()) {
			dir.mkdirs();
		}

		String guidName = UUID.randomUUID().toString().toLowerCase() + "." + pictureDTO.getImageType();
		pictureDTO.setGuid(guidName);

		String filePath = d + guidName;
		File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);

		// 保存文件至本地
		long size = FileUtil.copyStream(request.getInputStream(), new FileOutputStream(file));
		pictureDTO.setSize(CommonUtil.getFileSizeKb(size));
		// 生成图片的缩略图
		String thumb = ImageUtil.createThumb(pictureDTO.getUserId(), pictureDTO.getGuid(), pictureDTO.getType());
		pictureDTO.setThumb(thumb);
		
		pictureDTO.setName("picture" + "." + pictureDTO.getImageType());

		// 保存文件至文件服务器
		FileUploadUtil.upload(file, filePath, token, new CallBack() {
			
			@Override
			public void goBack(String result) {
				if (CommConstants.OK_MARK.equals(result)) {
					// 保存数据库记录
					String postJson = PojoMapper.toJson(pictureDTO);
					result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.tackPictureAndSave), token, postJson);

					if (result != null && !result.startsWith(CommConstants.ERROR_MARK)) {
						// 发送企业网盘更新消息
						if (CommConstants.FILE_TYPE_SHAREDISK.equals(pictureDTO.getType())) {
							ShareFileNewMessageDTO fileNewDTO = new ShareFileNewMessageDTO(pictureDTO.getUserId(), pictureDTO.getEnterpriseId());
							ShareFileNewDTO fileNew = new ShareFileNewDTO(pictureDTO.getFolderId(), result, pictureDTO.getGuid(), Operation.UploadFile);
							fileNewDTO.getFiles().add(fileNew);
							FileProxy.sendShareFileNewMessage(token, fileNewDTO);
						}
					}
				}
			}
		});

		// 保存文件缩略图至文件服务器
		if (thumb != null) {
			File thumbFile = new File(DiskUtil.ONLINEDISK_PATH, thumb);
			FileUploadUtil.upload(thumbFile, thumb, token);
		}

		String result = "OK$" + filePath;
		return result;
	}

	/**
	 * 拍照
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws  
	 */
	public String tackPicture(HttpServletRequest request) throws IOException {
		String fileName = UUID.randomUUID().toString() + "." + CommConstants.FILE_FORMAT_PNG;
		File dir = new File(DiskUtil.ONLINEDISK_PATH, DiskUtil.DISK_TEMP_DIR);
		if (!dir.mkdir()) {
			dir.mkdirs();
		}

		File file = new File(dir, fileName);
		FileUtil.copyStream(request.getInputStream(), new FileOutputStream(file));

		String fileUrl = DiskUtil.DISK_TEMP_DIR + fileName;

		String result = "OK$" + fileUrl;
		return result;
	}

	/**
	 * 录音上传
	 * @param request
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public String recordVoiceMail(HttpServletRequest request, String postData, final String token) throws Exception {
		final RecordVoiceDTO recordDTO = PojoMapper.fromJsonAsObject(postData, RecordVoiceDTO.class);

		String guidName = UUID.randomUUID().toString() + "." + CommConstants.FILE_FORMAT_MP3;

		File dir = new File(DiskUtil.ONLINEDISK_PATH, DiskUtil.VOICERECORD_DIR);
		if (!dir.mkdir()) {
			dir.mkdirs();
		}

		// 保存文件至本地
		final String filePath = DiskUtil.VOICERECORD_DIR + guidName;
		File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		FileUtil.copyStream(request.getInputStream(), new FileOutputStream(file));
		
		// 保存文件至文件服务器
		FileUploadUtil.upload(file, filePath, token, new CallBack() {
			
			@Override
			public void goBack(String result) {
				if (CommConstants.OK_MARK.equals(result)) {
					// 发送留言消息
					MessageDTO message = new MessageDTO();
					message.setMessageType(MessageType.VoiceMail);
					message.setSender(recordDTO.getUserId());
					message.setFromUser(recordDTO.getUserName());
					message.setReceiver(recordDTO.getReceiverId());
					message.setReceiverName(recordDTO.getReceiverName());
					message.setMessageBody(filePath);
					message.setStatus(MessageStatus.New);
					message.setSendDate(new Date());
					FileProxy.sendMessage(message, token);
				}
			}
		});

		return CommConstants.OK_MARK;
	}

	/**
	 * 文件上传
	 * @param request
	 * @param postData
	 * @return
	 * @throws Exception 
	 */
	public String fileUpload(HttpServletRequest request, String postData, String token) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(5 * 1024 * 1024); // 5M缓冲,文件大于5M则保存到临时目录
		factory.setRepository(new File(DiskUtil.getDiskTempPath())); // 临时目录
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding(CommConstants.CHARSET_UTF_8);
		
		FileUploadDTO bean = PojoMapper.fromJsonAsObject(postData, FileUploadDTO.class);
		
		String result = CommConstants.OK_MARK;
		
		if (RESTurl.getStartPoint.equals(bean.getMethod())) {
			// 文件上传之前，检查网盘空间，同名文件，权限
			String fileName = CommonUtil.ASCII2String(bean.getFileNameASCII());
			bean.setFileName(fileName);
			result = FileUploadUtil.checkFileUpload(bean, token);
		} else if (RESTurl.isUploadOver.equals(bean.getMethod())) {
			// TODO not supported
			result = CommConstants.UPLOAD_SUCCESS;
		} else {
			// 上传文件
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				FileItemStream fis = iterator.next();
				if (!fis.isFormField()) {
					// 获取文件名
					String fileNameASCII = fis.getName();
					String fileName = CommonUtil.ASCII2String(fileNameASCII);
					bean.setFileName(fileName);
					// 生成guid
					createGuidName(bean);
					// 保存文件
					File targetFile = DiskUtil.getTargetFile(bean);
					long size = FileUtil.copyInputStreamToFile(fis.openStream(), targetFile);
					bean.setFileSize(size);
					// 插入记录至数据库
					result = FileUploadUtil.handleUpload(targetFile, bean, token);
				}
			}
		}
		return result;
	}
	
	/**
	 * 生成guidName
	 * @param bean
	 */
	private void createGuidName(FileUploadDTO bean) {
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())
				|| CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())
				|| CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(bean.getType())
				|| bean.getGuidName() == null
				|| "".equals(bean.getGuidName().trim())
				|| "null".equalsIgnoreCase(bean.getGuidName().trim())) {
			bean.setGuidName(FileUtil.createFileGuidName(bean.getFileName()));
		}
	}
}
