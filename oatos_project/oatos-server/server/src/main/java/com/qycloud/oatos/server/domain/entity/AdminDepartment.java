package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import com.conlect.oatos.dto.client.admin.AdminDepartmentDTO;

/**
 * 二级管理员部门操作权限
 * @author yang
 *
 */
public class AdminDepartment implements Serializable {

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

	public AdminDepartment() {
	}

	public AdminDepartment(AdminDepartmentDTO d) {
		userId = d.getUserId();
		departmentId = d.getDepartmentId();
	}

	public AdminDepartmentDTO toAdminDepartmentDTO() {
		AdminDepartmentDTO d = new AdminDepartmentDTO();
		d.setUserId(userId);
		d.setDepartmentId(departmentId);
		return d;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

}
