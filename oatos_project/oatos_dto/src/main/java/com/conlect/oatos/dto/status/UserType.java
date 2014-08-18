package com.conlect.oatos.dto.status;

/**
 * user type
 * 
 * @author yang
 * 
 */
public interface UserType {
	/**
	 * 个人用户
	 */
	int PersonalUser = 0;

	/**
	 * 企业用户
	 */
	int BusinessUser = 1;

	/**
	 * 企业管理员
	 */
	int Administrator = 2;

	/**
	 * 企业二级管理员
	 */
	int SecondAdministrator = 3;
}
