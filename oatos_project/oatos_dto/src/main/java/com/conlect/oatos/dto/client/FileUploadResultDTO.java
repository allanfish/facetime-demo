package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IFileUploadResultDTO;

/**
 * 文件上传结果
 * 
 * @author yang
 * 
 */
public class FileUploadResultDTO implements IFileUploadResultDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	private Long fileId;

	/**
	 * 文件名
	 */
	private String name;

	/**
	 * 文件类型
	 */
	private String type;

	/**
	 * 上传结果
	 */
	private String result;

	public FileUploadResultDTO() {
	}

	public FileUploadResultDTO(Long fileId, String name, String type,
			String result) {
		this.fileId = fileId;
		this.name = name;
		this.type = type;
		this.result = result;
	}

	@Override
	public Long getFileId() {
		return fileId;
	}

	@Override
	public void setFileId(Long fileId) {
		this.fileId = fileId;
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
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getResult() {
		return result;
	}

	@Override
	public void setResult(String result) {
		this.result = result;
	}

}
