package com.qycloud.oatos.filecache.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import com.conlect.oatos.dto.client.CheckPermissionDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.FilePermission;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.file.ServerInnerUrl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;

public class FileProxy {

	/**
	 * 远程服务请求代理
	 */
	private static XhrProxy proxy = XhrProxy.getInstance();

	/**
	 * 获取文件
	 * @param fileDTO
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static String getFilePath(FileDTO fileDTO, String token) throws Exception {
		String filePath = null;
		//获得文件在网盘中的路径
		String path = DiskUtil.getFilePath(fileDTO);
		//获得文件在filecache文件服务器中的路径
		File f = new File(DiskUtil.ONLINEDISK_PATH, path);
		
		//先在fileCache文件服务器中查找
		if (f.exists() && f.length() > 0) {
			filePath = path;
		} else if (fileDTO.getFileId() != null) {
			path = DiskUtil.getHistoryFilePath(fileDTO);
			f = new File(DiskUtil.ONLINEDISK_PATH, path);
			if (f.exists() && f.length() > 0) {
				filePath = path;
			}
		}
		
		
		//若果fileCache中没有，则在fileManager中查找文件
		if (filePath == null) {
			//远程去取文件在fileManager中的路径
			StringBuilder postURL = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
			postURL.append(ServerInnerUrl.getFilePath);
			
			String json = PojoMapper.toJson(new FileDTO(fileDTO));
			path = proxy.post(postURL.toString(), token, json);
			
			//远程去取文件在fileManager中的文件
			StringBuilder postURL1 = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
			postURL1.append(ServerInnerUrl.getFile);

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			HttpEntity postEntity = new StringEntity(json, CommConstants.CHARSET_UTF_8);
			HttpResponse response = proxy.postForResponse(postURL1.toString(), postEntity, headers);
			
			//在fileCache中拷贝一份文件
			f = new File(DiskUtil.ONLINEDISK_PATH, path);
			FileUtil.copyInputStreamToFile(response.getEntity().getContent(), f);
			filePath = path;
		}
		return filePath;
	}

	/**
	 * 根据文件路径获取文件
	 * @param filePath
	 * @param token
	 * @return
	 * @throws IOException
	 */
	public static File getFile(String filePath, String token) throws IOException {
		File f = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		if (!(f.exists()  && f.length() > 0)) {
			String fileDir = filePath.substring(0, filePath.lastIndexOf(DiskUtil.FILE_SEPARATOR));
			File dir = new File(DiskUtil.ONLINEDISK_PATH, fileDir);
			if (!dir.mkdir()) {
				dir.mkdirs();
			}
			StringBuilder urlPath = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
			urlPath.append(RESTurl.getFileStream);

			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, token);
			HttpEntity postData = new StringEntity(filePath, CommConstants.CHARSET_UTF_8);
			HttpEntity entity = proxy.postForEntity(urlPath.toString(), postData, headers);
			FileUtil.copyInputStreamToFile(entity.getContent(), f);
		}
		return f;
	}
	
	/**
	 * 删除文件服务器中文件
	 * @param files
	 * @param token
	 */
	public static void delete(final String files, final String token) {
		if (files != null && files.length() > 0) {
			Thread thread = new Thread(){

				@Override
				public void run() {
					try {
						StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
						url.append(ServerInnerUrl.deleteFiles);

						proxy.post(url.toString(), token, files);
					} catch (Exception ex) {
						Logs.getLogger().error(files, ex);
					}

				}};
				thread.start();
		}
	}

	/**
	 * 检查企业网盘操作权限
	 * @param userId
	 * @param folderId
	 * @param operation
	 * @param token
	 * @return
	 */
	public static String checkPermission(long userId, long folderId, FilePermission operation, String token) {
		String result = "";
		try {
			CheckPermissionDTO checkDTO = new CheckPermissionDTO(userId, folderId, operation);
			String postData = PojoMapper.toJson(checkDTO);
			result = proxy.post(ConfigUtil.getServiceUrl(RESTurl.checkPermission), token, postData);
		}
		catch (Exception ex) {
			result = ErrorType.error500.name();
		}
		return result;
	}

	/**
	 * 发送企业网盘更新消息
	 * @param token
	 * @param fileNewDTO
	 */
	public static void sendShareFileNewMessage(String token, ShareFileNewMessageDTO fileNewDTO) {
		try {
			// send message
			MessageDTO message = new MessageDTO();
			message.setMessageType(MessageType.ShareFileNew);
			message.setMessageBody(PojoMapper.toJson(fileNewDTO));
			message.setSender(fileNewDTO.getUserId());
			message.setReceiver(fileNewDTO.getEnterpriseId());
			message.setSendDate(new Date());
			sendMessage(message, token);
		}
		catch (Exception ex) {
			Logs.getLogger().error(token, ex);
		}
	}

	/**
	 * 发送消息
	 * @param message
	 * @param token
	 * @return
	 */
	public static String sendMessage(MessageDTO message, String token) {
		String postData = PojoMapper.toJson(message);
		
		String url = ConfigUtil.getValue(MyConst.SendMessage);
		proxy.post(url, token, postData);
		return CommConstants.OK_MARK;
	}
}
