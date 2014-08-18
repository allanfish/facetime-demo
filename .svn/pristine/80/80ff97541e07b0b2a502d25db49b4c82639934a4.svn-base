package com.qycloud.oatos.server.domain.entity;

import com.conlect.oatos.dto.client.UserDeviceTokenDTO;

/**
 * 用户ios设备标志
 * @author yang
 *
 */
public class UserDeviceToken {

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * ios设备标识，消息推送使用
	 */
	private String deviceToken;

	public UserDeviceToken() {
	}

	public UserDeviceTokenDTO toUserDeviceTokenDTO() {
		UserDeviceTokenDTO dto = new UserDeviceTokenDTO();
		dto.setUserId(userId);
		dto.setDeviceToken(deviceToken);
		return dto;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
}
