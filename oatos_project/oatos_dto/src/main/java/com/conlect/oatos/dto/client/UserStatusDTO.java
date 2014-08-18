package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IUserStatusDTO;
import com.conlect.oatos.dto.status.UserAgent;
import com.conlect.oatos.dto.status.UserStatus;

/**
 * 用户在线状态
 * 
 * @author PeterGuo
 */
public class UserStatusDTO implements IUserStatusDTO {
	private static final long serialVersionUID = 2463597485189575872L;
	/**
	 * 用户ID
	 */
	public long userId;
	/**
	 * 用户在线状态<br> {@link UserStatus}
	 */
	public String userStatus;

	/**
	 * 用户使用的设备
	 */
	private String agent = UserAgent.Web;

	public UserStatusDTO() {
	}

	public UserStatusDTO(long userId, String userStatus) {
		this.userId = userId;
		this.userStatus = userStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.mailing7.dto.client.IUserStatus#getUserId()
	 */
	@Override
	public long getUserId() {
		return userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.mailing7.dto.client.IUserStatus#setUserId(long)
	 */
	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.mailing7.dto.client.IUserStatus#getUserStatus()
	 */
	@Override
	public String getUserStatus() {
		return userStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.mailing7.dto.client.IUserStatus#setUserStatus(java.lang.String
	 * )
	 */
	@Override
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String getAgent() {
		return agent;
	}

	@Override
	public void setAgent(String agent) {
		this.agent = agent;
	}

}
