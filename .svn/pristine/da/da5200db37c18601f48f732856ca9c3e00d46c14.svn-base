package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.Admin;

/**
 * 管理员实体层
 * 
 * @author yang
 * 
 */
public interface AdminMapper {

	/**
	 * 添加二级管理员
	 * 
	 * @param admins
	 */
	void addAdmins(List<Admin> admins);

	/**
	 * 删除二级管理员
	 * 
	 * @param admin
	 */
	void deleteAdmin(Admin admin);

	/**
	 * 更新二级管理员
	 * 
	 * @param admin
	 */
	void updateAdmin(Admin admin);

	/**
	 * 获取企业的二级管理员
	 * 
	 * @param entId
	 * @return
	 */
	List<Admin> getAdminsByEntId(long entId);

	/**
	 * 根据用户获取二级管理员信息
	 * 
	 * @param userId
	 * @return
	 */
	Admin getAdminByUserId(long userId);

	/**
	 * 修改用户类型
	 * 
	 * @param userId
	 * @param userType
	 */
	void updateUserType(@Param("userId") long userId,
			@Param("userType") int userType);

}
