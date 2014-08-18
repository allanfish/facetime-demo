package com.conlect.oatos.dto.client.mail;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailListDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;
	private List<MailDTO> list = new ArrayList<MailDTO>();

	public MailListDTO() {
		super();
	}

	public MailListDTO(List<MailDTO> list) {
		super();
		this.list = list;
	}

	public List<MailDTO> getList() {
		return list;
	}

	public void setList(List<MailDTO> list) {
		this.list = list;
	}

}
