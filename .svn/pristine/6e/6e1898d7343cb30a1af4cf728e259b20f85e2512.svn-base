package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.autobean.IShareFolderDTO;

/**
 * 文件夹
 * 
 * @author yang
 * 
 */
public class ComplexFolderDTO extends FolderDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 子文件夹
	 */
	private List<ComplexFolderDTO> folders = new ArrayList<ComplexFolderDTO>();

	/**
	 * 文件
	 */
	private List<FileDTO> files = new ArrayList<FileDTO>();

	public ComplexFolderDTO() {
	}

	public ComplexFolderDTO(IShareFolderDTO folderDTO) {
		super(folderDTO);
	}

	public ComplexFolderDTO(long folderId, String name, Long parentId) {
		super(folderId, name, parentId);
	}

	public ComplexFolderDTO(String name, Long parentId) {
		super(name, parentId);
	}

	public List<ComplexFolderDTO> getFolders() {
		return folders;
	}

	public void setFolders(List<ComplexFolderDTO> folders) {
		this.folders = folders;
	}

	public List<FileDTO> getFiles() {
		return files;
	}

	public void setFiles(List<FileDTO> files) {
		this.files = files;
	}

}
