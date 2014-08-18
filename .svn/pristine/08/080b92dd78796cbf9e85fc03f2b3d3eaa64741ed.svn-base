package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.ShareLinkDTO;

/**
 * 共享链接
 * 
 * @author yang
 * 
 */
public class ShareLink implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 外链ID
	 */
	private long linkId;
	/**
	 * 外链code
	 */
	private String linkCode;
	/**
	 * 文件夹ID
	 */
	private Long folderId;
	/**
	 * 文件ID
	 */
	private Long fileId;
	/**
	 * 最大下载次数
	 */
	private Long maxDownload;
	/**
	 * 当前下载次数
	 */
	private long downloadCount;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 到期时间
	 */
	private Date expirationTime;
	/**
	 * 创建者ID
	 */
	private long createUserId;
	/**
	 * 创建者姓名
	 */
	private String createUserName;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 企业ID
	 */
	private long enterpriseId;

	public ShareLink() {
	}

	public ShareLink(ShareLinkDTO linkDTO) {
		BeanUtils.copyProperties(linkDTO, this);
	}

	public ShareLinkDTO toShareLinkDTO() {
		ShareLinkDTO linkDTO = new ShareLinkDTO();
		BeanUtils.copyProperties(this, linkDTO);
		return linkDTO;
	}

	public long getLinkId() {
		return linkId;
	}

	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}

	public String getLinkCode() {
		return linkCode;
	}

	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getMaxDownload() {
		return maxDownload;
	}

	public void setMaxDownload(Long maxDownload) {
		this.maxDownload = maxDownload;
	}

	public long getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(long downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
