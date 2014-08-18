package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户ios设备标志list
 * 
 * @author yang
 * 
 */
public class UserDeviceTokensDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	private List<UserDeviceTokenDTO> userDeviceTokenList = new ArrayList<UserDeviceTokenDTO>(
			0);

	public List<UserDeviceTokenDTO> getUserDeviceTokenList() {
		return userDeviceTokenList;
	}

	public void setUserDeviceTokenList(
			List<UserDeviceTokenDTO> userDeviceTokenList) {
		if (userDeviceTokenList != null)
			this.userDeviceTokenList = userDeviceTokenList;
	}
}
