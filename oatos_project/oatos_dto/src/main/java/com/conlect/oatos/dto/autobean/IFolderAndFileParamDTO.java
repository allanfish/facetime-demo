package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

public interface IFolderAndFileParamDTO extends Serializable {
	long getUserId();

	void setUserId(long userId);

	public abstract void setFolderIds(List<Long> folderIds);

	public abstract List<Long> getFolderIds();

}
