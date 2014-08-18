package com.conlect.oatos.dto.client.mail;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.client.BaseDTO;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailFileDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;
	private INetworkFileDTO networkFile;
	private MailAttachDTO mailAttach;

	public INetworkFileDTO getNetworkFile() {
		return networkFile;
	}

	public void setNetworkFile(INetworkFileDTO networkFile) {
		this.networkFile = networkFile;
	}

	public MailAttachDTO getMailAttach() {
		return mailAttach;
	}

	public void setMailAttach(MailAttachDTO mailAttach) {
		this.mailAttach = mailAttach;
	}

}
