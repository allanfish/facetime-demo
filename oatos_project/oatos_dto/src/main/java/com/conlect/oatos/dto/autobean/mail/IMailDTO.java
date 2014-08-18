package com.conlect.oatos.dto.autobean.mail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IMailDTO extends Serializable {

	/**
	 * 快捷回复
	 */
	int FAST_REPLY = 0;
	/**
	 * 正常回复
	 */
	int NORMAL_REPLY = 1;
	/**
	 * 全部回复
	 */
	int ALL_REPLY = 2;

	public long getFolderId();

	public void setFolderId(long folderId);

	boolean isHaveRead();

	void setHaveRead(boolean haveRead);

	String getBcc();

	String getCc();

	String getContent();

	long getMailAccountId();

	long getMailId();

	String getMessageId();

	String getRecieve();

	Date getSendDate();

	String getSender();

	long getSize();

	String getSubject();

	boolean isHaveAttach();

	void setBcc(String bcc);

	void setCc(String cc);

	void setContent(String content);

	void setHaveAttach(boolean haveAttach);

	void setMailAccountId(long mailAccountId);

	void setMailId(long mailId);

	void setMessageId(String messageId);

	void setRecieve(String recieve);

	void setSendDate(Date sendDate);

	void setSender(String sender);

	void setSize(long size);

	void setSubject(String subject);

	void setFolderUrl(String folderUrl);

	String getFolderUrl();

	void setReplyType(int replyType);

	int getReplyType();

	void setMessageNum(int messageNum);

	int getMessageNum();

	void setAttachs(List<IMailAttachDTO> attachDTOs);

	List<IMailAttachDTO> getAttachs();
}