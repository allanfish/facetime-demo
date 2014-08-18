/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IPasswordChangeDTO;

/**
 * 用户密码修改DTO
 * 
 * @author PeterGuo
 */
public class PasswordChangeDTO extends NonceDTO implements IPasswordChangeDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID或企业ID
	 */
	public long id;
	/**
	 * 用户原来的密码（密文）
	 */
	public String oldPasswrod;
	/**
	 * 用户新设定的密码（密文）
	 */
	public String newPassword;

	public PasswordChangeDTO() {
	}

	public PasswordChangeDTO(long id, String oldPasswrod, String newPassword,
			String nonce, String hashKey) {
		this.id = id;
		this.oldPasswrod = oldPasswrod;
		this.newPassword = newPassword;
		this.nonce = nonce;
		this.hashKey = hashKey;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getOldPasswrod() {
		return oldPasswrod;
	}

	@Override
	public void setOldPasswrod(String oldPasswrod) {
		this.oldPasswrod = oldPasswrod;
	}

	@Override
	public String getNewPassword() {
		return newPassword;
	}

	@Override
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
