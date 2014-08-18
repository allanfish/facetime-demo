package com.conlect.oatos.dto.autobean.admin;

import java.io.Serializable;
import java.util.List;

public interface IAdminsDTO extends Serializable {

	List<IAdminDTO> getAdmins();

	void setAdmins(List<IAdminDTO> admins);
}
