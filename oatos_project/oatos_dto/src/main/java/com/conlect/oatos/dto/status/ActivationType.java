/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conlect.oatos.dto.status;

/**
 * 激活类型
 * 
 * @author PeterGuo
 */
public interface ActivationType {
	// 注册中
	String Register = "atre";
	// 被邀请中
	String FriendInvite = "atfi";
	// 忘记密码中
	String ForgotPassword = "atfp";
	// 已经激活
	String Activate = "Activate";
	// 登录
	String Login = "Login";
}
