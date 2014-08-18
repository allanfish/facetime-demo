package com.conlect.oatos.dto.client.mail;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailFolderListDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	private List<MailFolderDTO> folders = new ArrayList<MailFolderDTO>();

	public MailFolderListDTO() {
	}

	public MailFolderListDTO(List<MailFolderDTO> folders) {
		this.folders = folders;
	}

	public List<MailFolderDTO> getFolders() {
		return folders;
	}

	public void setFolders(List<MailFolderDTO> folders) {
		this.folders = folders;
	}

}
