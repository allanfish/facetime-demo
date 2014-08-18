package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.MailContactGroup;

/**
 * 邮箱联系人分组实体层
 * @author yang
 *
 */
public interface MailContactGroupMapper {

	/**
	 * 新建联系人分组
	 * @param mailContactGroupModel
	 */
	void addMailContactGroup(MailContactGroup mailContactGroupModel);

	/**
	 * 删除联系人分组
	 * @param mailContactGroupId
	 */
	void deleteMailContactGroup(long mailContactGroupId);

	/**
	 * 获取联系人分组列表
	 * @param mailAccountId
	 * @return
	 */
	List<MailContactGroup> getMailContactGroupsByUserId(long mailAccountId);

	/**
	 * 修改分组信息
	 * @param mailContactGroupModel
	 */
	void updateMailContactGroup(MailContactGroup mailContactGroupModel);
}
