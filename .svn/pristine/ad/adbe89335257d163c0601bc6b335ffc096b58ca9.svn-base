package com.qycloud.oatos.server.dao;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.Login;

/**
 * 登录实体层
 * @author yang
 * 
 */
public interface LoginMapper {

	/**
	 * 添加登录信息
	 * @param login
	 */
	void addLogin(Login login);

	/**
	 * 根据企业名和用户帐号查询用户登录信息
	 * @param entName
	 * @param account
	 * @return
	 */
	Login getLoginByAccount(@Param("entName") String entName,
			@Param("account") String account);

	/**
	 * 修改登录密码
	 * 
	 * @param login
	 */
	void updatePassword(Login login);

	/**
	 * 修改登录帐号
	 * 
	 * @param login
	 */
	void updateAccount(Login login);

	/**
	 * 修改登录帐号和密码
	 * 
	 * @param login
	 * @return
	 */
	void updateAccountAndPassword(Login login);

	/**
	 * 根据用户ID获取用户登录信息
	 * @param userId
	 * @return
	 */
	Login getLoginByUserId(long userId);
}
