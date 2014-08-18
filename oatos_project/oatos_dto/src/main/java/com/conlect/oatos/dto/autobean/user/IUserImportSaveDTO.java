package com.conlect.oatos.dto.autobean.user;

import java.util.List;

import com.conlect.oatos.dto.autobean.IEnterpriseUserDTO;

public interface IUserImportSaveDTO {

	public long getEntId();

	public void setEntId(long entId);

	public List<IEnterpriseUserDTO> getUserList();

	public void setUserList(List<IEnterpriseUserDTO> userList);
}
