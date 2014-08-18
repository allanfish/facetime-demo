package com.conlect.oatos.dto.client.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.mail.IMailQueryDTO;

/**
 * 查询邮箱文件夹下邮件的DTO
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailQueryDTO implements IMailQueryDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * 邮件帐户ID
	 */
	private long mailAccountId;
	/**
	 * 邮件ID
	 */
	private long mailId;
	/**
	 * 附件ID
	 */
	private Long attachId;
	/**
	 * 附件名
	 */
	private String attachFileName;
	/**
	 * 附件的guid
	 */
	private String attachFileGuid;
	/**
	 * 文件夹url
	 */
	private String folderurl;
	/**
	 * 信息id
	 */
	private String messageId;
	/**
	 * 开始
	 */
	private Integer beginIndex = 0;
	/**
	 * 最大尺寸
	 */
	private Integer maxSize = 0;
	/**
	 * 是否已读
	 */
	private boolean haveRead = false;
	/**
	 * 文件夹id
	 */
	private long folderId;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 信息的列表
	 */
	private List<String> messageIds = new ArrayList<String>(0);
	/**
	 * 邮件ID列表
	 */
	private List<Long> mailIds = new ArrayList<Long>(0);
	/**
	 * 主题列表
	 */
	private List<String> subjects = new ArrayList<String>(0);
	/**
	 * 最后接受时间
	 */
	private Date latestReceiveDate;
	/**
	 * 最小发送时间
	 */
	private Date minSentDate;
	/**
	 * 最大发送时间
	 */
	private Date maxSentDate;
	/**
	 * 是否第一次加载
	 */
	private boolean isFirstTime;

	@Override
	public boolean isFirstTime() {
		return isFirstTime;
	}

	@Override
	public void setFirstTime(boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}

	@Override
	public Date getMinSentDate() {
		return minSentDate;
	}

	@Override
	public void setMinSentDate(Date minSentDate) {
		this.minSentDate = minSentDate;
	}

	@Override
	public Date getMaxSentDate() {
		return maxSentDate;
	}

	@Override
	public void setMaxSentDate(Date maxSentDate) {
		this.maxSentDate = maxSentDate;
	}

	@Override
	public Date getLatestReceiveDate() {
		return latestReceiveDate;
	}

	@Override
	public void setLatestReceiveDate(Date latestReceiveDate) {
		this.latestReceiveDate = latestReceiveDate;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public List<String> getSubjects() {
		return subjects;
	}

	@Override
	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@Override
	public List<Long> getMailIds() {
		return mailIds;
	}

	@Override
	public void setMailIds(List<Long> mailIds) {
		this.mailIds = mailIds;
	}

	@Override
	public long getFolderId() {
		return folderId;
	}

	@Override
	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	@Override
	public Long getAttachId() {
		return attachId;
	}

	@Override
	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	@Override
	public boolean isHaveRead() {
		return haveRead;
	}

	@Override
	public void setHaveRead(boolean haveRead) {
		this.haveRead = haveRead;
	}

	@Override
	public String getAttachFileGuid() {
		return attachFileGuid;
	}

	@Override
	public String getAttachFileName() {
		return attachFileName;
	}

	@Override
	public Integer getBeginIndex() {
		return beginIndex;
	}

	@Override
	public String getFolderurl() {
		return folderurl;
	}

	@Override
	public long getMailAccountId() {
		return mailAccountId;
	}

	@Override
	public long getMailId() {
		return mailId;
	}

	@Override
	public Integer getMaxSize() {
		return maxSize;
	}

	@Override
	public String getMessageId() {
		return messageId;
	}

	@Override
	public void setAttachFileGuid(String attachFileGuid) {
		this.attachFileGuid = attachFileGuid;
	}

	@Override
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}

	@Override
	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	@Override
	public void setFolderurl(String folderurl) {
		this.folderurl = folderurl;
	}

	@Override
	public void setMailAccountId(long mailAccountId) {
		this.mailAccountId = mailAccountId;
	}

	@Override
	public void setMailId(long mailId) {
		this.mailId = mailId;
	}

	@Override
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Override
	public List<String> getMessageIds() {
		return messageIds;
	}

	@Override
	public void setMessageIds(List<String> messageIds) {
		this.messageIds = messageIds;
	}
}
