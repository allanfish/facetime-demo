package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.ShareFileHistory;

/**
 * 企业网盘文件历史记录实体层
 * @author yang
 *
 */
public interface ShareFileHistoryMapper {

	/**
	 * 插入历史记录
	 * @param history
	 */
	void addShareFileHistory(ShareFileHistory history);

	/**
	 * 批量插入历史记录
	 * @param historyList
	 */
	void addShareFileHistoryList(List<ShareFileHistory> historyList);

	/**
	 * 按版本取文件历史版本信息
	 * @param fileId
	 * @param version
	 * @return
	 */
	ShareFileHistory getShareFileHistoryByVersion(@Param("fileId") long fileId,
			@Param("version") long version);

	/**
	 * 取文件历史版本记录
	 * @param fileId
	 * @return
	 */
	List<ShareFileHistory> getShareFileHistoryByFileId(long fileId);

	/**
	 * 按文件夹取文件历史版本，每个版本只取一条记录
	 * @param folderId
	 * @return
	 */
	List<ShareFileHistory> getShareFileHistoryByFolderId(long folderId);

	/**
	 * 按文件夹取历史版本详细信息
	 * @param folderId
	 * @param version
	 * @return
	 */
	List<ShareFileHistory> getShareFileVersionDetailByFolderId(
			@Param("folderId") long folderId, @Param("version") long version);

	/**
	 * 按文件夹取某个版本的文件信息
	 * @param folderId
	 * @param version
	 * @return
	 */
	List<ShareFileHistory> getShareFileStatusByFolderIdAndVersion(
			@Param("folderId") long folderId, @Param("version") long version);

	/**
	 * 按文件夹取某个版本以后新加的文件
	 * @param folderId
	 * @param version
	 * @return
	 */
	List<ShareFileHistory> getShareFileNewByFolderIdAndVersion(
			@Param("folderId") long folderId, @Param("version") long version);

	/**
	 * 获取企业网盘最大版本号
	 * @param enterpriseId
	 * @return
	 */
	long getShareMaxVersion(long enterpriseId);

	/**
	 * 取某个用户修改的文件记录
	 * @param userId
	 * @return
	 */
	List<ShareFileHistory> getShareFileHistoryByUserId(long userId);
}
