package com.conlect.oatos.dto.autobean.pay;

import java.io.Serializable;
import java.util.List;

public interface IServiceTypeListDTO extends Serializable {

	public List<IServiceTypeDTO> getList();

	public void setList(List<IServiceTypeDTO> list);
}
