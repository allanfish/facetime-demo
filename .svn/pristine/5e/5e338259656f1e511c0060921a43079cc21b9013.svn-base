package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import com.conlect.oatos.dto.client.RoleDTO;

/**
 * 角色
 * @author yang
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private long roleId;
	/**
	 * 企业ID
	 */
	private long enterpriseId;
	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 角色描述
	 */
	private String description;

	public Role() {
	}

	public Role(RoleDTO dto) {
		this.roleId = dto.getRoleId();
		this.enterpriseId = dto.getEnterpriseId();
		this.name = dto.getName();
		this.description = dto.getDescription();
	}

	public RoleDTO toRoleDTO() {
		RoleDTO dto = new RoleDTO();
		dto.setRoleId(this.roleId);
		dto.setEnterpriseId(this.enterpriseId);
		dto.setName(this.name);
		dto.setDescription(this.description);

		return dto;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
