package com.conlect.oatos.dto.client.mail;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ExtendMailDTO implements BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MailDTO mailDTO;
	private String basePath;
	private Boolean sendForSystemAccount = true;

	public void setMailDTO(MailDTO mailDTO) {
		this.mailDTO = mailDTO;
	}

	public MailDTO getMailDTO() {
		return mailDTO;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setSendForSystemAccount(Boolean sendForSystemAccount) {
		this.sendForSystemAccount = sendForSystemAccount;
	}

	public Boolean getSendForSystemAccount() {
		return sendForSystemAccount;
	}
}
