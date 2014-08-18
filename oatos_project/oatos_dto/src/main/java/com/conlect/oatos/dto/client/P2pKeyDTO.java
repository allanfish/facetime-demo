package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IP2pKeyDTO;

/**
 * p2p key
 * 
 * @author yang
 * 
 */
public class P2pKeyDTO implements IP2pKeyDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * key
	 */
	private String key;

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}
}
