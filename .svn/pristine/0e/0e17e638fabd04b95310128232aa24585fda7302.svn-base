package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.ShareFolderHistory;

/**
 * 企业网盘文件夹实体层
 * @author yang
 *
 */
public interface ShareFolderHistoryMapper {

	/**
	 * 插入文件夹历史记录
	 * @param historyModel
	 */
	void addShareFolderHistory(ShareFolderHistory historyModel);

	/**
	 * 批量插入文件夹历史记录
	 * @param historyList
	 */
	void addShareFolderHistoryList(List<ShareFolderHistory> historyList);

	/**
	 * 获取文件夹历史版本信息
	 * @param folderId
	 * @param version
	 * @return
	 */
	ShareFolderHistory getShareFolderHistoryByVersion(
			@Param("folderId") long folderId, @Param("version") long version);

	/**
	 * 获取文件夹历史版本列表
	 * @param folderId
	 * @return
	 */
	List<ShareFolderHistory> getShareFolderHistoryByFolderId(long folderId);

	/**
	 * 取某个版本的文件夹版本信息
	 * @param folderId
	 * @param version
	 * @return
	 */
	List<ShareFolderHistory> getShareFolderVersionDetailByFolderId(
			@Param("folderId") long folderId, @Param("version") long version);

	/**
	 * 取某个版本的文件夹状态信息
	 * @param folderId
	 * @param version
	 * @return
	 */
	List<ShareFolderHistory> getShareFolderStatusByFolderIdAndVersion(
			@Param("folderId") long folderId, @Param("version") long version);

	/**
	 * 取某个版本以后新建的文件夹
	 * @param folderId
	 * @param version
	 * @return
	 */
	List<ShareFolderHistory> getShareFolderNewByFolderIdAndVersion(
			@Param("folderId") long folderId, @Param("version") long version);

	/**
	 * 按用户取其修改的文件夹记录
	 * @param folderId
	 * @return
	 */
	List<ShareFolderHistory> getShareFolderHistoryByUserId(long folderId);
}
