package com.qycloud.oatos.server.domain.entity;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.mail.MailContactDTO;

/**
 * 邮箱联系人
 * 
 * @author yang
 * 
 */
public class MailContact {
	/**
	 * 联系人ID
	 */
	private long mailContactId;
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 联系人分组ID
	 */
	private Long mailContactGroupId;
	/**
	 * 联系人名
	 */
	private String name;
	/**
	 * 联系人邮箱地址
	 */
	private String mailAddress;
	/**
	 * 联系人电话
	 */
	private String phone;
	/**
	 * 备注
	 */
	private String remark;

	public MailContact() {
		super();
	}

	public MailContact(MailContactDTO mailContactDTO) {
		BeanUtils.copyProperties(mailContactDTO, this);
	}

	public MailContact(long mailContactId, long userId,
			Long mailContactGroupId, String name, String mailAddress,
			String phone, String remark) {
		super();
		this.mailContactId = mailContactId;
		this.userId = userId;
		this.mailContactGroupId = mailContactGroupId;
		this.name = name;
		this.mailAddress = mailAddress;
		this.phone = phone;
		this.remark = remark;
	}

	public long getMailContactId() {
		return mailContactId;
	}

	public void setMailContactId(long mailContactId) {
		this.mailContactId = mailContactId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Long getMailContactGroupId() {
		return mailContactGroupId;
	}

	public void setMailContactGroupId(Long mailContactGroupId) {
		this.mailContactGroupId = mailContactGroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public MailContactDTO toDTO() {
		MailContactDTO dto = new MailContactDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
