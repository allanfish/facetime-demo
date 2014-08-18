package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色list
 * 
 * @author yang
 * 
 */
public class UserRolesDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<UserRoleDTO> userRoleList = new ArrayList<UserRoleDTO>();

	public List<UserRoleDTO> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRoleDTO> userRoleList) {
		this.userRoleList = userRoleList;
	}

}
