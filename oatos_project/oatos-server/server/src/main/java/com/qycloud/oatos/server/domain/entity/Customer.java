package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

/**
 * 客户
 * @author yang
 *
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户ID
	 */
	private long customerId;
	/**
	 * 客户登录IP
	 */
	private String customerIP;
	/**
	 * 客户的网盘地址
	 * @deprecated
	 */
	private String cloudDiskIp;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerIP() {
		return customerIP;
	}

	public void setCustomerIP(String customerIP) {
		this.customerIP = customerIP;
	}

	/**
	 * @deprecated
	 */
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}
	/**
	 * @deprecated
	 */
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}

}
