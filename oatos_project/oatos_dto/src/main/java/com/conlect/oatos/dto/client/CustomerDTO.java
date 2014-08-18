package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.ICustomerDTO;

/**
 * 临时客户DTO
 * 
 * @author jinkerjiang
 * 
 */
public class CustomerDTO implements ICustomerDTO {
	private static final long serialVersionUID = -5959166593423504259L;

	/**
	 * 客户IP
	 */
	private String ip;
	/**
	 * 客户用户ID
	 */
	private Long userId;
	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(String ip, Long userId, String cloudDiskIp) {
		super();
		this.ip = ip;
		this.userId = userId;
		this.cloudDiskIp = cloudDiskIp;
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

	/**
	 * @return the userId
	 */
	@Override
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	@Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}

}
