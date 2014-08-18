package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.ConferenceDocDTO;

/**
 * 视频会议文档
 * @author yang
 *
 */
public class ConferenceDoc implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件ID
	 */
	private long fileId;
	/**
	 * 会议ID
	 */
	private long conferenceId;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件guid
	 */
	private String guid;
	/**
	 * 文件大小
	 */
	private long size;
	/**
	 * 文件种类
	 */
	private String type;
	/**
	 * 文件上传时间
	 */
	private Date createTime;
	/**
	 * 企业网盘或者个人网盘对应文件ID
	 */
	private Long diskFileId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 企业ID
	 */
	private Long enterpriseId;

	/**
	 * 文档页数
	 */
	private Integer pageCount;

	public ConferenceDoc() {
	}

	public ConferenceDoc(ConferenceDocDTO docDTO) {
		BeanUtils.copyProperties(docDTO, this);
	}

	public ConferenceDocDTO toConferenceDocDTO() {
		ConferenceDocDTO docDTO = new ConferenceDocDTO();
		BeanUtils.copyProperties(this, docDTO);
		return docDTO;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public long getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(long conferenceId) {
		this.conferenceId = conferenceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getDiskFileId() {
		return diskFileId;
	}

	public void setDiskFileId(Long diskFileId) {
		this.diskFileId = diskFileId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
