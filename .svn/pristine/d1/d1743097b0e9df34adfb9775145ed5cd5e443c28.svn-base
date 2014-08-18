package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 视频会议
 * 
 * @author yang
 * 
 */
public class ConferenceDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 会议id
	 */
	private long id;
	/**
	 * 主题
	 */
	private String theme;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 时长，单位分钟
	 */
	private int length;
	/**
	 * 会议状态<br> {@link com.conlect.oatos.dto.status.ConferenceStatus}
	 */
	private String status;
	/**
	 * 会议创建者ID
	 */
	private long createrId;
	/**
	 * 会议创建者
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
	 * 企业id
	 */
	private long enterpriseId;

	/**
	 * 会议类型<br> {@link com.conlect.oatos.dto.status.ConferenceType}
	 */
	private int type;

	/**
	 * 参会成员
	 */
	private List<ConferenceMemberDTO> members = new ArrayList<ConferenceMemberDTO>();

	public ConferenceDTO() {
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

	public List<ConferenceMemberDTO> getMembers() {
		return members;
	}

	public void setMembers(List<ConferenceMemberDTO> members) {
		this.members = members;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public boolean isRecord() {
		return record;
	}

	public void setRecord(boolean record) {
		this.record = record;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
