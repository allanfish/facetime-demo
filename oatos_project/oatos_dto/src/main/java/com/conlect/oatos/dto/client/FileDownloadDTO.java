package com.conlect.oatos.dto.client;

/**
 * 文件下载参数
 * 
 * @author yang
 * 
 */
public class FileDownloadDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	protected Long userId;
	/**
	 * 企业id
	 */
	protected Long entId;
	/**
	 * 文件id
	 */
	protected Long fileId;
	/**
	 * 文件夹id
	 */
	protected Long folderId;
	/**
	 * 文件名ASCII
	 */
	protected String fileNameASCII;
	/**
	 * 文件名
	 */
	protected String fileName;
	/**
	 * 文件guid
	 */
	protected String guidName;
	/**
	 * 文件类型
	 */
	protected String type;

	/**
	 * 分段下载开始位置
	 */
	protected Integer start = 0;
	/**
	 * 分段下载结束位置
	 */
	protected Integer end = 0;

	/**
	 * 是否检查下载权限
	 */
	protected boolean checkDownPermission = false;

	public FileDownloadDTO() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public String getFileNameASCII() {
		return fileNameASCII;
	}

	public void setFileNameASCII(String fileNameASCII) {
		this.fileNameASCII = fileNameASCII;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getGuidName() {
		return guidName;
	}

	public void setGuidName(String guidName) {
		this.guidName = guidName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public boolean isCheckDownPermission() {
		return checkDownPermission;
	}

	public void setCheckDownPermission(boolean checkDownPermission) {
		this.checkDownPermission = checkDownPermission;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (userId != null) {
			sb.append("userId:").append(userId);
		}
		if (entId != null) {
			sb.append(", enterpriseId:").append(entId);
		}
		if (fileId != null) {
			sb.append(", fileId:").append(fileId);
		}
		if (folderId != null) {
			sb.append(", folderId:").append(folderId);
		}
		sb.append(", fileName:").append(fileName);
		sb.append(", guidName:").append(guidName);
		sb.append(", type:").append(type);
		if (start != null) {
			sb.append(", start:").append(start);
		}
		if (end != null) {
			sb.append(", end:").append(end);
		}
		sb.append(", checkDownPermission:").append(checkDownPermission);
		return sb.toString();
	}

}
