package com.conlect.oatos.dto.client.pay;

import java.util.List;

import com.conlect.oatos.dto.autobean.pay.BaseEntPayDTO;

public class EntPayDTO implements BaseEntPayDTO {

	private static final long serialVersionUID = 2678958155138088826L;
	private long entId;
	private String entName;
	private long remainEntDiskSize;
	private long remainPersonDiskSize;
	private long remainShareLinkSize;
	private long remainUserSize;
	private List<ServiceTypeDTO> serviceTypes;

	private ServiceStatusDTO serviceStatus;

	public List<ServiceTypeDTO> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(List<ServiceTypeDTO> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	public ServiceStatusDTO getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(ServiceStatusDTO serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	@Override
	public long getEntId() {
		return entId;
	}

	@Override
	public void setEntId(long entId) {
		this.entId = entId;
	}

	@Override
	public String getEntName() {
		return entName;
	}

	@Override
	public void setEntName(String entName) {
		this.entName = entName;
	}

	@Override
	public long getRemainEntDiskSize() {
		return remainEntDiskSize;
	}

	@Override
	public void setRemainEntDiskSize(long remainEntDiskSize) {
		this.remainEntDiskSize = remainEntDiskSize;
	}

	@Override
	public long getRemainPersonDiskSize() {
		return remainPersonDiskSize;
	}

	@Override
	public void setRemainPersonDiskSize(long remainPersonDiskSize) {
		this.remainPersonDiskSize = remainPersonDiskSize;
	}

	@Override
	public long getRemainShareLinkSize() {
		return remainShareLinkSize;
	}

	@Override
	public void setRemainShareLinkSize(long remainShareLinkSize) {
		this.remainShareLinkSize = remainShareLinkSize;
	}

	@Override
	public long getRemainUserSize() {
		return remainUserSize;
	}

	@Override
	public void setRemainUserSize(long remainUserSize) {
		this.remainUserSize = remainUserSize;
	}

}
