package com.conlect.oatos.dto.autobean.admin;

import java.io.Serializable;

public interface IAdminFolderDTO extends Serializable {
	long getUserId();

	void setUserId(long userId);

	long getFolderId();

	void setFolderId(long folderId);
}
