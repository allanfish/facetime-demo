package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

public interface IRoleUsersDTO extends Serializable {
	List<IRoleUserDTO> getRoleUserDTOs();

	void setRoleUserDTOs(List<IRoleUserDTO> roleUserDTOs);
}
