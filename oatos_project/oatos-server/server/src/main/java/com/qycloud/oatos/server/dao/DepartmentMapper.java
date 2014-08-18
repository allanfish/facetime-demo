package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.Department;

/**
 * 部门实体层
 * @author yang
 *
 */
public interface DepartmentMapper {

	/**
	 * 新建部门
	 * @param dept
	 */
	void addDepartment(Department dept);

	/**
	 * 修改部门信息
	 * @param dept
	 */
	void updateDepartment(Department dept);

	/**
	 * 删除部门
	 * @param dept
	 */
	void deleteDepartment(Department dept);

	/**
	 * 获取部门信息
	 * @param deptId
	 * @return
	 */
	Department getDepartmentById(long deptId);

	/**
	 * 获取企业部门列表
	 * @param entpriseId
	 * @return
	 */
	List<Department> getDepartmentByEntId(long entpriseId);

	/**
	 * 取相同名称部门
	 * @param dep
	 * @return
	 */
	Department getSameDepartment(Department dep);

	/**
	 * 获取子部门
	 * @param deptId
	 * @return
	 */
	List<Department> getSubDepartmentByDeptId(long deptId);

	Integer getDepartMaxOrderValue(long enterpriseId);
}
