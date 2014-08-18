package com.conlect.oatos.dto.client;

import java.util.List;

import com.conlect.oatos.dto.autobean.IUserIconDTO;
import com.conlect.oatos.dto.autobean.IUserIconsDTO;

/**
 * 用户图像list
 * 
 * @author yang
 * 
 */
public class UserIconsDTO implements IUserIconsDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<IUserIconDTO> iconList;

	@Override
	public List<IUserIconDTO> getIconList() {
		return iconList;
	}

	@Override
	public void setIconList(List<IUserIconDTO> iconList) {
		this.iconList = iconList;
	}

}
