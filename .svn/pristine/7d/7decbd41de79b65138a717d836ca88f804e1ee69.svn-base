package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.MailContact;

/**
 * 邮箱联系人实体层
 * @author yang
 *
 */
public interface MailContactMapper {

	/**
	 * 添加联系人
	 * @param mailContactModel
	 */
	void addMailContact(MailContact mailContactModel);

	/**
	 * 获取联系人列表
	 * @param mailAccountId
	 * @return
	 */
	List<MailContact> getMailContactsByUserId(long mailAccountId);

	/**
	 * 删除联系人
	 * @param mailContactId
	 */
	void deleteMailContact(long mailContactId);

	/**
	 * 修改联系人信息
	 * @param mailContactModel
	 */
	void updateMailContact(MailContact mailContactModel);

	/**
	 * 删除某个分组的联系人
	 * @param mailContactGroupId
	 */
	void deleteMailContactByGroupId(long mailContactGroupId);

	/**
	 * 删除未分组的联系人
	 * @param userId
	 */
	void deleteUngroupMailContactByUserId(long userId);
}
