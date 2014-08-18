package com.conlect.oatos.dto.autobean;

import java.util.List;

public interface IRoleUserDTO extends IRoleDTO {
	List<Long> getUserIdList();

	void setUserIdList(List<Long> userIdList);
}
