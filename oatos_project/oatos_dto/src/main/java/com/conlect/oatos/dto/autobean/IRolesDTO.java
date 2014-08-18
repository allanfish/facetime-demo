package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * roles
 * 
 * @author yang
 * 
 */
public interface IRolesDTO extends Serializable {

	List<IRoleDTO> getRoleList();

	void setRoleList(List<IRoleDTO> roleList);
}
