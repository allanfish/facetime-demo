package com.conlect.oatos.dto.client;

/**
 * 复制文件dto
 * 
 * @author yang
 * 
 */
public class CopyFileDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 源文件
	 */
	private String fromPath;

	/**
	 * 目标文件
	 */
	private String toPath;

	public CopyFileDTO() {
	}

	public CopyFileDTO(String fromPath, String toPath) {
		this.fromPath = fromPath;
		this.toPath = toPath;
	}

	public String getFromPath() {
		return fromPath;
	}

	public void setFromPath(String fromPath) {
		this.fromPath = fromPath;
	}

	public String getToPath() {
		return toPath;
	}

	public void setToPath(String toPath) {
		this.toPath = toPath;
	}
}
