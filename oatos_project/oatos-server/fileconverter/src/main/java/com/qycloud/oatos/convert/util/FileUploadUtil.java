package com.qycloud.oatos.convert.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;

import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.XhrProxy;

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
	 * @param callBack
	 */
	public static void upload(final File file, final String path, final String token) {
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
					proxy.post(url.toString(), postData, headers);

				} catch (Exception ex) {
					Logs.getLogger().error(path, ex);
				}

			}};
			thread.start();
	}

}
