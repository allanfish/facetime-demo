package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息list
 * 
 * @author yang
 * 
 */
public interface IUserInfosDTO extends Serializable {

	void setUserInfoDTOList(List<IUserInfoDTO> userInfoDTOList);

	List<IUserInfoDTO> getUserInfoDTOList();

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);
}
