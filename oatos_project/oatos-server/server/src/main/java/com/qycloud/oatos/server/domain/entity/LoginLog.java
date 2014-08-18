package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 * 
 * @author yang
 * 
 */
public class LoginLog implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private long id;

	/**
	 * 企业ID
	 */
	private Long entId;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 用户帐号
	 */
	private String userName;

	/**
	 * 企业帐号
	 */
	private String entName;

	/**
	 * 登陆ip
	 */
	private String ip;

	/**
	 * 登录设备
	 */
	private String equipment;

	/**
	 * 登录时间
	 */
	private Date time;

	public LoginLog() {
	}

	public LoginLog(long id, Long entId, long userId, String userName,
			String entName, String ip, String equipment) {
		this.id = id;
		this.entId = entId;
		this.userId = userId;
		this.userName = userName;
		this.entName = entName;
		this.ip = ip;
		this.equipment = equipment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

}
