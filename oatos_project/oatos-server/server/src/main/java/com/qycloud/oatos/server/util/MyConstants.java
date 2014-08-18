/*
 * @(#)CommonUser.java   10/09/08
 *
 * Copyright (c) 2005 your company name
 *
 * License agreement text here ...
 *
 *
 *
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qycloud.oatos.server.util;



/**
 * 类名：常量定义 内容： 完成日期：20100906
 * 
 * @author 秦利军
 */
public interface MyConstants
{
	//
	String ServiceContentType = "text/plain;charset=UTF-8";

	// 系统邮件地址
	String SysMailSendHost = "SysMailSendHost";
	String SysMailUser = "SysMailUser";
	String SysMailPassword = "SysMailPassword";

	// web url key
	String WebUrl = "WebUrl";

	// offline file max
	String OfflineFileMax = "OfflineFileMax";

	// Maximum number of users
	String MaxUserNo = "MaxUserNo";


	// 邮件发送匹配字符
	String RigisterSubject = "燕麦OS邮箱验证";
	String BuddyInviteSubject = "燕麦OS好友邀请";
	String RetrievePasswordSubject = "燕麦OS重设密码";
	String WebUrlPath = "#{webUrl}";
	String LinkHref = "#{LinkHref}";	
	String BuddyName = "#{BuddyName}";
	String InviteMsg = "#{inviteMsg}";
	String VerificationCode = "#{VerificationCode}";
	
	String Name = "#{Name}";
	String Email = "#{Email}";
	
	String EnterpriseSubject = "感谢您注册OATOS";
	String EnterpriseSubjectEn = "Thank you for registering OATOS";
	String enterpriseName = "#{enterpriseName}";
	String Administrator = "#{Administrator}";
	String userName = "#{userName}";
	String homePageMailKey = "#{homePage}";
	String appLogoMailKey = "#{appLogo}";
	String supportEmailMailKey = "#{supportEmail}";
	String appPageMailKey = "#{appPage}";

}
