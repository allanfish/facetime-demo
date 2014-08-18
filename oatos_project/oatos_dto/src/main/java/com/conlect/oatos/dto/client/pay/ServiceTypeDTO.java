package com.conlect.oatos.dto.client.pay;

import com.conlect.oatos.dto.autobean.pay.IServiceTypeDTO;

/**
 * 服务类型价格
 * 
 * @author yang
 * 
 */
@SuppressWarnings("serial")
public class ServiceTypeDTO implements IServiceTypeDTO {
	private long serviceId;

	private String serviceName;

	private int price;

	private String desc;

	@Override
	public long getServiceId() {
		return serviceId;
	}

	@Override
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
