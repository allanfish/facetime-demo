package com.conlect.oatos.dto.client.pay;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.client.BaseDTO;

@SuppressWarnings("serial")
public class CurrentServiceListDTO implements BaseDTO {

	/**
	 * 当前服务列表
	 */
	private List<CurrentServiceDTO> list = new ArrayList<CurrentServiceDTO>();

	public List<CurrentServiceDTO> getList() {
		return list;
	}

	public void setList(List<CurrentServiceDTO> list) {
		this.list = list;
	}

}
