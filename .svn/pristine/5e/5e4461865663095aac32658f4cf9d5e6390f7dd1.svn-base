package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

/**
 * 阻止联系人
 * @author yang
 *
 */
public class Block implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 阻止联系的用户ID
	 */
	private long blockUserId;

	/**
	 * 可见，true：用户设置的阻止，同事列表可见；false：管理员设置的阻止，同事列表不可见。
	 */
	private boolean visible = true;

	public Block() {
	}

	public Block(long userId, long blockUserId) {
		this.userId = userId;
		this.blockUserId = blockUserId;
	}

	public Block(long userId, long blockUserId, boolean visible) {
		this.userId = userId;
		this.blockUserId = blockUserId;
		this.visible = visible;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getBlockUserId() {
		return blockUserId;
	}

	public void setBlockUserId(long blockUserId) {
		this.blockUserId = blockUserId;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
