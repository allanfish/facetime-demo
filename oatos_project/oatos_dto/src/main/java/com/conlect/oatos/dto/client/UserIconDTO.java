package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IUserIconDTO;

/**
 * 用户图像dto
 * 
 * @author yang
 * 
 */
public class UserIconDTO implements IUserIconDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 地址
	 */
	private String path;
	/**
	 * 地址
	 * 
	 * @deprecated
	 */
	@Deprecated
	private String url;
	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}
}
