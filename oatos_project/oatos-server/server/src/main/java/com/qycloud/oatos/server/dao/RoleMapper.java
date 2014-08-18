package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.Role;
import com.qycloud.oatos.server.domain.entity.UserRole;

/**
 * 角色实体层
 * @author yang
 *
 */
public interface RoleMapper {

	/**
	 * 添加角色
	 * @param role
	 */
	void addRole(Role role);

	/**
	 * 批量添加角色
	 * @param roles
	 */
	void addRoles(List<Role> roles);

	/**
	 * 修改角色信息
	 * @param role
	 */
	void updateRole(Role role);

	/**
	 * 删除角色
	 * @param role
	 */
	void deleteRole(Role role);

	/**
	 * 获取企业角色列表
	 * @param entId
	 * @return
	 */
	List<Role> getRolesByEntId(long entId);

	/**
	 * 取企业用户角色信息
	 * @param entId
	 * @return
	 */
	List<UserRole> getUserRoleByEntId(long entId);

	/**
	 * 添加用户角色
	 * @param roleModel
	 */
	void addUserRole(UserRole roleModel);

	/**
	 * 删除用户角色信息
	 * @param userId
	 */
	void deleteUserRoleByUserId(long userId);

	/**
	 * 按角色删除用户角色信息
	 * @param roleId
	 */
	void deleteUserRoleByRoleId(long roleId);

	/**
	 * 取企业第一个创建的角色
	 * @param entId
	 * @return
	 */
	Long getFirstRoleByEntId(long entId);

	/**
	 * 批量添加用户角色
	 * @param roleModels
	 */
	void addUserRoles(List<UserRole> roleModels);

	/**
	 * 取重名的角色
	 * @param role
	 * @return
	 */
	Role getSameNameRole(Role role);
}
