package com.qycloud.oatos.filecache.jms.support;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.InputStreamEntity;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.file.FileStatusUpdateDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.FileStatus;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.conlect.oatos.utils.SysLogger;
import com.qycloud.oatos.filecache.util.ConfigUtil;

public class FileUploadWorker implements Runnable {

	private static final XhrProxy HTTP = XhrProxy.getInstance();
	private FileUploadDTO uploadDTO;

	public FileUploadWorker(FileUploadDTO uploadDTO) {
		this.uploadDTO = uploadDTO;
	}

	@Override
	public void run() {
		try {
			StringBuilder url = new StringBuilder(ConfigUtil.getValue(MyConst.FileManagerService));
			url.append(RESTurl.fileUpload);
			File cacheFile = DiskUtil.getTargetFile(uploadDTO);

			HttpEntity postData = new InputStreamEntity(new FileInputStream(cacheFile), -1);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put(RESTurl.UserTokenkey, uploadDTO.getToken());
			headers.put(RESTurl.filePath, DiskUtil.getTargetFilePath(uploadDTO));
			String result = HTTP.post(url.toString(), postData, headers);
			if (CommConstants.OK_MARK.equals(result)) {
				// 更新文件状态为FileStatus.UPLOAD_TO_FS
				FileStatusUpdateDTO updateDTO = new FileStatusUpdateDTO();
				updateDTO.setFileId(uploadDTO.getFileId());
				updateDTO.setFileStatus(FileStatus.UPLOAD_TO_FS);
				if (uploadDTO.getType().equals(CommConstants.FILE_TYPE_ONLINEDISK)) {
					result = HTTP.post(ConfigUtil.getServiceUrl(RESTurl.updatePersonalFileStatus), uploadDTO.getToken(),
							PojoMapper.toJson(updateDTO));
				} else {
					result = HTTP.post(ConfigUtil.getServiceUrl(RESTurl.updateShareFileStatus), uploadDTO.getToken(),
							PojoMapper.toJson(updateDTO));
				}
			}
			if (CommConstants.OK_MARK.equals(result)) {
				SysLogger.getServerLogger().info(
						String.format("update file %s to FileManager result: %s", uploadDTO.getFileName(), result));
			} else {
				SysLogger.getServerLogger().error(
						String.format("update file %s to FileManager result: %s", uploadDTO.getFileName(), result));
				//TODO 处理上传文件到FileManager失败的情况
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
