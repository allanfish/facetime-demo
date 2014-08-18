package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.INetworkFolderDTO;

/**
 * 个人网盘文件夹
 * 
 * @author 秦利军
 * 
 */
public class NetworkFolderDTO implements INetworkFolderDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹id
	 */
	public long folderId;
	/**
	 * 用户id
	 */
	public long userId;
	/**
	 * 文件夹名
	 */
	public String folderName;
	/**
	 * 父文件夹id
	 */
	public Long parentFolderId;
	/**
	 * 删除状态<br> {@link com.conlect.oatos.dto.status.FileStatus}
	 */
	private int deleted;

	/**
	 * 封面
	 */
	private String thumb;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 版本
	 */
	private long version;

	public NetworkFolderDTO() {
	}

	public NetworkFolderDTO(long folderId, long userId, String folderName,
			Long parentFolderId) {
		this.folderId = folderId;
		this.userId = userId;
		this.folderName = folderName;
		this.parentFolderId = parentFolderId;
	}

	@Override
	public long getFolderId() {
		return folderId;
	}

	@Override
	public void setFolderId(long folderId) {
		this.folderId = folderId;
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
	public String getFolderName() {
		return folderName;
	}

	@Override
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	@Override
	public Long getParentFolderId() {
		return parentFolderId;
	}

	@Override
	public void setParentFolderId(Long parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	@Override
	public int getDeleted() {
		return deleted;
	}

	@Override
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public String getThumb() {
		return thumb;
	}

	@Override
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public long getVersion() {
		return version;
	}

	@Override
	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NetworkFolderDTO) {
			NetworkFolderDTO folderDTO = (NetworkFolderDTO) obj;
			return folderId == folderDTO.folderId;
		}
		return false;
	}

}
