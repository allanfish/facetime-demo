package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IViewFileDTO;
import com.conlect.oatos.dto.autobean.IViewFileResultDTO;
import com.conlect.oatos.dto.status.CommConstants;

/**
 * 文件浏览结果dto
 * 
 * @author yang
 * 
 */
public class ViewFileResultDTO implements IViewFileResultDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	private Long fileId;

	/**
	 * 文件guid
	 */
	private String guid;

	/**
	 * 结果
	 */
	private String message = CommConstants.OK_MARK;

	/**
	 * 当前页码
	 */
	private int currentPage;

	/**
	 * 页数
	 */
	private int pageCount;

	/**
	 * 图片宽度px
	 */
	private int width;
	/**
	 * 图片高度px
	 */
	private int height;

	/**
	 * 文件地址
	 */
	private String url;

	/**
	 * 文件内容，编辑时需要
	 */
	private String content;

	/**
	 * 封面
	 */
	private String thumb;

	/**
	 * 最后修改时间
	 */
	private long lastModified;

	public ViewFileResultDTO() {
	}

	public ViewFileResultDTO(IViewFileDTO fileDTO) {
		this.fileId = fileDTO.getFileId();
		this.guid = fileDTO.getGuid();
	}

	@Override
	public int getCurrentPage() {
		return currentPage;
	}

	@Override
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public int getPageCount() {
		return pageCount;
	}

	@Override
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getThumb() {
		return thumb;
	}

	@Override
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Override
	public long getLastModified() {
		return lastModified;
	}

	@Override
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
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
	public String getGuid() {
		return guid;
	}

	@Override
	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}

}
