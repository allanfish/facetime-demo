package com.qycloud.oatos.server.domain.model;

import java.io.Serializable;

import com.conlect.oatos.dto.status.CallStatus;
import com.conlect.oatos.dto.status.UserStatus;

/**
 * user information in memory cached
 * 
 * @author yang
 * 
 */
public class CachedUser implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private long userId;
	private String token = "";
	private String onlineStatus = UserStatus.Logout;
	private String p2pKey;
	private String callStatus = CallStatus.Free;
	private String agent;
	private int customerCounter;

	public int getCustomerCounter() {
    	return customerCounter;
    }

	public void setCustomerCounter(int customerCounter) {
    	this.customerCounter = customerCounter;
    }

	public CachedUser(long userId) {
		this.userId = userId;
	}

	public CachedUser(long userId, String onlineStatus, String callStatus, String agent) {
		this.userId = userId;
		this.onlineStatus = onlineStatus;
		this.callStatus = callStatus;
		this.agent = agent;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getP2pKey() {
		return p2pKey;
	}

	public void setP2pKey(String p2pKey) {
		this.p2pKey = p2pKey;
	}

	public String getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(String callStatus) {
		this.callStatus = callStatus;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

}
