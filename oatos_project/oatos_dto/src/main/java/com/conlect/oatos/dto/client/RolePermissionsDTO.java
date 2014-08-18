package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色企业网盘权限list
 * 
 * @author yang
 * 
 */
public class RolePermissionsDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RolePermissionDTO> permissionList = new ArrayList<RolePermissionDTO>();

	public List<RolePermissionDTO> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<RolePermissionDTO> permissionList) {
		this.permissionList = permissionList;
	}
}
