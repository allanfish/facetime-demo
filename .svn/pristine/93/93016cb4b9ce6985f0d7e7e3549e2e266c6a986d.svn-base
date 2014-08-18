package com.qycloud.oatos.server.domain.entity;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.pay.ServiceTypeDTO;

/**
 * 企业服务类型
 * @author yang
 *
 */
public class ServiceType {
	/**
	 * 服务ID
	 */
	private long serviceId;

	/**
	 * 服务名<br>
	 * {@link com.conlect.oatos.dto.status.OrderItemType}
	 */
	private String serviceName;

	/**
	 * 单价
	 */
	private int price;

	/**
	 * 描述
	 */
	private String desc;

	public ServiceTypeDTO toServiceTypeDTO() {
		ServiceTypeDTO dto = new ServiceTypeDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}