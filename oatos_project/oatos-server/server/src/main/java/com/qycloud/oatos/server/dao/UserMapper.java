package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.User;

/**
 * 用户实体层
 * @author yang
 *
 */
public interface UserMapper {

	/**
	 * 获取用户信息
	 * 
	 * @param userId
	 * @return
	 */
	User getUser(long userId);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 */
	void updateUser(User user);

	/**
	 * 添加企业用户
	 * 
	 * @param mailUser
	 */
	void addUser(User user);

	/**
	 * 取企业用户列表
	 * @return
	 */
	List<User> getUserByEntId(long entId);

	/**
	 * 取企业的客服成员
	 * @return
	 */
	List<User> getCustomerServiceByEntId(long entId);

	/**
	 * 删除用户
	 * @param userId
	 */
	void deleteUser(long userId);

	/**
	 * 修改用户状态
	 * @param userId
	 * @param status
	 */
	void updateUserStatus(@Param("userId") long userId,
			@Param("status") String status);

	/**
	 * 修改企业网盘容量
	 * @param userId
	 * @param diskSize
	 */
	void updateDiskSize(@Param("userId") long userId,
			@Param("diskSize") long diskSize);

	/**
	 * 取企业用户总数
	 * 
	 * @return
	 */
	long getUserCountByEntId(long entId);

	/**
	 * 按部门和角色取用户
	 * @param entId
	 * @param departmentId
	 * @param roleId
	 * @return
	 */
	List<User> getUserByEntIdAndDeptIdAndRoleId(@Param("entId") long entId,
			@Param("deptId") long departmentId, @Param("roleId") long roleId);

	/**
	 * 去系统中所有用户的ID
	 * @return
	 */
	List<Long> getAllUserId();
	
	/**
	 * 取得某个企业用户所使用网盘总空间大小
	 * @param enterpriseId
	 * @return
	 */
	long getPersonDiskUseSizeSum(long enterpriseId);
	
	
	/**
	 * 取部门下用户
	 * @param deptId
	 * @return
	 */
	List<User> getUserByDeptId(long deptId);

	/**添加设备token
	 * @param userId
	 * @param deviceToeken 
	 */
	void addDeviceTokenByUserId(@Param("userId") long userId, @Param("deviceToken")String deviceToeken);
	
	/**
	 * 通过分组的ID查找用户信息
	 * @param groupId
	 * @return
	 */
	List<User> getUserByGroupId(long groupId);

	/**
	 * 通过用户ID获得分组列表里用户信息的详情
	 * @param userId
	 * @return
	 */
	List<User> getUserInfoListByUserId(long userId);
}
