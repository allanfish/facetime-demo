package com.conlect.oatos.dto.client;

/**
 * 外链文件dto
 * 
 * @author yang
 * 
 */
public class ShareLinkFilesDTO extends ShareFolderAndFileDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹
	 */
	private ShareFolderDTO folderDTO;
	/**
	 * 文件
	 */
	private ShareFileDTO fileDTO;

	public ShareFolderDTO getFolderDTO() {
		return folderDTO;
	}

	public void setFolderDTO(ShareFolderDTO folderDTO) {
		this.folderDTO = folderDTO;
	}

	public ShareFileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(ShareFileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}

}
