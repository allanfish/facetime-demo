package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

import com.conlect.oatos.dto.status.FilePermission;

/**
 * check permission dto
 * 
 * @author yang
 * 
 */
public interface ICheckPermissionDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	Long getFolderId();

	void setFolderId(Long folderId);

	FilePermission getOperate();

	void setOperate(FilePermission operate);
}
