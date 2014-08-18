package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 角色用户list
 * 
 * @author yang
 * 
 */
public class RoleUsersDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RoleUserDTO> roleUserDTOs;

	public RoleUsersDTO() {
	}

	public List<RoleUserDTO> getRoleUserDTOs() {
		return roleUserDTOs;
	}

	public void setRoleUserDTOs(List<RoleUserDTO> roleUserDTOs) {
		this.roleUserDTOs = roleUserDTOs;
	}

}
