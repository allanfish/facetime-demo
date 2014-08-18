package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;

/**
 * 文件上传参数
 * 
 * @author yang
 * 
 */
public class FileUploadDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件id，同步文件时需要
	 */
	protected Long fileId;
	/**
	 * 用户id
	 */
	protected Long userId;
	/**
	 * 企业id
	 */
	protected Long entId;
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
	 * 文件guid，可不传
	 */
	protected String guidName;
	/**
	 * 文件大小，，单位：byte，分段上传时需要
	 */
	protected Long fileSize;
	/**
	 * 文件类型
	 */
	protected String type;
	/**
	 * 接收者id，发送文件时需要
	 */
	protected Long receiverId;

	/**
	 * 分块大小，单位：byte，分段上传时需要
	 */
	protected Long blockSize;
	/**
	 * 分块数目，分段上传时需要
	 */
	protected Integer blockCount;
	/**
	 * 分块编号，分段上传时需要
	 */
	protected Integer blockIndex;
	/**
	 * 文件MD5校验码
	 */
	protected String md5;

	/**
	 * 旧的guid，文件同步上传时需要
	 */
	protected String oldGuid;

	/**
	 * 方法<br/>
	 * 1:表示判断网盘容量及检查文件是否存在,取已经上传完成块数目<br/>
	 * 3:表示检查文件是否在后台传输完成且成功上传<br/>
	 * 为其他值则表示上传数据
	 */
	protected String method;

	public FileUploadDTO() {
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
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

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(Long blockSize) {
		this.blockSize = blockSize;
	}

	public Integer getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(Integer blockCount) {
		this.blockCount = blockCount;
	}

	public Integer getBlockIndex() {
		return blockIndex;
	}

	public void setBlockIndex(Integer blockIndex) {
		this.blockIndex = blockIndex;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getOldGuid() {
		return oldGuid;
	}

	public void setOldGuid(String oldGuid) {
		this.oldGuid = oldGuid;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public NetworkFileDTO toPrivateFileDTO() {
		NetworkFileDTO fileDTO = new NetworkFileDTO();
		fileDTO.setFileId(fileId);
		fileDTO.setUserId(userId);
		fileDTO.setFolderId(folderId);
		fileDTO.setName(fileName);
		fileDTO.setGuid(guidName);
		fileDTO.setSize(CommonUtil.getFileSizeKb(fileSize));
		fileDTO.setType(CommConstants.FILE_TYPE_ONLINEDISK);
		return fileDTO;
	}

	public ShareFileUpdateDTO toEnterpriseFileDTO() {
		ShareFileUpdateDTO fileDTO = new ShareFileUpdateDTO();
		fileDTO.setFileId(fileId);
		fileDTO.setEnterpriseId(entId);
		fileDTO.setFolderId(folderId);
		fileDTO.setName(fileName);
		fileDTO.setGuid(guidName);
		fileDTO.setSize(CommonUtil.getFileSizeKb(fileSize));
		fileDTO.setUserId(userId);
		fileDTO.setType(CommConstants.FILE_TYPE_SHAREDISK);
		return fileDTO;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("method:").append(method);
		sb.append(", userId:").append(userId);
		sb.append(", entId:").append(entId);
		sb.append(", folderId:").append(folderId);
		sb.append(", fileName:").append(fileName);
		sb.append(", guidName:").append(guidName);
		sb.append(", type:").append(type);
		sb.append(", receiverId:").append(receiverId);
		sb.append(", fileSize:").append(fileSize);
		return sb.toString();
	}

}
