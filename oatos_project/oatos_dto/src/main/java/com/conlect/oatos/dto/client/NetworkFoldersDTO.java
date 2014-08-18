package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 个人网盘文件夹list
 * 
 * @author yang
 * 
 */
public class NetworkFoldersDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹list
	 */
	private List<NetworkFolderDTO> networkFolderDTOList;

	public void setNetworkFolderDTOList(
			List<NetworkFolderDTO> networkFolderDTOList) {
		this.networkFolderDTOList = networkFolderDTOList;
	}

	public List<NetworkFolderDTO> getNetworkFolderDTOList() {
		return networkFolderDTOList;
	}

}
