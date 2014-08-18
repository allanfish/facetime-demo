package com.conlect.oatos.dto.client;

import java.util.Date;

import com.conlect.oatos.dto.autobean.IFileDTO;

/**
 * 文件 dto
 * 
 * @author yang
 * 
 */
public class FileDTO implements IFileDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件 id
	 */
	private Long fileId;

	/**
	 * 企业id
	 */
	private long enterpriseId;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 文件夹 id
	 */
	private Long folderId;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件guid
	 */
	private String guid;
	/**
	 * type
	 */
	private String type;

	/**
	 * 文件大小，单位KB
	 */
	private long size;
	/**
	 * 上传时间
	 */
	private Date createTime;
	/**
	 * 最后修改时间
	 */
	private Date updateTime;

	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	/**
	 * 删除状态<br> {@link com.conlect.oatos.dto.status.FileStatus}
	 */
	private int deleted;

	/**
	 * 封面
	 */
	private String thumb;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 文件版本号
	 */
	private long version;

	/**
	 * 文件页数
	 */
	private Integer pageCount;

	public FileDTO() {
	}

	public FileDTO(Long folderId, String name, String guid) {
		this.folderId = folderId;
		this.name = name;
		this.guid = guid;
	}

	public FileDTO(Long fileId, Long folderId, String name, String guid) {
		this.fileId = fileId;
		this.folderId = folderId;
		this.name = name;
		this.guid = guid;
	}

	public FileDTO(IFileDTO fileDTO) {
		fileId = fileDTO.getFileId();
		enterpriseId = fileDTO.getEnterpriseId();
		userId = fileDTO.getUserId();
		folderId = fileDTO.getFolderId();
		name = fileDTO.getName();
		guid = fileDTO.getGuid();
		size = fileDTO.getSize();
		createTime = fileDTO.getCreateTime();
		updateTime = fileDTO.getUpdateTime();
		thumb = fileDTO.getThumb();
		remark = fileDTO.getRemark();
		version = fileDTO.getVersion();
		pageCount = fileDTO.getPageCount();
		deleted = fileDTO.getDeleted();
		type = fileDTO.getType();
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
	public long getEnterpriseId() {
		return enterpriseId;
	}

	@Override
	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public Long getFolderId() {
		return folderId;
	}

	@Override
	public void setFolderId(Long folderId) {
		this.folderId = folderId;
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
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
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
	public Date getUpdateTime() {
		return updateTime;
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
	public void setUpdateTime(Date updateTime) {
		if (updateTime == null) {
			updateTime = new Date();
		}
		this.updateTime = new Date((updateTime.getTime() / 1000) * 1000);
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
	public int getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String getThumb() {
		return thumb;
	}

	@Override
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public long getVersion() {
		return version;
	}

	@Override
	public void setVersion(long version) {
		this.version = version;
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
