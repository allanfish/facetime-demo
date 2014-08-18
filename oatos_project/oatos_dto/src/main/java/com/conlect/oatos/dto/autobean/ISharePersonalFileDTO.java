package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * for client only
 * 
 * @author yang
 * 
 */
public interface ISharePersonalFileDTO extends Serializable {

	List<INetworkFolderDTO> getForderList();

	void setForderList(List<INetworkFolderDTO> forderList);

	List<INetworkFileDTO> getFileList();

	void setFileList(List<INetworkFileDTO> fileList);

	long getUserId();

	void setUserId(long userId);

	String getUserName();

	void setUserName(String userName);

	long getEnterpriseId();

	void setEnterpriseId(long enterpriseId);

	long getFolderId();

	void setFolderId(long folderId);

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);
}
