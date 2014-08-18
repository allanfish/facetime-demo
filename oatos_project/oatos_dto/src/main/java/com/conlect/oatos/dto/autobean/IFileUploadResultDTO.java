package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

public interface IFileUploadResultDTO extends Serializable {

	Long getFileId();

	void setFileId(Long fileId);

	String getName();

	void setName(String name);

	String getType();

	void setType(String type);

	String getResult();

	void setResult(String result);
}
