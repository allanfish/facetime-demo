package com.conlect.oatos.dto.autobean;

import java.util.Date;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

public interface IPrivateFolderUpdateDTO extends INetworkFolderDTO {
	Operation getOperation();

	void setOperation(Operation operation);

	Date getUpdateTime();

	void setUpdateTime(Date updateTime);

	String getComment();

	void setComment(String comment);
}
