package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.List;

/**
 * share files dto
 * 
 * @author yang
 * 
 */
public interface IShareFilesDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	List<IShareFileDTO> getShareFileList();

	void setShareFileList(List<IShareFileDTO> shareFileList);

	long getUsedSize();

	void setUsedSize(long usedSize);
}
