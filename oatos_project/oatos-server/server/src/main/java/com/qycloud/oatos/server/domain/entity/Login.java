package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

/**
 * 用户登录信息实体
 * 
 * @author yang
 * 
 */
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private long loginId;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 企业名称
	 */
	private String entName;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 登录密码
	 */
	private String password;

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
