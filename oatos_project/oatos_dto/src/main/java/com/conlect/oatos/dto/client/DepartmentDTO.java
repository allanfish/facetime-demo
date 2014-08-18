package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门DTO
 * 
 * @author Peter Guo
 * 
 */
public class DepartmentDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 部门ID
	 */
	private long departmentId;
	/**
	 * 企业ID
	 */
	private long enterpriseId;
	/**
	 * 部门名
	 */
	private String name;
	/**
	 * 父部门ID
	 */
	private Long parentId;

	/**
	 * 创建者ID
	 */
	private Long createUserId;

	/**
	 * 子部门
	 */
	private List<DepartmentDTO> departments = new ArrayList<DepartmentDTO>();

	/**
	 * 部门下员工
	 */
	private List<UserInfoDTO> employees = new ArrayList<UserInfoDTO>();

	/**
	 * 排序时使用
	 */
	private int level = 0;

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public List<DepartmentDTO> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentDTO> departments) {
		this.departments = departments;
	}

	public List<UserInfoDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<UserInfoDTO> employees) {
		this.employees = employees;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
