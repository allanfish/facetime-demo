package com.conlect.oatos.dto.autobean;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

public interface IShareFileUpdateDTO extends IShareFileDTO {

	Operation getOperation();

	void setOperation(Operation operation);

	String getComment();

	void setComment(String comment);

	String getUserName();

	void setUserName(String userName);

	long getRestoreUserId();

	void setRestoreUserId(long restoreUserId);
}
