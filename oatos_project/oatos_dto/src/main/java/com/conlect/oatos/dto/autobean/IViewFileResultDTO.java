package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

/**
 * 
 * @author yang
 * 
 */
public interface IViewFileResultDTO extends Serializable {

	int getCurrentPage();

	void setCurrentPage(int currentPage);

	int getPageCount();

	void setPageCount(int pageCount);

	int getWidth();

	void setWidth(int width);

	int getHeight();

	void setHeight(int height);

	String getUrl();

	void setUrl(String url);

	String getContent();

	void setContent(String content);

	String getThumb();

	void setThumb(String thumb);

	long getLastModified();

	void setLastModified(long lastModified);

	Long getFileId();

	void setFileId(Long fileId);

	String getGuid();

	void setGuid(String guid);

	String getMessage();

	void setMessage(String message);
}
