package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IFileThumbDTO;

/**
 * 文件缩略图dto
 * 
 * @author yang
 * 
 */
public class FileThumbDTO implements IFileThumbDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 文件guid
	 */
	private String guidName;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 缩略图地址
	 */
	private String url;

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getGuidName() {
		return guidName;
	}

	@Override
	public void setGuidName(String guidName) {
		this.guidName = guidName;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

}
