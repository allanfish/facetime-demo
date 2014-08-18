package com.conlect.oatos.dto.client.user;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;
import com.conlect.oatos.dto.client.EnterpriseUserDTO;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class EnterpriseUserListDTO implements BaseDTO {

	private static final long serialVersionUID = -672763826667053313L;
	private List<EnterpriseUserDTO> list = new ArrayList<EnterpriseUserDTO>();

	public List<EnterpriseUserDTO> getList() {
		return list;
	}

	public void setList(List<EnterpriseUserDTO> list) {
		this.list = list;
	}

}
