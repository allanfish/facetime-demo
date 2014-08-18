package com.conlect.oatos.dto.client.pay;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.client.BaseDTO;

@SuppressWarnings("serial")
public class OrderItemListDTO implements BaseDTO {

	/**
	 * 订单列表
	 */
	private List<OrderItemDTO> list = new ArrayList<OrderItemDTO>();

	public List<OrderItemDTO> getList() {
		return list;
	}

	public void setList(List<OrderItemDTO> list) {
		this.list = list;
	}

}
