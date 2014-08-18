package com.conlect.oatos.dto.client.mail;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.mail.IMailAttachDTO;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MailAttachDTO implements IMailAttachDTO {

	private static final long serialVersionUID = 1L;

	private long attachId;
	private long mailId;
	private Long fileId;
	private String contentId;
	private String mimeType;
	private String fileName;
	private Long size;
	private String filePath;

	@Override
	public long getAttachId() {
		return attachId;
	}

	@Override
	public void setAttachId(long attachId) {
		this.attachId = attachId;
	}

	@Override
	public long getMailId() {
		return mailId;
	}

	@Override
	public void setMailId(long mailId) {
		this.mailId = mailId;
	}

	@Override
	public Long getFileId() {
		return fileId;
	}

	@Override
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	@Override
	public String getContentId() {
		return contentId;
	}

	@Override
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	@Override
	public String getMimeType() {
		return mimeType;
	}

	@Override
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Long getSize() {
		return size;
	}

	@Override
	public void setSize(Long size) {
		this.size = size;
	}

	@Override
	public String getFilePath() {
		return filePath;
	}

	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
