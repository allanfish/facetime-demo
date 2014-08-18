package com.conlect.oatos.dto.autobean.mail;

import java.util.List;

public interface IMailContactGroupDTO {

	long getMailContactGroupId();

	void setMailContactGroupId(long mailContactGroupId);

	String getGroupName();

	void setGroupName(String groupName);

	long getUserId();

	void setUserId(long userId);

	List<IMailContactDTO> getMailContacts();

	void setMailContacts(List<IMailContactDTO> mailContacts);

}