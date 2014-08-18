/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IRegisterDTO;

/**
 * 注册
 * 
 * @author PeterGuo
 */
public class RegisterDTO extends NonceDTO implements IRegisterDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名，登录帐号
	 */
	private String account;
	/**
	 * 用户别名
	 * 
	 * @deprecated
	 */
	@Deprecated
	private String nickname;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 密码（密文）
	 */
	private String password;

	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	public RegisterDTO() {
	}

	@Override
	public String getAccount() {
		return account;
	}

	@Override
	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String getNickname() {
		return nickname;
	}

	@Override
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
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
