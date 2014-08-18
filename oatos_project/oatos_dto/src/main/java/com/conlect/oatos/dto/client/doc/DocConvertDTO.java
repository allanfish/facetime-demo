package com.conlect.oatos.dto.client.doc;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.IConferenceDocDTO;
import com.conlect.oatos.dto.autobean.IViewFileDTO;
import com.conlect.oatos.dto.client.BaseDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.DocConvertPriority;
import com.conlect.oatos.dto.status.DocConvertType;
import com.conlect.oatos.file.DiskUtil;

/**
 * 文档转换接口
 * 
 * @author yang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DocConvertDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 网盘根路径
	 */
	private String diskRootPath;

	/**
	 * 文件ID
	 */
	private Long fileId;

	/**
	 * 源文件相对路径
	 */
	private String sourcePath;

	/**
	 * 目标文件相对路径
	 */
	private String targetPath;

	/**
	 * 开始页码
	 */
	private int startPage;

	/**
	 * 总页数
	 */
	private Integer pageCount;

	/**
	 * 转换任务类型<br> {@link DocConvertType}
	 */
	private String convertType = DocConvertType.DOC_CONVERT;

	/**
	 * 转换任务优先级
	 */
	private int priorty = DocConvertPriority.DEFAULT;

	/**
	 * 文件转成swf，中间pdf文件相对路径
	 */
	private String pdfPath;

	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 是否保存目标文件至文件服务器
	 */
	private boolean saveTarget = false;

	/**
	 * 文件处理状态
	 */
	private String status;

	/**
	 * @deprecated
	 */
	@Deprecated
	private String token;

	public DocConvertDTO() {
		diskRootPath = DiskUtil.ONLINEDISK_PATH;
	}

	public DocConvertDTO(String input, String output) {
		diskRootPath = DiskUtil.ONLINEDISK_PATH;
		this.sourcePath = input;
		this.targetPath = output;
	}

	public DocConvertDTO(IViewFileDTO fileDTO) {
		userId = fileDTO.getUserId();
		diskRootPath = DiskUtil.ONLINEDISK_PATH;
		fileId = fileDTO.getFileId();
		startPage = fileDTO.getCurrentPage();
		pageCount = fileDTO.getPageCount();
		fileType = fileDTO.getType();
	}

	public DocConvertDTO(IConferenceDocDTO fileDTO) {
		diskRootPath = DiskUtil.ONLINEDISK_PATH;
		fileId = fileDTO.getFileId();
		startPage = 0;
		pageCount = fileDTO.getPageCount();
		fileType = CommConstants.FILE_TYPE_CONFERENCE_DOC;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDiskRootPath() {
		return diskRootPath;
	}

	public void setDiskRootPath(String diskRootPath) {
		this.diskRootPath = diskRootPath;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getConvertType() {
		return convertType;
	}

	public void setConvertType(String convertType) {
		this.convertType = convertType;
	}

	public int getPriorty() {
		return priorty;
	}

	public void setPriorty(int priorty) {
		this.priorty = priorty;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isSaveTarget() {
		return saveTarget;
	}

	public void setSaveTarget(boolean saveTarget) {
		this.saveTarget = saveTarget;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
