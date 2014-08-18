package com.conlect.oatos.dto.client;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 接收文件
 * 
 * @author yang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AcceptFileDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 文件list
	 */
	private List<NetworkFileDTO> fileList;

	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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
