package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * accept file for client only
 * 
 * @author yang
 * 
 */
public interface IAcceptFileDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	List<INetworkFileDTO> getFileList();

	void setFileList(List<INetworkFileDTO> fileList);

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);
}
