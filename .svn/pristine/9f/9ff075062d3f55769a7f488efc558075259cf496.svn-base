package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.Set;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

public interface IShareFileNewMessageDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	long getEnterpriseId();

	void setEnterpriseId(long enterpriseId);

	Operation getOperation();

	void setOperation(Operation operation);

	long getLatestVersion();

	void setLatestVersion(long latestVersion);

	Set<IShareFolderNewDTO> getFolders();

	void setFolders(Set<IShareFolderNewDTO> folders);

	Set<IShareFileNewDTO> getFiles();

	void setFiles(Set<IShareFileNewDTO> files);
}
