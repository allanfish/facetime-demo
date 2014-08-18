/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.conlect.oatos.dto.status;

/**
 * 用户状态
 * 
 * @author huhao
 * 
 */
public interface UserStatus {
	/**
	 * 在线
	 */
	String Online = "online";

	/**
	 * 不在线
	 */
	String Offline = "offline";

	/**
	 * 忙碌
	 */
	String Busy = "busy";

	/**
	 * 隐身
	 */
	String Corbet = "corbet";

	/**
	 * 离开
	 */
	String Leave = "leave";

	/**
	 * 退出
	 */
	String Logout = "logout";
}
