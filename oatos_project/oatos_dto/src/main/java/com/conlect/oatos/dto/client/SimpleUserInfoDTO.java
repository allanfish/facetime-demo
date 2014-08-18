package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.ISimpleUserInfoDTO;

/**
 * 用户基本信息
 * 
 * @author yang
 * 
 */
public class SimpleUserInfoDTO implements ISimpleUserInfoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 用户名
	 */
	private String userName;

	public SimpleUserInfoDTO() {
	}

	public SimpleUserInfoDTO(long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
