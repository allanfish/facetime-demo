package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import com.conlect.oatos.dto.client.NetworkFolderDTO;

/**
 * 个人网盘文件夹
 * 
 * @author yang
 * 
 */
public class PersonalFolder implements Serializable, Comparable<PersonalFolder> {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	/**
	 * 文件夹ID
	 */
	private long folderId;
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 文件夹名称
	 */
	private String folderName;
	/**
	 * 父文件夹ID
	 */
	private Long parentFolderId;

	/**
	 * 文件夹删除状态<br>
	 * {@link com.conlect.oatos.dto.status.FileStatus}
	 */
	private int deleted;

	/**
	 * 文件夹封面
	 */
	private String thumb;
	/**
	 * 文件夹备注
	 */
	private String remark;

	/**
	 * 文件夹版本
	 */
	private long version;

	// 排序使用
	private int level;

	public PersonalFolder() {
	}

	public PersonalFolder(NetworkFolderDTO folderDTO) {
		folderId = folderDTO.getFolderId();
		userId = folderDTO.getUserId();
		folderName = folderDTO.getFolderName();
		deleted = folderDTO.getDeleted();
		if (folderDTO.getParentFolderId() != null
				&& folderDTO.getParentFolderId() > 0) {
			parentFolderId = folderDTO.getParentFolderId();
		}
		version = folderDTO.getVersion();
		thumb = folderDTO.getThumb();
		remark = folderDTO.getRemark();
	}

	public NetworkFolderDTO toFolderDTO() {
		NetworkFolderDTO folderDTO = new NetworkFolderDTO();
		folderDTO.setFolderId(folderId);
		folderDTO.setUserId(userId);
		folderDTO.setFolderName(folderName);
		folderDTO.setParentFolderId(parentFolderId);
		folderDTO.setDeleted(deleted);
		folderDTO.setVersion(version);
		folderDTO.setThumb(thumb);
		folderDTO.setRemark(remark);
		return folderDTO;
	}

	/**
	 * @return the folderId
	 */
	public long getFolderId() {
		return folderId;
	}

	/**
	 * @param folderId
	 *            the folderId to set
	 */
	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * @param folderName
	 *            the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public void setParentFolderId(Long parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public Long getParentFolderId() {
		return parentFolderId;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	/**
	 * 倒序排列
	 */
	@Override
	public int compareTo(PersonalFolder o) {
		return this.level - o.getLevel();
	}
}
