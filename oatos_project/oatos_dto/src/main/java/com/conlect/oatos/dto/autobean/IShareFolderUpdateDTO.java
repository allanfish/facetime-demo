package com.conlect.oatos.dto.autobean;

import java.util.Date;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

public interface IShareFolderUpdateDTO extends IShareFolderDTO {

	Operation getOperation();

	void setOperation(Operation operation);

	String getComment();

	void setComment(String comment);

	String getUserName();

	void setUserName(String userName);

	Date getUpdateTime();

	void setUpdateTime(Date updateTime);

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);
}
