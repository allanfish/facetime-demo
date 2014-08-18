package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import com.conlect.oatos.dto.client.DepartmentDTO;

/**
 * 部门
 * @author yang
 *
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 部门ID
	 */
	private long departmentId;
	/**
	 * 企业ID
	 */
	private Long enterpriseId;
	/**
	 * 部门名
	 */
	private String name;
	/**
	 * 父部门ID
	 */
	private Long parentId;

	/**
	 * 排序
	 */
	private int orderValue;
	

	public Department() {
	}

	public Department(DepartmentDTO deptDto) {
		this.departmentId = deptDto.getDepartmentId();
		this.enterpriseId = deptDto.getEnterpriseId();
		this.name = deptDto.getName();
		parentId = deptDto.getParentId();
		this.orderValue = deptDto.getLevel();
	}

	public DepartmentDTO toDepartmentDTO() {
		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentId(this.departmentId);
		dto.setEnterpriseId(this.enterpriseId);
		dto.setName(name);
		dto.setParentId(parentId);
		dto.setLevel(orderValue);
		return dto;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
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

	public int getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}

}
