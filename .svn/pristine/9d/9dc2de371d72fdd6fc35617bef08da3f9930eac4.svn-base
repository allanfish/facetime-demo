package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import com.conlect.oatos.dto.client.admin.AdminFolderDTO;

/**
 * 二级管理员企业网盘文件夹操作权限
 * @author yang
 *
 */
public class AdminFolder implements Serializable {

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

	public AdminFolder() {
	}

	public AdminFolder(AdminFolderDTO f) {
		userId = f.getUserId();
		folderId = f.getFolderId();
	}

	public AdminFolderDTO toAdminFolderDTO() {
		AdminFolderDTO f = new AdminFolderDTO();
		f.setUserId(userId);
		f.setFolderId(folderId);
		return f;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

}
