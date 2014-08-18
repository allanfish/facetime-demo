package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IShareFileNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

/**
 * 新文件文件dto
 * 
 * @author yang
 * 
 */
public class ShareFileNewDTO implements IShareFileNewDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	private Long fileId;

	/**
	 * 文件夹id
	 */
	private Long folderId;

	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 文件guid
	 */
	private String guid;

	/**
	 * 操作
	 */
	private ShareFileNewMessageDTO.Operation operation;

	public ShareFileNewDTO(Long folderId, String name, String guid,
			Operation operation) {
		this.folderId = folderId;
		this.name = name;
		this.guid = guid;
		this.operation = operation;
	}

	public ShareFileNewDTO(Long fileId, Long folderId, String name,
			String guid, Operation operation) {
		this(folderId, name, guid, operation);
		this.fileId = fileId;
	}

	@Override
	public Long getFileId() {
		return fileId;
	}

	@Override
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	@Override
	public Long getFolderId() {
		return folderId;
	}

	@Override
	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getGuid() {
		return guid;
	}

	@Override
	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public ShareFileNewMessageDTO.Operation getOperation() {
		return operation;
	}

	@Override
	public void setOperation(ShareFileNewMessageDTO.Operation operation) {
		this.operation = operation;
	}

}
