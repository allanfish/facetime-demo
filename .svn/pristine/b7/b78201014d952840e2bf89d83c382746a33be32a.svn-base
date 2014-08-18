package com.conlect.oatos.dto.client.mail;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailContactsDTO implements BaseDTO {
	private List<MailContactDTO> mailContacts;

	public MailContactsDTO() {
		super();
	}

	public MailContactsDTO(List<MailContactDTO> mailContacts) {
		super();
		this.mailContacts = mailContacts;
	}

	public List<MailContactDTO> getMailContacts() {
		return mailContacts;
	}

	public void setMailContacts(List<MailContactDTO> mailContacts) {
		this.mailContacts = mailContacts;
	}

}
