package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IReLoginDTO;
import com.conlect.oatos.dto.status.UserAgent;

/**
 * 重新登录DTO
 * 
 * @author yang
 * 
 */
public class ReLoginDTO implements IReLoginDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户token
	 */
	private String token;

	/**
	 * 用户使用的登录设备<br> {@link UserAgent}
	 */
	private String agent = UserAgent.Web;
	/**
	 * 标识客户端
	 */
	private String clientId;

	public ReLoginDTO() {
	}

	public ReLoginDTO(String token, String agent) {
		this.token = token;
		this.agent = agent;
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String getAgent() {
		return agent;
	}

	@Override
	public void setAgent(String agent) {
		this.agent = agent;
	}

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
