package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.autobean.mail.IMailAttachDTO;
import com.conlect.oatos.dto.client.mail.MailAttachDTO;

/**
 * 邮件附件信息
 * @author yang
 */
public class MailAttach implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 附件ID
	 */
	private long attachId;
	/**
	 * 邮件ID
	 */
	private long mailId;
	/**
	 * 网盘系统文件ID
	 */
	private Long fileId;
	/**
	 * 内容ID
	 */
	private String contentId;
	/**
	 * mime type
	 */
	private String mimeType;
	/**
	 * 附件名
	 */
	private String fileName;
	/**
	 * 附件大小
	 */
	private Long size;

	public MailAttach() {
		super();
	}

	public MailAttach(IMailAttachDTO dto) {
		BeanUtils.copyProperties(dto, this);
	}

	public long getAttachId() {
		return attachId;
	}

	public void setAttachId(long attachId) {
		this.attachId = attachId;
	}

	public long getMailId() {
		return mailId;
	}

	public void setMailId(long mailId) {
		this.mailId = mailId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimetype) {
		this.mimeType = mimetype;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public MailAttachDTO toDTO() {
		MailAttachDTO dto = new MailAttachDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}
}
