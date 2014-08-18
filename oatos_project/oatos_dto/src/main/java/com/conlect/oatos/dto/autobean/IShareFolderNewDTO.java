package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;

public interface IShareFolderNewDTO extends Serializable {
	Long getFolderId();

	void setFolderId(Long folderId);

	Long getParentId();

	void setParentId(Long parentId);

	String getName();

	void setName(String name);

	ShareFileNewMessageDTO.Operation getOperation();

	void setOperation(ShareFileNewMessageDTO.Operation operation);
}
