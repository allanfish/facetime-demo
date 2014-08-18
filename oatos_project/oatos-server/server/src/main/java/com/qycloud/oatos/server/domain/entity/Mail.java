package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.mail.MailDTO;

/**
 * 邮件
 * @author yang
 *
 */
public class Mail implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 系统邮件ID
	 */
	private long mailId;
	/**
	 * 邮件文件夹ID
	 */
	private long mailFolderId;
	/**
	 * 邮件账号ID
	 */
	private long mailAccountId;
	/**
	 * 邮件系统邮件ID
	 */
	private String messageId;
	/**
	 * 邮件数目
	 */
	private int messageNum;
	/**
	 * 邮件标题
	 */
	private String subject;
	/**
	 * 邮件发送方
	 */
	private String sender;
	/**
	 * 邮件接收方，多地址以“；”间隔
	 */
	private String recieve;
	/**
	 * 邮件附送拷贝，多地址以“；”间隔
	 */
	private String cc;
	/**
	 * 邮件密件附送拷贝，多地址以“；”间隔
	 */
	private String bcc;
	/**
	 * 邮件正文
	 */
	private String content;
	/**
	 * 邮件发送日期
	 */
	private Date sendDate;
	/**
	 * 邮件大小,单位：bytes
	 */
	private long size;
	/**
	 * 是否有附件
	 */
	private boolean haveAttach;
	/**
	 * 是否已读
	 */
	private boolean haveRead;

	public Mail() {
		super();
	}

	public int getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}

	public Mail(MailDTO mailDTO) {
		BeanUtils.copyProperties(mailDTO, this);
		this.mailFolderId = mailDTO.getFolderId();
		this.haveRead = mailDTO.isHaveRead();
		this.haveAttach = mailDTO.isHaveAttach();
	}

	public boolean isHaveRead() {
		return haveRead;
	}

	public void setHaveRead(boolean haveRead) {
		this.haveRead = haveRead;
	}

	public String getBcc() {
		return bcc;
	}

	public String getCc() {
		return cc;
	}

	public String getContent() {
		return content;
	}

	public long getMailAccountId() {
		return mailAccountId;
	}

	public long getMailFolderId() {
		return mailFolderId;
	}

	public long getMailId() {
		return mailId;
	}

	public String getMessageId() {
		return messageId;
	}

	public String getRecieve() {
		return recieve;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public String getSender() {
		return sender;
	}

	public long getSize() {
		return size;
	}

	public String getSubject() {
		return subject;
	}

	public boolean isHaveAttach() {
		return haveAttach;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setHaveAttach(boolean haveAttach) {
		this.haveAttach = haveAttach;
	}

	public void setMailAccountId(long mailAccountId) {
		this.mailAccountId = mailAccountId;
	}

	public void setMailFolderId(long mailFolderId) {
		this.mailFolderId = mailFolderId;
	}

	public void setMailId(long mailId) {
		this.mailId = mailId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public void setRecieve(String recieve) {
		this.recieve = recieve;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public MailDTO toDTO() {
		MailDTO mail = new MailDTO();
		BeanUtils.copyProperties(this, mail);
		mail.setFolderId(this.getMailFolderId());
		return mail;
	}
}
