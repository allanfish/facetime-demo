package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色list
 * 
 * @author yang
 * 
 */
public class RolesDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RoleDTO> roleList = new ArrayList<RoleDTO>();

	public List<RoleDTO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleDTO> roleList) {
		this.roleList = roleList;
	}

}
