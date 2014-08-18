package com.conlect.oatos.dto.client.mail;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

/**
 * 邮箱联系人组
 * 
 * @author jinkerjiang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailContactGroupDTO implements BaseDTO {
	private static final long serialVersionUID = -7290179675059077419L;
	private long mailContactGroupId;
	private String groupName;
	private long userId;
	private List<MailContactDTO> mailContacts = new ArrayList<MailContactDTO>();

	public MailContactGroupDTO() {
		super();
	}

	public MailContactGroupDTO(long mailContactGroupId, String groupName,
			long userId) {
		super();
		this.mailContactGroupId = mailContactGroupId;
		this.groupName = groupName;
		this.userId = userId;
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

	public List<MailContactDTO> getMailContacts() {
		return mailContacts;
	}

	public void setMailContacts(List<MailContactDTO> mailContacts) {
		this.mailContacts = mailContacts;
	}

	public void addMailContact(MailContactDTO mailContact) {
		mailContacts.add(mailContact);
	}
}
