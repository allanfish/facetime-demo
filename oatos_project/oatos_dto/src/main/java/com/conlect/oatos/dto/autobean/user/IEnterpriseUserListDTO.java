package com.conlect.oatos.dto.autobean.user;

import java.io.Serializable;
import java.util.List;

import com.conlect.oatos.dto.autobean.IEnterpriseUserDTO;

public interface IEnterpriseUserListDTO extends Serializable {

	public List<IEnterpriseUserDTO> getList();

	public void setList(List<IEnterpriseUserDTO> list);

}
