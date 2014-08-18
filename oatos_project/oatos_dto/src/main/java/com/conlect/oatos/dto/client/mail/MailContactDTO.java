package com.conlect.oatos.dto.client.mail;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.mail.IMailContactDTO;
import com.conlect.oatos.dto.client.BaseDTO;

/**
 * 邮箱联系人
 * 
 * @author jinkerjiang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailContactDTO implements BaseDTO, IMailContactDTO {
	private long mailContactId;
	private long userId;
	private Long mailContactGroupId;
	private String name;
	private String mailAddress;
	private String phone;
	private String remark;

	public MailContactDTO() {
		super();
	}

	public MailContactDTO(long mailContactId, long userId,
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

	@Override
	public long getMailContactId() {
		return mailContactId;
	}

	@Override
	public void setMailContactId(long mailContactId) {
		this.mailContactId = mailContactId;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public Long getMailContactGroupId() {
		return mailContactGroupId;
	}

	@Override
	public void setMailContactGroupId(Long mailContactGroupId) {
		this.mailContactGroupId = mailContactGroupId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getMailAddress() {
		return mailAddress;
	}

	@Override
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
