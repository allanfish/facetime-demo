package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

public interface IChatGroupDTO extends Serializable {

	long getId();

	void setId(long id);

	long getUserId();

	void setUserId(long userId);

	String getGroupName();

	void setGroupName(String groupName);

}