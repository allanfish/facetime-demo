package com.conlect.oatos.dto.client.admin;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

/**
 * 二级管理员list
 * 
 * @author yang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AdminsDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 二级管理员list
	 */
	private List<AdminDTO> admins;

	public AdminsDTO() {
	}

	public List<AdminDTO> getAdmins() {
		return admins;
	}

	public void setAdmins(List<AdminDTO> admins) {
		this.admins = admins;
	}

}
