package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * user icons
 * 
 * @author yang
 * 
 */
public interface IUserIconsDTO extends Serializable {

	void setIconList(List<IUserIconDTO> iconList);

	List<IUserIconDTO> getIconList();
}
