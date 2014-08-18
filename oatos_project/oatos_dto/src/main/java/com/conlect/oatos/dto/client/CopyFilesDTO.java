package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 文件复制dto list
 * 
 * @author yang
 * 
 */
public class CopyFilesDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CopyFileDTO> files;

	public List<CopyFileDTO> getFiles() {
		return files;
	}

	public void setFiles(List<CopyFileDTO> files) {
		this.files = files;
	}

}
