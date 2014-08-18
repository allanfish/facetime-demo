package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.conlect.oatos.dto.client.ConferenceDTO;

/**
 * 视频会议
 * @author yang
 *
 */
public class Conference implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 会议ID
	 */
	private long id;
	/**
	 * 会议主题
	 */
	private String theme;
	/**
	 * 会议内容
	 */
	private String content;
	/**
	 * 会议开始时间
	 */
	private Date startTime;
	/**
	 * 会议时长，单位：分钟
	 */
	private int length;
	/**
	 * 会议状态<br>
	 * {@link com.conlect.oatos.dto.status.ConferenceStatus}
	 */
	private String status;
	/**
	 * 会议创建者ID
	 */
	private long createrId;
	/**
	 * 会议创建者姓名
	 */
	private String createrName;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 是否录制
	 */
	private boolean record;

	/**
	 * 企业ID
	 */
	private long enterpriseId;

	/**
	 * 会议类型<br>
	 * {@link com.conlect.oatos.dto.status.ConferenceType}
	 */
	private int type;

	public Conference() {
	}

	public Conference(ConferenceDTO conferenceDTO) {
		id = conferenceDTO.getId();
		theme = conferenceDTO.getTheme();
		content = conferenceDTO.getContent();
		startTime = conferenceDTO.getStartTime();
		length = conferenceDTO.getLength();
		status = conferenceDTO.getStatus();
		createrId = conferenceDTO.getCreaterId();
		createrName = conferenceDTO.getCreaterName();
		createTime = conferenceDTO.getCreateTime();
		enterpriseId = conferenceDTO.getEnterpriseId();
		record = conferenceDTO.isRecord();
		type = conferenceDTO.getType();
	}

	public ConferenceDTO toConferenceDTO() {
		ConferenceDTO conferenceDTO = new ConferenceDTO();
		conferenceDTO.setId(id);
		conferenceDTO.setTheme(theme);
		conferenceDTO.setContent(content);
		conferenceDTO.setStartTime(startTime);
		conferenceDTO.setLength(length);
		conferenceDTO.setStatus(status);
		conferenceDTO.setCreaterId(createrId);
		conferenceDTO.setCreaterName(createrName);
		conferenceDTO.setCreateTime(createTime);
		conferenceDTO.setEnterpriseId(enterpriseId);
		conferenceDTO.setRecord(record);
		conferenceDTO.setType(type);
		return conferenceDTO;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(long createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isRecord() {
    	return record;
    }

	public void setRecord(boolean record) {
    	this.record = record;
    }

	public long getEnterpriseId() {
    	return enterpriseId;
    }

	public void setEnterpriseId(long enterpriseId) {
    	this.enterpriseId = enterpriseId;
    }

	public int getType() {
    	return type;
    }

	public void setType(int type) {
    	this.type = type;
    }

}
