package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * 网络文件接口
 * 
 * @author yang
 * 
 */
public interface INetworkFilesDTO extends Serializable {

	void setNetworkFileDTOList(List<INetworkFileDTO> networkFileDTOList);

	List<INetworkFileDTO> getNetworkFileDTOList();
}
