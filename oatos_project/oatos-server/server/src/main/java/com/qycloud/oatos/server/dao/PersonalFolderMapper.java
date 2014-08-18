package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.PersonalFolder;

/**
 * 个人网盘文件夹实体层
 * @author yang
 *
 */
public interface PersonalFolderMapper {
	/**
	 * 新增文件夹
	 * 
	 * @param folder
	 */
	void addPersonalFolder(PersonalFolder folder);

	/**
	 * 修改文件夹
	 * 
	 * @param folder
	 */
	void updatePersonalFolder(PersonalFolder folder);

	/**
	 * 删除文件夹
	 * 
	 * @param folder
	 */
	void deletePersonalFolder(long folderId);

	/**
	 * 获取用户文件夹，不包括回收站中文件
	 * 
	 * @param UserId
	 */
	List<PersonalFolder> getFoldersByUserId(long userId);
	
	/**
	 * 获取个人网盘文件夹信息
	 * @param folderId
	 * @return
	 */
	PersonalFolder getFolderById(long folderId);

	/**
	 * 是否同一文件下已经存在同一文件夹名称
	 * 
	 * @param folder
	 * @return
	 */
	PersonalFolder getSameFolder(PersonalFolder folder);

	/**
	 * 根据文件夹名及用户 id取文件夹信息
	 * 
	 * @param folder
	 * @return
	 */
	PersonalFolder getDefaultFolder(PersonalFolder folder);

	/**
	 * delete folder by user
	 * 
	 * @param userId
	 */
	void deleteFolderByUserId(long userId);

	/**
	 * 获取用户文件夹，包括回收站中文件
	 * 
	 * @param UserId
	 */
	List<PersonalFolder> getAllFoldersByUserId(long userId);

	/**
	 * 按文件夹取子文件夹
	 * @param userId
	 * @param folderId
	 * @return
	 */
	List<PersonalFolder> getFoldersByUserIdAndFolderId(@Param("userId") long userId, @Param("folderId") Long folderId);
	
	/**
	 * 通过用户ID得到个人网盘回收站的文件夹
	 * @param userId
	 * @return
	 */
	List<PersonalFolder> getPersonalFolderListInRecycleByUserId(long userId);

}
