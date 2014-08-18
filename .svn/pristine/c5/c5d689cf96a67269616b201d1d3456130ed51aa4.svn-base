package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.ILoginDTO;

/**
 * 用户登录信息
 * 
 * @author PeterGuo
 */

public class LoginDTO extends NonceDTO implements ILoginDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户帐号，用户名
	 */
	public String account;
	/**
	 * @deprecated
	 */
	@Deprecated
	public int keepUserInfo;
	/**
	 * 用户登录密码，密文
	 */
	public String password;

	/**
	 * 客户端标识
	 */
	public String clientId;

	/**
	 * 用户登录终端IP地址
	 */
	private String ip;

	public LoginDTO() {
	}

	public LoginDTO(String account, String nonce, String password) {
		this.account = account;
		this.nonce = nonce;
		this.password = password;
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
	public int getKeepUserInfo() {
		return keepUserInfo;
	}

	@Override
	public void setKeepUserInfo(int keepUserInfo) {
		this.keepUserInfo = keepUserInfo;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String hashPassword) {
		this.password = hashPassword;
	}

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String getIp() {
		return ip;
	}

	@Override
	public void setIp(String ip) {
		this.ip = ip;
	}

}
