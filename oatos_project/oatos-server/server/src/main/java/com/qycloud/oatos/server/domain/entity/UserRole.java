package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

/**
 * 用户角色
 * @author yang
 *
 */
public class UserRole implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 角色ID
	 */
	private long roleId;

	public UserRole() {
	}

	public UserRole(long userId, long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
