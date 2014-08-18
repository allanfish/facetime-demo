package com.qycloud.oatos.server.domain.entity;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.mail.MailContactGroupDTO;

/**
 * 邮箱联系人组
 * 
 * @author yang
 * 
 */
public class MailContactGroup {
	/**
	 * 邮箱联系人分组ID
	 */
	private long mailContactGroupId;
	/**
	 * 分组名
	 */
	private String groupName;
	/**
	 * 用户id
	 */
	private long userId;

	public MailContactGroup() {
		super();
	}

	public MailContactGroup(MailContactGroupDTO dto) {
		BeanUtils.copyProperties(dto, this);
	}

	public MailContactGroup(long mailContactGroupId, String groupName,
			long mailAccountId) {
		super();
		this.mailContactGroupId = mailContactGroupId;
		this.groupName = groupName;
		this.userId = mailAccountId;
	}

	public long getMailContactGroupId() {
		return mailContactGroupId;
	}

	public void setMailContactGroupId(long mailContactGroupId) {
		this.mailContactGroupId = mailContactGroupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public MailContactGroupDTO toDTO() {
		MailContactGroupDTO dto = new MailContactGroupDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
