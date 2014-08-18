package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IFolderDTO;
import com.conlect.oatos.dto.autobean.INetworkFolderDTO;
import com.conlect.oatos.dto.autobean.IShareFolderDTO;

/**
 * 文件夹
 * 
 * @author yang
 * 
 */
public class FolderDTO implements IFolderDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹id
	 */
	private long folderId;
	/**
	 * 文件夹名
	 */
	private String name;
	/**
	 * 父文件夹id
	 */
	private Long parentId;
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

	public FolderDTO() {
	}

	public FolderDTO(String name, Long parentId) {
		this.name = name;
		this.parentId = parentId;
	}

	public FolderDTO(long folderId, String name, Long parentId) {
		this.folderId = folderId;
		this.name = name;
		this.parentId = parentId;
	}

	public FolderDTO(IShareFolderDTO folderDTO) {
		this.folderId = folderDTO.getFolderId();
		this.name = folderDTO.getName();
		this.parentId = folderDTO.getParentId();
	}

	public FolderDTO(INetworkFolderDTO folderDTO) {
		this.folderId = folderDTO.getFolderId();
		this.name = folderDTO.getFolderName();
		this.parentId = folderDTO.getParentFolderId();
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
	public Long getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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
	public long getVersion() {
		return version;
	}

	@Override
	public void setVersion(long version) {
		this.version = version;
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
}
