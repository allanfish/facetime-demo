package com.conlect.oatos.dto.autobean;

public interface IEnterpriseLoginDTO extends ILoginDTO {

	String getEnterpriseName();

	void setEnterpriseName(String enterpriseName);

	public String getDeviceToken();

	public void setDeviceToken(String deviceToken);

}
