package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.ShareFolder;

/**
 * 企业网盘文件夹实体层
 * @author yang
 *
 */
public interface ShareFolderMapper {

	/**
	 * 新建文件夹
	 * @param folder
	 */
	void addShareFolder(ShareFolder folder);

	/**
	 * 修改文件夹信息
	 * @param folder
	 */
	void updateShareFolder(ShareFolder folder);

	/**
	 * 批量修改文件夹信息
	 * @param folders
	 */
	void updateShareFolders(List<ShareFolder> folders);

	/**
	 * 删除文件夹
	 * @param folder
	 */
	void deleteShareFolder(ShareFolder folder);

	/**
	 * 批量删除文件夹
	 * @param folders
	 */
	void deleteShareFolders(List<ShareFolder> folders);

	/**
	 * 获取单个文件夹信息
	 * @param folderId
	 * @return
	 */
	ShareFolder getShareFolderById(long folderId);

	/**
	 * 取文件夹列表，不包括已删除和回收站中文件夹
	 * 
	 * @param enterpriseId
	 * @return
	 */
	List<ShareFolder> getShareFoldersByEntId(long enterpriseId);

	/**
	 * 取同名文件夹
	 * 
	 * @param shareFolder
	 * @return
	 */
	ShareFolder getSameShareFolder(ShareFolder shareFolder);

	/**
	 * 取文件夹列表，包括回收站中文件夹，不包括已删除文件夹
	 * 
	 * @param enterpriseId
	 * @return
	 */
	List<ShareFolder> getAllShareFoldersByEntId(long enterpriseId);

	/**
	 * 取子文件夹，folderId为空时，取顶层文件夹
	 * @param entId
	 * @param folderId
	 * @return
	 */
	List<ShareFolder> getFoldersByEntIdAndFolderId(@Param("entId") long entId, @Param("folderId") Long folderId);
	
	/**
	 * 通过企业ID取企业网盘回收站中文件夹列表
	 * @param enterpriseId
	 * @return
	 */
	List<ShareFolder> getShareFolderListInRecycleByEnterpriseId(long enterpriseId);
	
}
