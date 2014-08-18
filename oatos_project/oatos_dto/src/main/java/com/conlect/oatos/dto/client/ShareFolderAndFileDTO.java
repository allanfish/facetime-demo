package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业网盘文件夹和文件
 * 
 * @author yang
 * 
 */
public class ShareFolderAndFileDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹
	 */
	private List<ShareFolderDTO> forderList = new ArrayList<ShareFolderDTO>(0);
	/**
	 * 文件
	 */
	private List<ShareFileDTO> fileList = new ArrayList<ShareFileDTO>(0);
	/**
	 * 父文件夹ID
	 * 
	 * @deprecated
	 */
	@Deprecated
	private Long parentFolderId;

	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	/**
	 * 版本
	 */
	private long version;

	/**
	 * 企业网盘已使用大小
	 * 
	 * @deprecated
	 */
	@Deprecated
	private long usedSize;

	public List<ShareFolderDTO> getForderList() {
		return forderList;
	}

	public void setForderList(List<ShareFolderDTO> forderList) {
		if (forderList != null)
			this.forderList = forderList;
	}

	public List<ShareFileDTO> getFileList() {
		return fileList;
	}

	public void setFileList(List<ShareFileDTO> fileList) {
		if (fileList != null)
			this.fileList = fileList;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public long getUsedSize() {
		return usedSize;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void setUsedSize(long usedSize) {
		this.usedSize = usedSize;
	}

	public Long getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(Long parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
}
