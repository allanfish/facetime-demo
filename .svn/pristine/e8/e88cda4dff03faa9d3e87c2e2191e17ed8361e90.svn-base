package com.qycloud.web.service.support;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;

import com.conlect.oatos.dto.autobean.IFileDTO;
import com.conlect.oatos.dto.client.CheckPermissionDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.FilePermission;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.utils.ConfigUtil;

public class FileHelper {

	private static final XhrProxy HTTP = XhrProxy.getInstance();

	private static final FileHelper instance = new FileHelper();

	public static final FileHelper get() {
		return instance;
	}

	/**
	 * 上传文件到FileManager
	 * 
	 * @param token
	 * @param path
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(String token, String path, File file) throws Exception {
		StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
		url.append(RESTurl.fileUpload);

		HttpEntity postData = new InputStreamEntity(new FileInputStream(file), -1);
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(RESTurl.UserTokenkey, token);
		headers.put(RESTurl.filePath, path);
		return HTTP.post(url.toString(), postData, headers);
	}

	/**
	 * 检查企业网盘操作权限
	 * @param userId
	 * @param folderId
	 * @param operation
	 * @param token
	 * @return
	 */
	public boolean checkPermission(long userId, long folderId, FilePermission operation, String token) {
		CheckPermissionDTO checkDTO = new CheckPermissionDTO(userId, folderId, operation);
		String postData = PojoMapper.toJson(checkDTO);
		String result = HTTP.post(ConfigUtil.getServiceUrl(RESTurl.checkPermission), token, postData);
		if (CommConstants.OK_MARK.equals(result))
			return true;
		return false;
	}

}
