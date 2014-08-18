package com.qycloud.oatos.server.security;

import com.conlect.oatos.dto.client.NonceDTO;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.UserAgent;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 数据安全完整性验证
 * @author yang
 *
 */
public class SecurityUtil {

	/**
	 * 密码解密和用户验证 (用户的密码先取 SHA256值，然后加密传输，系统保存用户密码的SHA256做对比）
	 * 
	 * @param name
	 * @param password
	 * @param nonceDTO
	 * @return
	 */
	public static String checkHashKey(String name, String password,
			NonceDTO nonceDTO) {
		String pwd = null;
		if (UserAgent.IOS.equalsIgnoreCase(nonceDTO.getAgent())) {
			// ios设备暂无加密
			pwd = Security.CreatePassword(Security.SHA256(password));
		} else {
			// 加密后的字符，十六进制的串
			String mPassword = password;
			String nonce = nonceDTO.getNonce();

			Security sc = new Security();
			// 解密
			String hPassword = sc.codeDecode(nonce, mPassword);
			// 转码
			String mdPassword = Security.hexStringToByteString(hPassword);
			// 计算校验码, 密码完整性
			String hashKey = Security.SHA256(name + mdPassword + nonce);
			
			if (hashKey.equals(nonceDTO.getHashKey())) {
				pwd = Security.CreatePassword(mdPassword);
			} else {
				throw new LogicException(ErrorType.errorCheckHashkey);
			}
		}
		return pwd;
	}
}
