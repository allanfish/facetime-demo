package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.Mail;

/**
 * 邮件实体层
 * @author yang
 *
 */
public interface MailMapper {

	/**
	 * 插入邮件信息
	 * @param mailModel
	 */
	void addMail(Mail mailModel);

	/**
	 * 批量插入邮件
	 * @param mailModels
	 */
	void addMails(List<Mail> mailModels);

	/**
	 * 删除邮件
	 * @param mailIds
	 */
	void deleteMails(final List<Long> mailIds);

	/**
	 * 获取邮件信息
	 * @param mailId
	 * @return
	 */
	Mail getMailById(long mailId);

	/**
	 * 获取多个邮件信息
	 * @param mailIds
	 * @return
	 */
	List<Mail> getMailListById(List<Long> mailIds);

	/**
	 * 分页获取邮件信息
	 * @param folderId
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	List<Mail> getMailsByFolderId(@Param("folderId") long folderId,
			@Param("skipResults") int skipResults,
			@Param("maxResults") int maxResults);

	/**
	 * 修改邮件信息
	 * @param mailModel
	 */
	void updateMail(Mail mailModel);

	/**
	 * 设置邮件为已读
	 * @param mailId
	 */
	void setMailRead(String mailId);
}
