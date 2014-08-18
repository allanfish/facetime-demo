package com.conlect.oatos.dto.client.mail;

import java.util.List;

import com.conlect.oatos.dto.autobean.mail.IDTOList;
import com.conlect.oatos.dto.client.BaseDTO;

public class DTOList implements BaseDTO, IDTOList {

	private static final long serialVersionUID = 1L;

	private List<?> list;

	public DTOList() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList() {
		return (List<T>) list;
	}

	@Override
	public <T> void setList(List<T> list) {
		this.list = list;
	}

}
