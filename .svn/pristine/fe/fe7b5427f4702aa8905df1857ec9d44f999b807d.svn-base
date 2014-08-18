package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.AdminFolder;

/**
 * 管理员文件夹权限实体层
 * 
 * @author yang
 * 
 */
public interface AdminFolderMapper {

	/**
	 * 添加管理员文件夹权限
	 * 
	 * @param folder
	 */
	void addAdminFolder(AdminFolder folder);

	/**
	 * 批量添加管理员文件夹权限
	 * 
	 * @param folders
	 */
	void addAdminFolders(List<AdminFolder> folders);

	/**
	 * 删除管理员文件夹权限
	 * 
	 * @param userId
	 */
	void deleteAdminFoldersByUserId(long userId);

	/**
	 * 获取管理员文件夹权限
	 * 
	 * @param userId
	 * @return
	 */
	List<AdminFolder> getAdminFoldersByUserId(long userId);

}
