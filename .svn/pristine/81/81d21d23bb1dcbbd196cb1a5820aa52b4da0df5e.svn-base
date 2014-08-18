package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.ICheckPermissionDTO;
import com.conlect.oatos.dto.status.FilePermission;

/**
 * 企业网盘权限检查
 * 
 * @author yang
 * 
 */
public class CheckPermissionDTO implements ICheckPermissionDTO {

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
	private Long folderId;

	/**
	 * 操作类型<br> {@link FilePermission}
	 */
	private FilePermission operate;

	public CheckPermissionDTO() {
	}

	public CheckPermissionDTO(long userId, Long folderId, FilePermission operate) {
		this.userId = userId;
		this.folderId = folderId;
		this.operate = operate;
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
	public FilePermission getOperate() {
		return operate;
	}

	@Override
	public void setOperate(FilePermission operate) {
		this.operate = operate;
	}

}
