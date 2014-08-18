package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 个人网盘文件list
 * 
 * @author yang
 * 
 */
public class NetworkFilesDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件list
	 */
	private List<NetworkFileDTO> networkFileDTOList;

	public NetworkFilesDTO() {
		super();
	}

	public NetworkFilesDTO(List<NetworkFileDTO> networkFileDTOList) {
		super();
		this.networkFileDTOList = networkFileDTOList;
	}

	public void setNetworkFileDTOList(List<NetworkFileDTO> networkFileDTOList) {
		this.networkFileDTOList = networkFileDTOList;
	}

	public List<NetworkFileDTO> getNetworkFileDTOList() {
		return networkFileDTOList;
	}

}
