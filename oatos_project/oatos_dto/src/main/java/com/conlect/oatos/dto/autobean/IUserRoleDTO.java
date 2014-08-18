package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * user role
 * 
 * @author yang
 * 
 */
public interface IUserRoleDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	List<IRoleDTO> getRoles();

	void setRoles(List<IRoleDTO> roles);
}
