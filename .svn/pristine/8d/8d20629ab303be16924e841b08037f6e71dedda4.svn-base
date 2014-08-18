package com.conlect.oatos.dto.client.admin;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.admin.IAdminDepartmentDTO;

/**
 * 二级管理员可操作的部门
 * 
 * @author yang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AdminDepartmentDTO implements IAdminDepartmentDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 部门ID
	 */
	private long departmentId;

	public AdminDepartmentDTO() {
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public long getDepartmentId() {
		return departmentId;
	}

	@Override
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

}
