package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 部门 list DTO
 * 
 * @author yang
 * 
 */
public class DepartmentsDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 部门列表
	 */
	private List<DepartmentDTO> departmentList;

	public List<DepartmentDTO> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DepartmentDTO> departmentList) {
		this.departmentList = departmentList;
	}

}
