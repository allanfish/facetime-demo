package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IShareFolderDTO;

/**
 * 企业网盘文件夹dto
 * 
 * @author yang
 * 
 */
public class ShareFolderDTO extends FolderDTO implements IShareFolderDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 企业id
	 */
	private long enterpriseId;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 空间限制，单位KB
	 */
	private Long maxSize;

	/**
	 * 外链id
	 */
	private Long shareLinkId;

	public ShareFolderDTO() {
	}

	@Override
	public long getEnterpriseId() {
		return enterpriseId;
	}

	@Override
	public Long getMaxSize() {
		return maxSize;
	}

	@Override
	public Long getShareLinkId() {
		return shareLinkId;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@Override
	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public void setShareLinkId(Long shareLinkId) {
		this.shareLinkId = shareLinkId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
