package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

/**
 * share folder dto
 * 
 * @author yang
 * 
 */
public interface IFolderDTO extends Serializable {

	long getFolderId();

	void setFolderId(long folderId);

	Long getParentId();

	void setParentId(Long parentId);

	String getName();

	void setName(String name);

	int getDeleted();

	void setDeleted(int deleted);

	String getThumb();

	void setThumb(String thumb);

	String getRemark();

	void setRemark(String remark);

	long getVersion();

	void setVersion(long version);
}
