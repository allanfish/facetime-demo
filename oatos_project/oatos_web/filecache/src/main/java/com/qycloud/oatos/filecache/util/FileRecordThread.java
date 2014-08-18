package com.qycloud.oatos.filecache.util;

import com.conlect.oatos.dto.autobean.IShareFileRecordDTO;
import com.conlect.oatos.dto.autobean.IViewFileDTO;
import com.conlect.oatos.dto.client.ShareFileRecordDTO;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;

public class FileRecordThread extends Thread {
	
	private IShareFileRecordDTO recordDTO;
	
	private String token;

	public FileRecordThread(IViewFileDTO fileDTO, String token) {
		this(fileDTO.getFileId(), fileDTO.getUserId(), MessageType.fileAccessRecord, token);
	}
	
	public FileRecordThread(long fileId, long userId, String recordType, String token) {
		this.token = token;
		recordDTO = new ShareFileRecordDTO();
		recordDTO.setFileId(fileId);
		recordDTO.setUserId(userId);
		recordDTO.setRecordType(recordType);

		start();
	}

	@Override
	public void run() {
		String postData = PojoMapper.toJson(recordDTO);
		XhrProxy.getInstance().post(ConfigUtil.getServiceUrl(RESTurl.insertFileAccessRecord), token, postData);
	}

}
