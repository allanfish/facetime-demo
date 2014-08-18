package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * for client only
 * 
 * @author yang
 * 
 */
public interface IFolderAndFileDTO extends Serializable {

	List<INetworkFolderDTO> getForderList();

	void setForderList(List<INetworkFolderDTO> forderList);

	List<INetworkFileDTO> getFileList();

	void setFileList(List<INetworkFileDTO> fileList);

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);
}
