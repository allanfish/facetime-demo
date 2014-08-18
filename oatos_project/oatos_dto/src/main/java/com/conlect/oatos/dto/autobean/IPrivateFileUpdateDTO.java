package com.conlect.oatos.dto.autobean;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

public interface IPrivateFileUpdateDTO extends INetworkFileDTO {
	Operation getOperation();

	void setOperation(Operation operation);

	String getComment();

	void setComment(String comment);
}
