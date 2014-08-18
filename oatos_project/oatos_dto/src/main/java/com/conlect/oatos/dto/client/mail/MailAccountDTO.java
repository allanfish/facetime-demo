package com.conlect.oatos.dto.client.mail;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.mail.IMailAccountDTO;
import com.conlect.oatos.dto.autobean.mail.IMailFolderDTO;

/**
 * 
 * 邮件账号DTO
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailAccountDTO implements IMailAccountDTO {

	private static final long serialVersionUID = 1L;
	private long mailAccountId;
	private long userId;
	private String mailAddress;
	private String password;
	private boolean savePwd;
	/** 账号描述 */
	private String accountDesc;
	/** 发件人姓名 */
	private String senderName;
	private String receiverServer;
	private String receiverPort;
	private String senderServer;
	private String senderPort;
	private String mailProtocol;
	private boolean userSetting;
	private List<IMailFolderDTO> mailFolders = new ArrayList<IMailFolderDTO>();
	private IMailFolderDTO inboxFolder;

	@Override
	public IMailFolderDTO getInboxFolder() {
		return inboxFolder;
	}

	@Override
	public void setInboxFolder(IMailFolderDTO inboxFolder) {
		this.inboxFolder = inboxFolder;
	}

	@Override
	public String getAccountDesc() {
		return accountDesc;
	}

	@Override
	public long getMailAccountId() {
		return mailAccountId;
	}

	@Override
	public String getMailAddress() {
		return mailAddress;
	}

	@Override
	public List<IMailFolderDTO> getMailFolders() {
		return mailFolders;
	}

	@Override
	public String getMailProtocol() {
		return mailProtocol;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getReceiverPort() {
		return receiverPort;
	}

	@Override
	public String getReceiverServer() {
		return receiverServer;
	}

	@Override
	public String getSenderName() {
		return senderName;
	}

	@Override
	public String getSenderPort() {
		return senderPort;
	}

	@Override
	public String getSenderServer() {
		return senderServer;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public boolean isSavePwd() {
		return savePwd;
	}

	@Override
	public boolean isUserSetting() {
		return userSetting;
	}

	@Override
	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	@Override
	public void setMailAccountId(long mailAccountId) {
		this.mailAccountId = mailAccountId;
	}

	@Override
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Override
	public void setMailFolders(List<IMailFolderDTO> mailFolderDTOs) {
		this.mailFolders = mailFolderDTOs;
	}

	@Override
	public void setMailProtocol(String mailProtocol) {
		this.mailProtocol = mailProtocol;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setReceiverPort(String receiverPort) {
		this.receiverPort = receiverPort;
	}

	@Override
	public void setReceiverServer(String receiverServer) {
		this.receiverServer = receiverServer;
	}

	@Override
	public void setSavePwd(boolean savePwd) {
		this.savePwd = savePwd;
	}

	@Override
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	@Override
	public void setSenderPort(String senderPort) {
		this.senderPort = senderPort;
	}

	@Override
	public void setSenderServer(String senderServer) {
		this.senderServer = senderServer;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public void setUserSetting(boolean userSetting) {
		this.userSetting = userSetting;
	}

	@Override
	public String toString() {
		return "MailAccountDTO [mailAccountId=" + mailAccountId + ", userId="
				+ userId + ", mailAddress=" + mailAddress + ", password="
				+ password + ", savePwd=" + savePwd + ", accountDesc="
				+ accountDesc + ", senderName=" + senderName
				+ ", receiverServer=" + receiverServer + ", receiverPort="
				+ receiverPort + ", senderServer=" + senderServer
				+ ", senderPort=" + senderPort + ", mailProtocol="
				+ mailProtocol + ", userSetting=" + userSetting + "]";
	}
}
