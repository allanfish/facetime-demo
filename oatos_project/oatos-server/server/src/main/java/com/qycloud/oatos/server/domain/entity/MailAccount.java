package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.client.mail.MailAccountDTO;

/**
 * 邮件账号实体
 * 
 * @author yang
 * 
 */
public class MailAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 帐号ID
	 */
	private long mailAccountId;
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 邮箱地址
	 */
	private String mailAddress;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否保存密码
	 */
	private boolean savePwd;
	/**
	 * 账号描述
	 */
	private String accountDesc;
	/**
	 * 发件人姓名
	 */
	private String senderName;
	/**
	 * 收件服务器
	 */
	private String receiverServer;
	/**
	 * 收件服务器端口
	 */
	private String receiverPort;
	/**
	 * 发送服务器
	 */
	private String senderServer;
	/**
	 * 发送服务器端口
	 */
	private String senderPort;
	/**
	 * 邮件协议
	 */
	private String mailProtocol;
	/**
	 * 用户设置
	 */
	private boolean userSetting;

	public MailAccount() {
	}

	public MailAccount(IMailAccountDTO mailAcc) {
		BeanUtils.copyProperties(mailAcc, this);
	}

	public boolean isUserSetting() {
		return userSetting;
	}

	public void setUserSetting(boolean userSetting) {
		this.userSetting = userSetting;
	}

	public MailAccount(long mailAccountId, long userId, String mailAddress,
			String password) {
		super();
		this.mailAccountId = mailAccountId;
		this.userId = userId;
		this.mailAddress = mailAddress;
		this.password = password;
	}

	public String getMailProtocol() {
		return mailProtocol;
	}

	public void setMailProtocol(String mailProtocol) {
		this.mailProtocol = mailProtocol;
	}

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public boolean isSavePwd() {
		return savePwd;
	}

	public void setSavePwd(boolean savePwd) {
		this.savePwd = savePwd;
	}

	public String getReceiverServer() {
		return receiverServer;
	}

	public void setReceiverServer(String receiverServer) {
		this.receiverServer = receiverServer;
	}

	public String getReceiverPort() {
		return receiverPort;
	}

	public void setReceiverPort(String receiverPort) {
		this.receiverPort = receiverPort;
	}

	public String getSenderServer() {
		return senderServer;
	}

	public void setSenderServer(String senderServer) {
		this.senderServer = senderServer;
	}

	public String getSenderPort() {
		return senderPort;
	}

	public void setSenderPort(String senderPort) {
		this.senderPort = senderPort;
	}

	public long getMailAccountId() {
		return mailAccountId;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public long getUserId() {
		return userId;
	}

	public void setMailAccountId(long mailAccountId) {
		this.mailAccountId = mailAccountId;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public MailAccountDTO toDTO() {
		MailAccountDTO mailAcc = new MailAccountDTO();
		BeanUtils.copyProperties(this, mailAcc);
		return mailAcc;
	}
}
