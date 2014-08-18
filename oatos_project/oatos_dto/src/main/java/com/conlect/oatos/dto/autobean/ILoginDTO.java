package com.conlect.oatos.dto.autobean;

public interface ILoginDTO extends INonceDTO {

	String getAccount();

	void setAccount(String account);

	int getKeepUserInfo();

	void setKeepUserInfo(int keepUserInfo);

	String getPassword();

	void setPassword(String hashPassword);

	String getClientId();

	void setClientId(String clientId);

	String getIp();

	void setIp(String ip);
}