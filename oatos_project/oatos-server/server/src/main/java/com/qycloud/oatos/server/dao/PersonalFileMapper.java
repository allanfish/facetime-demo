package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.PersonalFile;

/**
 * 个人网盘文件实体层
 * @author yang
 * 
 */
public interface PersonalFileMapper {

	/**
	 * 增加用户文件
	 * 
	 * @param file
	 */
	void addPersonalFile(PersonalFile file);

	/**
	 * 修改文件
	 * 
	 * @param file
	 */
	void updatePersonalFile(PersonalFile file);

	/**
	 * 删除文件
	 * @param fileId
	 */
	void deletePersonalFile(long fileId);

	/**
	 * 获取用户文件，不包括回收站中文件
	 * 
	 * @param UserId
	 * @return
	 */
	List<PersonalFile> getFilesByUserId(long userId);

	/**
	 * 获取某文件夹下文件
	 * 
	 * @param Folder
	 * @return
	 */
	List<PersonalFile> getFilesByFolderId(long folderId);

	/**
	 * 获取单个文件
	 * 
	 * @param file
	 * @return
	 */
	PersonalFile getFileById(long fileId);

	/**
	 * 检查是否存在同名文件
	 * 
	 * @param file
	 * @return
	 */
	PersonalFile getSameNameFile(PersonalFile file);

	/**
	 * 取个人网盘已用空间
	 * 
	 * @param userId
	 * @return
	 */
	long getFileSizeSumByUserId(long userId);

	/**
	 * 按用户删除文件
	 * 
	 * @param userId
	 */
	void deleteFileByUserId(long userId);

	/**
	 * 获取用户文件包括回收站中文件
	 * 
	 * @param UserId
	 * @return
	 */
	List<PersonalFile> getAllFilesByUserId(long userId);

	/**
	 * 修改文件页数
	 * @param fileId
	 * @param pageCount
	 */
	void updatePersonalFilePageCount(@Param("fileId") long fileId,
			@Param("pageCount") Integer pageCount);

	/**
	 * 按文件夹取文件
	 * @param userId
	 * @param folderId
	 * @return
	 */
	List<PersonalFile> getFilesByUserIdAndFolderId(@Param("userId") long userId, @Param("folderId") Long folderId);

	/**
	 * 按文件名搜索文件
	 * @param userId
	 * @param folderId
	 * @param fileNames
	 * @return
	 */
	List<PersonalFile> getFileList(@Param("userId") String userId,
			@Param("folderId") long folderId,
			@Param("fileNames") String fileNames);
	
	/**
	 * 得到个人网盘回收站中的文件
	 * @param userId
	 * @return
	 */
	List<PersonalFile> getPersonalFileInRecycleByUserId(long userId);

}
