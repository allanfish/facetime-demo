package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

/**
 * simple user info
 * 
 * @author yang
 * 
 */
public interface ISimpleUserInfoDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	String getUserName();

	void setUserName(String userName);
}
