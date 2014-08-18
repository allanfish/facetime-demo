package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.ShareLink;

/**
 * 外链实体层
 * @author yang
 *
 */
public interface ShareLinkMapper {

	/**
	 * 新建外链
	 * @param shareLink
	 */
	void addShareLink(ShareLink shareLink);

	/**
	 * 按code取外链信息
	 * @param code
	 * @return
	 */
	ShareLink getShareLinkByCode(String code);

	/**
	 * 删除外链
	 * @param linkId
	 */
	void deleteShareLinkByLinkId(long linkId);

	/**
	 * 修改外链信息
	 * @param shareLink
	 */
	void updateShareLink(ShareLink shareLink);

	/**
	 * 取企业的所有外链
	 * @param entId
	 * @return
	 */
	List<ShareLink> getShareLinkByEntId(long entId);

	/**
	 * 取某个文件夹外链信息
	 * @param folderId
	 * @return
	 */
	ShareLink getShareLinkByFolderId(long folderId);

	/**
	 * 取某个文件外链信息
	 * @param fileId
	 * @return
	 */
	ShareLink getShareLinkByFileId(long fileId);

	/**
	 * 取外链信息
	 * @param linkId
	 * @return
	 */
	ShareLink getShareLinkByLinkId(long linkId);

	/**
	 * 修改外链下载次数
	 * @param linkId
	 */
	void updateDownloadCount(long linkId);
}
