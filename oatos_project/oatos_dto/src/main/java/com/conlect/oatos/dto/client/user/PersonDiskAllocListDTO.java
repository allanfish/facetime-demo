package com.conlect.oatos.dto.client.user;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.client.BaseDTO;

@SuppressWarnings("serial")
public class PersonDiskAllocListDTO implements BaseDTO {

	private long entId;
	private List<PersonDiskAllocDTO> personDiskList = new ArrayList<PersonDiskAllocDTO>();

	public long getEntId() {
		return entId;
	}

	public void setEntId(long entId) {
		this.entId = entId;
	}

	public List<PersonDiskAllocDTO> getPersonDiskList() {
		return personDiskList;
	}

	public void setPersonDiskList(List<PersonDiskAllocDTO> personDiskList) {
		this.personDiskList = personDiskList;
	}

}
