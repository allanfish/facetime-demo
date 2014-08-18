package com.conlect.oatos.dto.client;

import java.util.Date;

import com.conlect.oatos.dto.autobean.IConferenceDocDTO;
import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.IShareFileDTO;
import com.conlect.oatos.dto.status.CommConstants;

/**
 * 视频会议文档
 * 
 * @author yang
 * 
 */
public class ConferenceDocDTO implements IConferenceDocDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	private long fileId;
	/**
	 * 会议Id
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
	 * 大小，单位KB
	 */
	private long size;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 上传时间
	 */
	private Date createTime;
	/**
	 * 网盘文件id，企业网盘或个人网盘文件需要
	 */
	private Long diskFileId;
	/**
	 * 用户ID，个人网盘文件需要
	 */
	private Long userId;
	/**
	 * 企业id，企业网盘文件需要
	 */
	private Long enterpriseId;
	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	/**
	 * 文件页数
	 */
	private Integer pageCount;

	public ConferenceDocDTO() {
	}

	public ConferenceDocDTO(IShareFileDTO f) {
		name = f.getName();
		guid = f.getGuid();
		size = f.getSize();
		type = CommConstants.FILE_TYPE_SHAREDISK;
		createTime = f.getCreateTime();
		diskFileId = f.getFileId();
		enterpriseId = f.getEnterpriseId();
		pageCount = f.getPageCount();
		cloudDiskIp = f.getCloudDiskIp();
	}

	public ConferenceDocDTO(INetworkFileDTO f) {
		name = f.getName();
		guid = f.getGuid();
		size = f.getSize();
		type = CommConstants.FILE_TYPE_ONLINEDISK;
		createTime = f.getCreateTime();
		diskFileId = f.getFileId();
		userId = f.getUserId();
		pageCount = f.getPageCount();
		cloudDiskIp = f.getCloudDiskIp();
	}

	@Override
	public long getFileId() {
		return fileId;
	}

	@Override
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	@Override
	public long getConferenceId() {
		return conferenceId;
	}

	@Override
	public void setConferenceId(long conferenceId) {
		this.conferenceId = conferenceId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getGuid() {
		return guid;
	}

	@Override
	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public long getSize() {
		return size;
	}

	@Override
	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public Long getDiskFileId() {
		return diskFileId;
	}

	@Override
	public void setDiskFileId(Long diskFileId) {
		this.diskFileId = diskFileId;
	}

	@Override
	public Long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	@Override
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}

	@Override
	public Integer getPageCount() {
		return pageCount;
	}

	@Override
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
