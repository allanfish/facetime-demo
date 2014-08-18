package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.LoginLog;

/**
 * 登录日志实体层
 * @author yang
 * 
 */
public interface LoginLogMapper {

	/**
	 * 插入登录日志
	 * @param loginLog
	 */
	void insertLoginLog(LoginLog loginLog);

	/**
	 * 获取登录日志
	 * @param loginLog
	 * @return
	 */
	List<LoginLog> getLoginLog(LoginLog loginLog);

}
