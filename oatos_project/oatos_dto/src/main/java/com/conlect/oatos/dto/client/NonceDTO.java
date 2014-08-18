package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.INonceDTO;
import com.conlect.oatos.dto.status.UserAgent;

/**
 * 数据完整性安全检查
 * 
 * @author yang
 * 
 */
public class NonceDTO implements INonceDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 随机串
	 */
	public String nonce;
	/**
	 * 用户验证码
	 */
	public String hashKey;

	/**
	 * 用户使用的设备， 必须<br> {@link UserAgent}
	 */
	private String agent = UserAgent.Web;

	@Override
	public String getNonce() {
		return nonce;
	}

	@Override
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	@Override
	public String getHashKey() {
		return hashKey;
	}

	@Override
	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
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
