package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.mail.MailFolderDTO;

/**
 * 邮件夹
 * @author yang
 *
 */
public class MailFolder implements Serializable {

	private static final long serialVersionUID = 5798738262243189717L;
	/**
	 * 文件夹ID
	 */
	private long mailFolderId;
	/**
	 * 父文件夹ID
	 */
	private Long parentFolderId;
	/**
	 * 邮箱账号ID
	 */
	private long mailAccountId;
	/**
	 * 文件夹名称
	 */
	private String folderName;
	/**
	 * 邮件数目
	 */
	private int msgCount;
	/**
	 * 邮件夹地址
	 */
	private String folderUrl;
	/**
	 * 未读邮件数目
	 */
	private int unreadMsgCount;

	/**
	 * 最近的抓取邮件的时间
	 */
	private Date latestReceiveDate;

	public MailFolder() {
		super();
	}

	public Date getLatestReceiveDate() {
		return latestReceiveDate;
	}

	public void setLatestReceiveDate(Date latestReceiveDate) {
		this.latestReceiveDate = latestReceiveDate;
	}

	public MailFolder(MailFolderDTO folder) {
		BeanUtils.copyProperties(folder, this);
		this.folderName = folder.getName();
		this.folderUrl = folder.getUrlName();
		this.mailFolderId = folder.getFolderId();
	}

	public MailFolderDTO toDTO() {
		MailFolderDTO folderDTO = new MailFolderDTO();
		BeanUtils.copyProperties(this, folderDTO);
		folderDTO.setUrlName(this.getFolderUrl());
		folderDTO.setFolderId(this.getMailFolderId());
		folderDTO.setName(this.getFolderName());
		folderDTO.setFullName(this.getFolderName());
		return folderDTO;
	}

	public String getFolderUrl() {
		return folderUrl;
	}

	public void setFolderUrl(String folderUrl) {
		this.folderUrl = folderUrl;
	}

	public int getUnreadMsgCount() {
		return unreadMsgCount;
	}

	public void setUnreadMsgCount(int unreadMsgCount) {
		this.unreadMsgCount = unreadMsgCount;
	}

	public int getMsgCount() {
		return msgCount;
	}

	public void setMsgCount(int msgCount) {
		this.msgCount = msgCount;
	}

	public String getFolderName() {
		return folderName;
	}

	public long getMailAccountId() {
		return mailAccountId;
	}

	public long getMailFolderId() {
		return mailFolderId;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public void setMailAccountId(long mailAccountId) {
		this.mailAccountId = mailAccountId;
	}

	public void setMailFolderId(long mailFolderId) {
		this.mailFolderId = mailFolderId;
	}

	public Long getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(Long parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

}
