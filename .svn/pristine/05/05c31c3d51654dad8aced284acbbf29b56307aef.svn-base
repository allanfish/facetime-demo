package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.ShareFolderDTO;

/**
 * 企业网盘文件夹
 * @author yang
 *
 */
public class ShareFolder implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹ID
	 */
	private long folderId;
	/**
	 * 企业ID
	 */
	private Long enterpriseId;
	/**
	 * 文件夹名
	 */
	private String name;
	/**
	 * 父文件夹ID
	 */
	private Long parentId;
	/**
	 * 文件夹容量
	 */
	private Long maxSize;
	/**
	 * 文件夹删除状态<br>
	 * {@link com.conlect.oatos.dto.status.FileStatus}
	 */
	private int deleted;

	/**
	 * 文件夹外链ID
	 */
	private Long shareLinkId;

	/**
	 * 文件夹封面
	 */
	private String thumb;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 文件夹版本
	 */
	private long version;

	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public ShareFolder() {
	}

	public ShareFolder(ShareFolderDTO shareFolderDTO) {
		folderId = shareFolderDTO.getFolderId();
		enterpriseId = shareFolderDTO.getEnterpriseId();
		name = shareFolderDTO.getName();
		parentId = shareFolderDTO.getParentId();
		deleted = shareFolderDTO.getDeleted();
		maxSize = shareFolderDTO.getMaxSize();
		shareLinkId = shareFolderDTO.getShareLinkId();
		version = shareFolderDTO.getVersion();
		thumb = shareFolderDTO.getThumb();
		remark = shareFolderDTO.getRemark();
	}

	public ShareFolderDTO toShareFolderDTO() {
		ShareFolderDTO folderDTO = new ShareFolderDTO();
		folderDTO.setFolderId(folderId);
		folderDTO.setEnterpriseId(enterpriseId);
		folderDTO.setName(name);
		folderDTO.setParentId(parentId);
		folderDTO.setDeleted(deleted);
		folderDTO.setMaxSize(maxSize);
		folderDTO.setShareLinkId(shareLinkId);
		folderDTO.setVersion(version);
		folderDTO.setThumb(thumb);
		folderDTO.setRemark(remark);
		return folderDTO;
	}

	public ShareFolder(ShareFolderHistory historyModel) {
		BeanUtils.copyProperties(historyModel, this);
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}

	public Long getShareLinkId() {
		return shareLinkId;
	}

	public void setShareLinkId(Long shareLinkId) {
		this.shareLinkId = shareLinkId;
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

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
