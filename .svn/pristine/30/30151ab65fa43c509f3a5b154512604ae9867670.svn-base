package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;

/**
 * 个人网盘文件
 * 
 * @author yang
 * 
 */
public class PersonalFile implements Serializable {
	/**
     *
     */
	private static final long serialVersionUID = 1L;
	/**
	 * 文件ID
	 */
	private Long fileId;
	/**
	 * 文件夹ID
	 */
	private Long folderId;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 文件guid
	 */
	private String fileGuid;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件大小，kb
	 */
	private long fileSize;

	/**
	 * 文件上传时间
	 */
	private Date creatTime;

	/**
	 * 文件类型
	 * @deprecated
	 */
	private String fileType;

	/**
	 * 文件最后修改时间
	 */
	private Date updateTime;

	/**
	 * 文件删除状态<br>
	 * {@link com.conlect.oatos.dto.status.FileStatus}
	 */
	private int deleted;

	/**
	 * 文件封面
	 */
	private String thumb;
	/**
	 * 文件备注
	 */
	private String remark;

	/**
	 * 文件版本
	 */
	private long version;

	/**
	 * 文件页数
	 */
	private Integer pageCount;

	public PersonalFile() {
	}

	public PersonalFile(INetworkFileDTO fileDTO) {
		fileId = fileDTO.getFileId();
		if (fileDTO.getFolderId() != null && fileDTO.getFolderId() > 0) {
			folderId = fileDTO.getFolderId().longValue();
		}
		userId = fileDTO.getUserId();
		fileGuid = fileDTO.getGuid();
		fileName = fileDTO.getName();
		creatTime = fileDTO.getCreateTime();
		updateTime = fileDTO.getUpdateTime();
		deleted = fileDTO.getDeleted();
		fileType = CommonUtil.getFileSuffixName(fileName);
		setFileSize(fileDTO.getSize());
		version = fileDTO.getVersion();
		thumb = fileDTO.getThumb();
		remark = fileDTO.getRemark();
		pageCount = fileDTO.getPageCount();
	}

	public NetworkFileDTO toFileDTO() {
		NetworkFileDTO fileDTO = new NetworkFileDTO();
		fileDTO.setFileId(fileId);
		fileDTO.setFolderId(folderId);
		fileDTO.setUserId(userId);
		fileDTO.setGuid(fileGuid);
		fileDTO.setName(fileName);
		fileDTO.setSize(fileSize);
		fileDTO.setCreateTime(creatTime);
		fileDTO.setUpdateTime(updateTime);
		fileDTO.setDeleted(deleted);
		fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
		fileDTO.setVersion(version);
		fileDTO.setThumb(thumb);
		fileDTO.setRemark(remark);
		fileDTO.setPageCount(pageCount);
		return fileDTO;
	}

	public PersonalFile(long fileId) {
		this.fileId = fileId;
	}

	public PersonalFile(long fileId, long userId, String fileGuid,
			String fileName, long fileSize, Date creatTime) {
		this.fileId = fileId;
		this.userId = userId;
		this.fileGuid = fileGuid;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.creatTime = creatTime;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFileGuid() {
		return fileGuid;
	}

	public void setFileGuid(String fileGuid) {
		this.fileGuid = fileGuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getFolderId() {
		return folderId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = CommonUtil.getFileSuffixName(fileName);
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		if (updateTime == null) {
			updateTime = new Date();
		}
		long ut = (updateTime.getTime() / 1000) * 1000;
		this.updateTime = new Date(ut);
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
