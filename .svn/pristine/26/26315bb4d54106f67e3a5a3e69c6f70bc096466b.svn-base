package com.conlect.oatos.dto.autobean.mail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IMailFolderDTO extends Serializable {

	public Long getParentFolderId();

	public void setParentFolderId(Long parentFolderId);

	public String getParentFolderUrl();

	public void setParentFolderUrl(String parentFolderUrl);

	String getFullName();

	long getMailAccountId();

	Integer getMsgCount();

	String getName();

	int getUnreadMsgCount();

	String getUrlName();

	void setFullName(String fullName);

	void setMailAccountId(long mailAcccountId);

	void setName(String name);

	void setUnreadMsgCount(int unreadMsgCount);

	void setUrlName(String urlName);

	void setMsgCount(int msgCount);

	void setMailList(List<IMailDTO> mailList);

	List<IMailDTO> getMailList();

	void setChildren(List<IMailFolderDTO> children);

	List<IMailFolderDTO> getChildren();

	public long getFolderId();

	public void setFolderId(long folderId);

	public Date getLatestReceiveDate();

	public void setLatestReceiveDate(Date latestReceiveDate);
}