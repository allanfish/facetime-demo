package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.MailFolder;

/**
 * 邮件夹实体层
 * @author yang
 *
 */
public interface MailFolderMapper {

	/**
	 * 新建邮件夹
	 * @param folder
	 */
	void addMailFolder(MailFolder folder);

	/**
	 * 批量新建邮件夹
	 * @param folders
	 */
	void addMailFolders(List<MailFolder> folders);

	/**
	 * 获取邮件夹列表
	 * @param mailAccountId
	 * @return
	 */
	List<MailFolder> getMailFoldersByAccountId(long mailAccountId);

	/**
	 * 获取子邮件夹
	 * @param mailAccountId
	 * @param parentFolderId
	 * @return
	 */
	List<MailFolder> getSubFolders(@Param("mailAccountId") long mailAccountId,
			@Param("parentFolderId") Long parentFolderId);

	/**
	 * 获取邮件夹信息
	 * @param folderId
	 * @return
	 */
	MailFolder getMailFolderById(long folderId);

	/**
	 * 修改邮件夹信息
	 * @param folder
	 */
	void updateMailFolder(MailFolder folder);
}
