package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import com.conlect.oatos.dto.client.RolePermissionDTO;

/**
 * 角色企业网盘文件夹权限
 * @author yang
 *
 */
public class RolePermission implements Serializable {

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

	public RolePermission() {
	}

	public RolePermission(RolePermissionDTO dto) {
		folderId = dto.getFolderId();
		roleId = dto.getRoleId();
		list = dto.isList();
		read = dto.isRead();
		write = dto.isWrite();
		download = dto.isDownload();
		upload = dto.isUpload();
		delete = dto.isDelete();
		share = dto.isShare();
		local = dto.isLocal();
	}

	public RolePermissionDTO toRolePermissionDTO() {
		RolePermissionDTO dto = new RolePermissionDTO();

		dto.setFolderId(folderId);
		dto.setRoleId(roleId);
		dto.setList(list);
		dto.setRead(read);
		dto.setWrite(write);
		dto.setDownload(download);
		dto.setUpload(upload);
		dto.setDelete(delete);
		dto.setShare(share);
		dto.setLocal(local);

		return dto;
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isWrite() {
		return write;
	}

	public void setWrite(boolean write) {
		this.write = write;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isUpload() {
		return upload;
	}

	public void setUpload(boolean upload) {
		this.upload = upload;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

}
