/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qycloud.oatos.server.logic.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import com.qycloud.oatos.server.util.ConfigUtil;
import com.qycloud.oatos.server.util.MyConstants;

/**
 * 
 * @author 郭天良
 */
public class MailAuthenticator extends Authenticator
{
	// 系统邮件的用户名和密码
	private String user;
	private String password;

	private static int index = 0;

	public MailAuthenticator(String user, String password)
	{
		//配置发送邮件的账号
		this.setUser(user);
		this.setPassword(password);
	}
	
	/**
	 * get new Authenticator
	 * @return
	 */
	public static synchronized MailAuthenticator getNewAuthenticator() {
		MailAuthenticator auth = null;

		String pwd = ConfigUtil.getValue(MyConstants.SysMailPassword);
		String str = ConfigUtil.getValue(MyConstants.SysMailUser);

		String[] users = str.split(",");
		if (index < users.length) {
	        auth = new MailAuthenticator(users[index], pwd);
	        index = index + 1;
        } else {
			index = 0;
			auth = new MailAuthenticator(users[index], pwd);
		}
		return auth;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(user, password);
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getUser()
	{
		return user;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}
}
