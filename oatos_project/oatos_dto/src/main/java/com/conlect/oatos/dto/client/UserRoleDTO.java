package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色dto
 * 
 * @author yang
 * 
 */
public class UserRoleDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 角色
	 */
	private List<RoleDTO> roles = new ArrayList<RoleDTO>();

	public UserRoleDTO() {
	}

	public UserRoleDTO(UserRoleDTO dto) {
		userId = dto.getUserId();
		roles = dto.getRoles();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

}
