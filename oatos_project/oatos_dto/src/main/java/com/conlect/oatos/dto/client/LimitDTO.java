package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.ILimitDTO;

/**
 * 上传下载限制 DTO
 * 
 * @author yang
 * 
 */
public class LimitDTO implements ILimitDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 增强版最大上传速度 50 k bytes
	 */
	private long uploadSpeed = 50 * 1024L;

	/**
	 * 增强版最大下载速度 50 k bytes
	 */
	private long downloadSpeed = 100 * 1024L;

	/**
	 * 网页最大上传文件大小 100 m bytes
	 */
	private long uploadSize = 100 * 1024 * 1024L;

	@Override
	public long getUploadSpeed() {
		return uploadSpeed;
	}

	@Override
	public void setUploadSpeed(long uploadSpeed) {
		this.uploadSpeed = uploadSpeed;
	}

	@Override
	public long getDownloadSpeed() {
		return downloadSpeed;
	}

	@Override
	public void setDownloadSpeed(long downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}

	@Override
	public long getUploadSize() {
		return uploadSize;
	}

	@Override
	public void setUploadSize(long uploadSize) {
		this.uploadSize = uploadSize;
	}

}
