package com.conlect.oatos.dto.autobean;

public interface IShareLinkFilesDTO extends IShareFolderAndFileDTO {

	IShareFolderDTO getFolderDTO();

	void setFolderDTO(IShareFolderDTO folderDTO);

	IShareFileDTO getFileDTO();

	void setFileDTO(IShareFileDTO fileDTO);
}
