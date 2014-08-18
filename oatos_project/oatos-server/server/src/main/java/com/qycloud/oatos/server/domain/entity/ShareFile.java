package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;

/**
 * 企业网盘文件
 * @author yang
 *
 */
public class ShareFile implements Serializable {

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
	 * 企业ID
	 */
	private Long enterpriseId;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件大小，kb
	 */
	private long size;
	/**
	 * 文件类型
	 * @deprecated
	 */
	private String type;
	/**
	 * 文件guid
	 */
	private String guid;
	/**
	 * 文件上传时间
	 */
	private Date createTime;
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
	 * 文件外链ID
	 */
	private Long shareLinkId;

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
	 * 文件上传者ID
	 */
	private Long createUserId;

	/**
	 * 文件页数
	 */
	private Integer pageCount;

	public ShareFile() {
	}

	public ShareFile(ShareFileDTO shareFileDTO) {
		fileId = shareFileDTO.getFileId();
		folderId = shareFileDTO.getFolderId();
		enterpriseId = shareFileDTO.getEnterpriseId();
		name = shareFileDTO.getName();
		setSize(shareFileDTO.getSize());
		type = CommonUtil.getFileSuffixName(name);
		guid = shareFileDTO.getGuid();
		createTime = shareFileDTO.getCreateTime();
		updateTime = shareFileDTO.getUpdateTime();
		deleted = shareFileDTO.getDeleted();
		shareLinkId = shareFileDTO.getShareLinkId();
		version = shareFileDTO.getVersion();
		thumb = shareFileDTO.getThumb();
		remark = shareFileDTO.getRemark();
		pageCount = shareFileDTO.getPageCount();
	}

	public ShareFileDTO toShareFileDTO() {
		ShareFileDTO fileDTO = new ShareFileDTO();
		fileDTO.setFileId(fileId);
		fileDTO.setFolderId(folderId);
		fileDTO.setEnterpriseId(enterpriseId);
		fileDTO.setName(name);
		fileDTO.setSize(size);
		fileDTO.setType(CommConstants.FILE_TYPE_SHAREDISK);
		fileDTO.setGuid(guid);
		fileDTO.setCreateTime(createTime);
		fileDTO.setUpdateTime(updateTime);
		fileDTO.setDeleted(deleted);
		fileDTO.setShareLinkId(shareLinkId);
		fileDTO.setVersion(version);
		fileDTO.setThumb(thumb);
		fileDTO.setRemark(remark);
		fileDTO.setPageCount(pageCount);

		return fileDTO;
	}

	public ShareFile(ShareFileHistory historyModel) {
		BeanUtils.copyProperties(historyModel, this);
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
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
		this.type = CommonUtil.getFileSuffixName(name);
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
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

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
