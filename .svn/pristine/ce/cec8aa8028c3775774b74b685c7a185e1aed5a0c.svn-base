package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.MailAccount;

/**
 * 邮件帐号实体层
 * @author yang
 * 
 */
public interface MailAccountMapper {

	/**
	 * 新建邮件帐号
	 * @param mailAccountModel
	 */
	void addMailAccount(MailAccount mailAccountModel);

	/**
	 * 获取邮件帐号列表
	 * @param userId
	 * @return
	 */
	List<MailAccount> getMailAccountsByUserId(long userId);

	/**
	 * 获取邮件帐号信息
	 */
	MailAccount getMailAccount(long mailAccId);

	/**
	 * 删除邮件账号
	 * 
	 * @param mailAccountId
	 */
	void deleteMailAccount(long mailAccountId);

	/**
	 * 修改邮件帐号信息
	 * @param mailAccountModel
	 */
	void updateMailAccount(MailAccount mailAccountModel);
}
