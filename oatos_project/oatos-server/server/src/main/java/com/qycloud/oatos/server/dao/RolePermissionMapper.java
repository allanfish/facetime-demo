package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.RolePermission;

/**
 * 角色权限实体层
 * @author yang
 *
 */
public interface RolePermissionMapper {

	/**
	 * 插入权限信息
	 * @param rolePermission
	 */
	void addRolePermisssion(RolePermission rolePermission);

	/**
	 * 插入修改权限信息
	 * @param rolePermission
	 * @return
	 */
	int updateRolePermisssions(List<RolePermission> rolePermissions);

	/**
	 * 按企业获取权限信息
	 * @param entId
	 * @return
	 */
	List<RolePermission> getRolePermisssionsByEntId(long entId);

	/**
	 * 按角色删除权限信息
	 * @param roleId
	 */
	void deletePermissionByRoleId(long roleId);

	/**
	 * 按用户和文件夹获取权限信息
	 * @param userId
	 * @param folderId
	 * @return
	 */
	List<RolePermission> getRolePermisssionsByUserIdAndFolderId(
			@Param("userId") long userId, @Param("folderId") long folderId);

	/**
	 * 复制附文件夹的权限设置至子文件夹
	 * @param parentFolderId
	 * @param folderId
	 */
	void copyParentRolePermisssionToChildFolder(
			@Param("parentFolderId") long parentFolderId,
			@Param("folderId") long folderId);

	/**
	 * 获取用户的企业网盘权限
	 * @param userId
	 * @return
	 */
	List<RolePermission> getRolePermisssionsByUserId(long userId);
}
