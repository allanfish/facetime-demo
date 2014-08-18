package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IShareFileDTO;

/**
 * 企业网盘文件dto
 * 
 * @author yang
 * 
 */
public class ShareFileDTO extends FileDTO implements IShareFileDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 锁定文件的用户id
	 */
	private Long lockByUserId;
	/**
	 * 锁定文件的用户名
	 */
	private String lockByUser;

	/**
	 * 外链id
	 */
	private Long shareLinkId;

	/**
	 * 是否已收藏
	 */
	private boolean favorite = false;

	public ShareFileDTO() {
	}

	@Override
	public Long getLockByUserId() {
		return lockByUserId;
	}

	@Override
	public void setLockByUserId(Long lockByUserId) {
		this.lockByUserId = lockByUserId;
	}

	@Override
	public String getLockByUser() {
		return lockByUser;
	}

	@Override
	public void setLockByUser(String lockByUser) {
		this.lockByUser = lockByUser;
	}

	@Override
	public Long getShareLinkId() {
		return shareLinkId;
	}

	@Override
	public void setShareLinkId(Long shareLinkId) {
		this.shareLinkId = shareLinkId;
	}

	@Override
	public boolean isFavorite() {
		return favorite;
	}

	@Override
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
}
