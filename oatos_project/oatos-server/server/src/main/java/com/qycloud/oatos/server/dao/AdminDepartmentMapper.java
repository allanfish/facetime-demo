package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.AdminDepartment;

/**
 * 管理员部门权限实体层
 * 
 * @author yang
 * 
 */
public interface AdminDepartmentMapper {

	/**
	 * 添加管理员部门权限
	 * 
	 * @param dep
	 */
	void addAdminDepartment(AdminDepartment dep);

	/**
	 * 批量添加管理员部门权限
	 * 
	 * @param deps
	 */
	void addAdminDepartments(List<AdminDepartment> deps);

	/**
	 * 删除管理员部门权限
	 * 
	 * @param userId
	 */
	void deleteAdminDepartmentsByUserId(long userId);

	/**
	 * 获取管理员部门权限
	 * 
	 * @param userId
	 * @return
	 */
	List<AdminDepartment> getAdminDepartmentsByUserId(long userId);

}
