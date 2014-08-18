package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.ICustomerClientToken;

/**
 * 临时客户token，继承自{@link ClientToken}, 并拥有ip地址字段
 * 
 * @author jinkerjiang
 * 
 */
public class CustomerClientToken extends ClientToken implements
		ICustomerClientToken {
	private static final long serialVersionUID = -665078069638534024L;

	private String ip;

	public CustomerClientToken() {
		super();
	}

	public CustomerClientToken(String userToken) {
		super(userToken);
	}

	/**
	 * @return the ip
	 */
	@Override
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	@Override
	public void setIp(String ip) {
		this.ip = ip;
	}

}
