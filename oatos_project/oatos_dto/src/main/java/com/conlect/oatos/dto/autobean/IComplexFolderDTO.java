package com.conlect.oatos.dto.autobean;

import java.util.List;

public interface IComplexFolderDTO extends IFolderDTO {

	List<IComplexFolderDTO> getFolders();

	void setFolders(List<IComplexFolderDTO> folders);

	List<IFileDTO> getFiles();

	void setFiles(List<IFileDTO> files);

}
