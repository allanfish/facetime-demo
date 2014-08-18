package com.conlect.oatos.dto.autobean;

/**
 * view file
 * 
 * @author yang
 * 
 */
public interface IViewFileDTO extends IFileDTO {

	boolean isSaved();

	void setSaved(boolean saved);

	long getFromId();

	void setFromId(long fromId);

	int getCurrentPage();

	void setCurrentPage(int currentPage);

	String getUserName();

	void setUserName(String userName);

	boolean isCheckPermission();

	void setCheckPermission(boolean checkPermission);

}
