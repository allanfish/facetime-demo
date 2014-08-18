package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * user roles
 * 
 * @author yang
 * 
 */
public interface IUserRolesDTO extends Serializable {

	List<IUserRoleDTO> getUserRoleList();

	void setUserRoleList(List<IUserRoleDTO> userRoleList);
}
