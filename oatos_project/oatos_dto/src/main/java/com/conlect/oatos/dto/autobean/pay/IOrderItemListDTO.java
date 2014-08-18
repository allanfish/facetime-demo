package com.conlect.oatos.dto.autobean.pay;

import java.io.Serializable;
import java.util.List;

public interface IOrderItemListDTO extends Serializable {

	public List<IOrderItemDTO> getList();

	public void setList(List<IOrderItemDTO> list);

}
