package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件打包下载 dto
 * 
 * @author yang
 * 
 */
public class DownFileDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户Id
	 */
	private long userId;

	/**
	 * 企业Id
	 */
	private long enterpriseId;
	/**
	 * @deprecated
	 */
	@Deprecated
	private String diskIp;

	/**
	 * 文件类型，企业网盘或者个人网盘
	 */
	private String type;

	/**
	 * 当前文件夹
	 */
	private FolderDTO folder;

	/**
	 * 子文件夹list
	 */
	private List<ComplexFolderDTO> folderList = new ArrayList<ComplexFolderDTO>();

	/**
	 * 文件list
	 */
	private List<FileDTO> fileList = new ArrayList<FileDTO>();

	public DownFileDTO() {
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
	public String getDiskIp() {
		return diskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void setDiskIp(String diskIp) {
		this.diskIp = diskIp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ComplexFolderDTO> getFolderList() {
		return folderList;
	}

	public void setFolderList(List<ComplexFolderDTO> folderList) {
		this.folderList = folderList;
	}

	public List<FileDTO> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileDTO> fileList) {
		this.fileList = fileList;
	}

	public FolderDTO getFolder() {
		return folder;
	}

	public void setFolder(FolderDTO folder) {
		this.folder = folder;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
