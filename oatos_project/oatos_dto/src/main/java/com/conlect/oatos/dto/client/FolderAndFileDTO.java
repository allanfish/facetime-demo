package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 个人网盘文件夹和文件
 * 
 * @author yang
 * 
 */
public class FolderAndFileDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹list
	 */
	private List<NetworkFolderDTO> forderList;
	/**
	 * 文件list
	 */
	private List<NetworkFileDTO> fileList;

	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	public List<NetworkFolderDTO> getForderList() {
		return forderList;
	}

	public void setForderList(List<NetworkFolderDTO> forderList) {
		this.forderList = forderList;
	}

	public List<NetworkFileDTO> getFileList() {
		return fileList;
	}

	public void setFileList(List<NetworkFileDTO> fileList) {
		this.fileList = fileList;
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

}
