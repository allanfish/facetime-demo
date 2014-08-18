package com.conlect.oatos.dto.client;

import java.util.HashSet;
import java.util.Set;

/**
 * 文件更新消息dto
 * 
 * @author yang
 * 
 */
public class ShareFileNewMessageDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作类型
	 * 
	 * @author yang
	 * 
	 */
	public enum Operation {
		NewFolder, RenameFolder, Delete, DeletePermanently, RenameFile, UploadFile, EditFile, ShareFile, Move, Lock, SetFolderSize, RestoreFromRecycle, Update, RestoreVersion,
	}

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 企业id
	 */
	private long enterpriseId;

	/**
	 * 版本
	 * 
	 * @deprecated
	 */
	@Deprecated
	private long latestVersion;

	/**
	 * 文件夹
	 */
	private Set<ShareFolderNewDTO> folders = new HashSet<ShareFolderNewDTO>();

	/**
	 * 文件
	 */
	private Set<ShareFileNewDTO> files = new HashSet<ShareFileNewDTO>();

	public ShareFileNewMessageDTO() {
	}

	public ShareFileNewMessageDTO(long userId, long enterpriseId) {
		this.userId = userId;
		this.enterpriseId = enterpriseId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public long getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(long latestVersion) {
		this.latestVersion = latestVersion;
	}

	public Set<ShareFolderNewDTO> getFolders() {
		return folders;
	}

	public void setFolders(Set<ShareFolderNewDTO> folders) {
		this.folders = folders;
	}

	public Set<ShareFileNewDTO> getFiles() {
		return files;
	}

	public void setFiles(Set<ShareFileNewDTO> files) {
		this.files = files;
	}
}
