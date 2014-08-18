package com.qycloud.oatos.server.test;

import com.conlect.oatos.dto.client.NonceDTO;

@SuppressWarnings("serial")
public class EncryptModel extends NonceDTO {

	/**
	 * 加密后的密码
	 */
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
