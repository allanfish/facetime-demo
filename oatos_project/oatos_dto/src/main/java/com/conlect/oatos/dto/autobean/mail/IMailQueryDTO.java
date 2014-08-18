package com.conlect.oatos.dto.autobean.mail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IMailQueryDTO extends Serializable {

	String getAttachFileGuid();

	void setAttachFileGuid(String attachFileGuid);

	String getAttachFileName();

	void setAttachFileName(String attachFileName);

	long getMailId();

	void setMailId(long mailId);

	String getMessageId();

	void setMessageId(String messageId);

	long getMailAccountId();

	void setMailAccountId(long mailAccountId);

	Integer getBeginIndex();

	Integer getMaxSize();

	void setBeginIndex(Integer beginIndex);

	void setMaxSize(Integer maxSize);

	String getFolderurl();

	void setFolderurl(String folderurl);

	void setMessageIds(List<String> messageIds);

	List<String> getMessageIds();

	void setHaveRead(boolean haveRead);

	boolean isHaveRead();

	void setAttachId(Long attachId);

	Long getAttachId();

	public abstract long getFolderId();

	public abstract void setFolderId(long folderId);

	public abstract void setMailIds(List<Long> mailIds);

	public abstract List<Long> getMailIds();

	public abstract void setSubjects(List<String> subjects);

	public abstract List<String> getSubjects();

	public abstract void setSubject(String subject);

	public abstract String getSubject();

	public abstract void setLatestReceiveDate(Date latestReceiveDate);

	public abstract Date getLatestReceiveDate();

	public abstract void setMaxSentDate(Date maxSentDate);

	public abstract Date getMaxSentDate();

	public abstract void setMinSentDate(Date minSentDate);

	public abstract Date getMinSentDate();

	public boolean isFirstTime();

	public void setFirstTime(boolean isFirstTime);

}