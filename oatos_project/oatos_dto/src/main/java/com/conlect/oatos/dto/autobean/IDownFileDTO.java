package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * down file
 * 
 * @author yang
 * 
 */
public interface IDownFileDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	String getDiskIp();

	void setDiskIp(String diskIp);

	String getType();

	void setType(String type);

	List<IComplexFolderDTO> getFolderList();

	void setFolderList(List<IComplexFolderDTO> folderList);

	List<IFileDTO> getFileList();

	void setFileList(List<IFileDTO> fileList);

	IFolderDTO getFolder();

	void setFolder(IFolderDTO folder);

	long getEnterpriseId();

	void setEnterpriseId(long enterpriseId);
}
