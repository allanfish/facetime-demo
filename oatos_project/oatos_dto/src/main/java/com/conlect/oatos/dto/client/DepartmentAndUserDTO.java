package com.conlect.oatos.dto.client;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 企业部门和用户 DTO
 * 
 * @author yang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DepartmentAndUserDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 部门List
	 */
	private List<DepartmentDTO> departmentList;

	/**
	 * 用户list
	 */
	private List<UserInfoDTO> userList;

	public List<DepartmentDTO> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentDTO> departmentList) {
		this.departmentList = departmentList;
	}

	public List<UserInfoDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfoDTO> userList) {
		this.userList = userList;
	}

}
