package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

/**
 * folder
 * 
 * @author yang
 * 
 */
public interface INetworkFolderDTO extends Serializable {

	void setFolderId(long folderId);

	void setUserId(long userId);

	void setFolderName(String folderName);

	void setParentFolderId(Long parentFolderId);

	long getFolderId();

	long getUserId();

	String getFolderName();

	Long getParentFolderId();

	int getDeleted();

	void setDeleted(int deleted);

	String getThumb();

	void setThumb(String thumb);

	String getRemark();

	void setRemark(String remark);

	long getVersion();

	void setVersion(long version);
}
