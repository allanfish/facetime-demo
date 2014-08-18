package com.qycloud.oatos.server.test;

import com.qycloud.oatos.server.security.Security;

/**
 * 加密工具
 * @author yang
 *
 */
public class EncryptUtil {

	/**
	 * 通过明文的账号和密码得到加密后的密码
	 * @param account 登录帐号,修改密码时为id
	 * @param password 明文密码
	 * @return
	 */
	public static EncryptModel encrypt(String account, String password) {
		EncryptModel encrypt = new EncryptModel();
		String nonce = Security.randomCharString();
		String passwordBySha256 = Security.SHA256(password);
		String hash = Security.SHA256(account + passwordBySha256 + nonce);
		String h = Security.byteStringToHexString(passwordBySha256);
		String ep = new Security().codeDecode(nonce, h);

		encrypt.setNonce(nonce);
		encrypt.setHashKey(hash);
		encrypt.setPassword(ep);
		return encrypt;
	}
}
