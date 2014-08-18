package com.conlect.oatos.dto.autobean;

/**
 * 临时客户token
 * 
 * @author jinkerjiang
 * 
 */
public interface ICustomerClientToken extends IClientToken {

	/**
	 * @return the ip
	 */
	String getIp();

	/**
	 * @param ip
	 *            the ip to set
	 */
	void setIp(String ip);

}