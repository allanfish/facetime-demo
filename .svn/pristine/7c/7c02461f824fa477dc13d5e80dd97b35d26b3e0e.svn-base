package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.conlect.oatos.dto.autobean.IShareFileRecordDTO;
import com.conlect.oatos.dto.client.ShareFileRecordDTO;

/**
 * 文件操作记录
 * @author yang
 *
 */
public class ShareFileRecord implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 记录ID
	 */
	private long recordId;
	/**
	 * 文件ID
	 */
	private long fileId;
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 操作类型
	 */
	private String recordType;
	/**
	 * 操作时间
	 */
	private Date accessTime;

	/**
	 * 文件夹ID
	 */
	private Long folderId;
	/**
	 * 文件名
	 */
	private String name;

	public ShareFileRecord() {
	}

	public ShareFileRecord(IShareFileRecordDTO recordDTO) {
		recordId = recordDTO.getRecordId();
		fileId = recordDTO.getFileId();
		userId = recordDTO.getUserId();
		recordType = recordDTO.getRecordType();
		accessTime = recordDTO.getAccessTime();
		folderId = recordDTO.getFolderId();
		name = recordDTO.getName();
	}

	public ShareFileRecordDTO toRecordDTO() {
		ShareFileRecordDTO recordDTO = new ShareFileRecordDTO();
		recordDTO.setRecordId(recordId);
		recordDTO.setFileId(fileId);
		recordDTO.setUserId(userId);
		recordDTO.setRecordType(recordType);
		recordDTO.setAccessTime(accessTime);
		recordDTO.setFolderId(folderId);
		recordDTO.setName(name);
		return recordDTO;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
