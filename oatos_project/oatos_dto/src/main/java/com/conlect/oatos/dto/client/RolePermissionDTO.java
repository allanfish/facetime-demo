package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IRolePermissionDTO;

/**
 * 角色企业网盘权限
 * 
 * @author yang
 * 
 */
public class RolePermissionDTO implements IRolePermissionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹ID
	 */
	private long folderId;
	/**
	 * 角色ID
	 */
	private long roleId;

	/**
	 * 文件列表可见
	 */
	private boolean list;
	/**
	 * 预览文件
	 */
	private boolean read;
	/**
	 * 编辑文件，新建，修改文件夹
	 */
	private boolean write;
	/**
	 * 删除文件和文件夹
	 */
	private boolean delete;
	/**
	 * 上传文件
	 */
	private boolean upload;
	/**
	 * 下载文件
	 */
	private boolean download;
	/**
	 * 共享文件和文件夹
	 */
	private boolean share;
	/**
	 * 文件本地交互
	 */
	private boolean local;

	@Override
	public long getFolderId() {
		return folderId;
	}

	@Override
	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	@Override
	public long getRoleId() {
		return roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean isList() {
		return list;
	}

	@Override
	public void setList(boolean list) {
		this.list = list;
	}

	@Override
	public boolean isRead() {
		return read;
	}

	@Override
	public void setRead(boolean read) {
		this.read = read;
	}

	@Override
	public boolean isWrite() {
		return write;
	}

	@Override
	public void setWrite(boolean write) {
		this.write = write;
	}

	@Override
	public boolean isDelete() {
		return delete;
	}

	@Override
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@Override
	public boolean isUpload() {
		return upload;
	}

	@Override
	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	@Override
	public boolean isDownload() {
		return download;
	}

	@Override
	public void setDownload(boolean download) {
		this.download = download;
	}

	@Override
	public boolean isShare() {
		return share;
	}

	@Override
	public void setShare(boolean share) {
		this.share = share;
	}

	@Override
	public boolean isLocal() {
		return local;
	}

	@Override
	public void setLocal(boolean local) {
		this.local = local;
	}

}
