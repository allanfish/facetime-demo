package com.conlect.oatos.dto.autobean.mail;

import java.io.Serializable;
import java.util.List;

public interface IMailAccountDTO extends Serializable {

	boolean isUserSetting();

	void setUserSetting(boolean userSetting);

	String getMailProtocol();

	void setMailProtocol(String mailProtocol);

	boolean isSavePwd();

	void setSavePwd(boolean savePwd);

	String getAccountDesc();

	void setAccountDesc(String accountDesc);

	String getSenderName();

	void setSenderName(String senderName);

	String getReceiverServer();

	void setReceiverServer(String receiverServer);

	String getReceiverPort();

	void setReceiverPort(String receiverPort);

	String getSenderServer();

	void setSenderServer(String senderServer);

	String getSenderPort();

	void setSenderPort(String senderPort);

	long getMailAccountId();

	String getMailAddress();

	String getPassword();

	long getUserId();

	void setMailAccountId(long mailAccountId);

	void setMailAddress(String mailAddress);

	void setPassword(String password);

	void setUserId(long userId);

	void setMailFolders(List<IMailFolderDTO> mailFolderDTOs);

	List<IMailFolderDTO> getMailFolders();

	public abstract void setInboxFolder(IMailFolderDTO inboxFolder);

	public abstract IMailFolderDTO getInboxFolder();

}