package com.conlect.oatos.dto.client.admin;

import com.conlect.oatos.dto.autobean.admin.IAdminFolderDTO;

/**
 * 二级管理员可操作的文件夹
 * 
 * @author yang
 * 
 */
public class AdminFolderDTO implements IAdminFolderDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 文件夹ID
	 */
	private long folderId;

	public AdminFolderDTO() {
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
	public long getFolderId() {
		return folderId;
	}

	@Override
	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

}
