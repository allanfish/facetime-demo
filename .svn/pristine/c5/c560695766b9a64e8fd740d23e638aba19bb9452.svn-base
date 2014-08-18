package com.conlect.oatos.dto.client.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.client.BaseDTO;

/**
 * 邮件结构
 * 
 * @author PeterGuo
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;
	/**
	 * 系统邮件ID
	 */
	private long mailId;
	/**
	 * 邮件文件夹ID
	 */
	private long folderId;
	/**
	 * 邮件文件夹url
	 */
	private String folderUrl;
	/**
	 * 邮件系统邮件ID
	 */
	private long mailAccountId;
	/**
	 * 消息ID
	 */
	private String messageId;
	/**
	 * 消息数目
	 */
	private int messageNum;
	/**
	 * 邮件主题
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
	/**
	 * 邮件附件名称
	 */
	private List<MailAttachDTO> attachs = new ArrayList<MailAttachDTO>();

	/**
	 * 回复类型
	 */
	private int replyType;

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public int getReplyType() {
		return replyType;
	}

	public void setReplyType(int replyType) {
		this.replyType = replyType;
	}

	public String getFolderUrl() {
		return folderUrl;
	}

	public void setFolderUrl(String folderUrl) {
		this.folderUrl = folderUrl;
	}

	public int getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}

	public MailDTO() {
		super();
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

	public void printSimpleInfo() {
		System.out.println("------------------START-----------------------");
		System.out.println("Message" + " messageId:" + messageId);
		System.out.println("Message" + " messageNum:" + messageNum);
		System.out.println("Message" + " sender:" + sender);
		System.out.println("Message" + " subject:" + subject);
		System.out.println("Message" + " isRead:" + isHaveRead());
		if (sendDate != null)
			System.out.println("Message" + " receivedDate:" + sendDate);
		System.out.println("Message" + " isContainAttch:" + isHaveAttach());
		System.out.println("------------------END-----------------------");
	}

	public void printInfo() {
		System.out.println("------------------START-----------------------");
		System.out.println("Message" + " messageId:" + messageId);
		System.out.println("Message" + " messageNum:" + messageNum);
		System.out.println("Message" + " subject:" + getSubject());
		System.out.println("Message" + " from:" + getSender());
		System.out.println("Message" + " to:" + getRecieve());
		System.out.println("Message" + " isNew:" + isHaveRead());
		System.out.println("Message" + " isContainAttch:" + isHaveRead());
		System.out.println("Message" + " content: ignored");
		if (haveAttach)
			System.out.println("Message" + " attachFile:" + attachs.size());
		System.out.println("------------------END-----------------------");
	}

	@Override
	public String toString() {
		return "MailDTO [mailId=" + mailId + ", folderUrl=" + folderUrl
				+ ", mailAccountId=" + mailAccountId + ", messageId="
				+ messageId + ", messageNum=" + messageNum + ", subject="
				+ subject + ", sender=" + sender + ", recieve=" + recieve
				+ ", cc=" + cc + ", bcc=" + bcc + ", content=" + content
				+ ", sendDate=" + sendDate + ", size=" + size + ", haveAttach="
				+ haveAttach + ", haveRead=" + haveRead + ", attachFileNames="
				+ attachs + ", attachs=" + attachs + ", replyType=" + replyType
				+ "]";
	}

	public void setAttachs(List<MailAttachDTO> attachDTOs) {
		this.attachs = attachDTOs;
	}

	public List<MailAttachDTO> getAttachs() {
		return attachs;
	}
}
