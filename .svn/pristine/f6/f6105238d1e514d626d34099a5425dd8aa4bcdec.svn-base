package com.conlect.oatos.dto.client.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

/**
 * 用户自定义的邮件文件夹
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailFolderDTO implements BaseDTO {
	private static final long serialVersionUID = 1L;
	/**
	 * 文件夹ID
	 */
	private long folderId;
	/**
	 * 邮件帐户ID
	 */
	private long mailAccountId;

	/**
	 * 父文件夹ID
	 */
	private Long parentFolderId;
	/**
	 * 父文件夹url
	 */
	private String parentFolderUrl;
	/**
	 * 邮件名字
	 */
	private String name;
	/**
	 * 邮件全名
	 */
	private String fullName;
	/**
	 * 邮件url名字
	 */
	private String urlName;
	/**
	 * 未读消息个数
	 */
	private int unreadMsgCount = 0;
	/**
	 * 消息的字数
	 */
	private int msgCount = 0;
	/**
	 * 邮件文件夹DTO列表
	 */
	private List<MailFolderDTO> children = new ArrayList<MailFolderDTO>();
	/**
	 * 邮件DTO列表
	 */
	private List<MailDTO> mailList = new ArrayList<MailDTO>();
	/**
	 * 最新接受日期
	 */
	private Date latestReceiveDate;

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public long getMailAccountId() {
		return mailAccountId;
	}

	public void setMailAccountId(long mailAccountId) {
		this.mailAccountId = mailAccountId;
	}

	public List<MailFolderDTO> getChildren() {
		return children;
	}

	public void setChildren(List<MailFolderDTO> children) {
		this.children = children;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getLatestReceiveDate() {
		return latestReceiveDate;
	}

	public void setLatestReceiveDate(Date latestReceiveDate) {
		this.latestReceiveDate = latestReceiveDate;
	}

	public List<MailDTO> getMailList() {
		return mailList;
	}

	public void setMailList(List<MailDTO> mailList) {
		this.mailList = mailList;
	}

	public int getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(Long parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public String getParentFolderUrl() {
		return parentFolderUrl;
	}

	public void setParentFolderUrl(String parentFolderUrl) {
		this.parentFolderUrl = parentFolderUrl;
	}

	public int getUnreadMsgCount() {
		return unreadMsgCount;
	}

	public void setUnreadMsgCount(int unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

}
