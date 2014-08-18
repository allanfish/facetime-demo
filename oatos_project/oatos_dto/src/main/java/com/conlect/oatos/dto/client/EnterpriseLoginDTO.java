package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IEnterpriseLoginDTO;

/**
 * 企业用户登录dto
 * 
 * @author yang
 * 
 */
public class EnterpriseLoginDTO extends LoginDTO implements IEnterpriseLoginDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 企业名称
	 */
	public String enterpriseName;

	/**
	 * IOS设备使用的device 标识
	 */
	private String deviceToken;

	@Override
	public String getDeviceToken() {
		return deviceToken;
	}

	@Override
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	@Override
	public String getEnterpriseName() {
		return enterpriseName;
	}

	@Override
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

}
