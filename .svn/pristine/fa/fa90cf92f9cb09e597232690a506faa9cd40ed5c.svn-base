package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IShareFolderNewDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

/**
 * 企业网盘新文件夹dto
 * 
 * @author yang
 * 
 */
public class ShareFolderNewDTO implements IShareFolderNewDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件夹id
	 */
	private Long folderId;

	/**
	 * 父文件间id
	 */
	private Long parentId;

	/**
	 * 文件夹名
	 */
	private String name;

	/**
	 * 操作
	 */
	private ShareFileNewMessageDTO.Operation operation;

	public ShareFolderNewDTO(Long parentId, String name, Operation operation) {
		this.parentId = parentId;
		this.name = name;
		this.operation = operation;
	}

	public ShareFolderNewDTO(Long folderId, Long parentId, String name,
			Operation operation) {
		this(parentId, name, operation);
		this.folderId = folderId;
	}

	public ShareFolderNewDTO(Long folderId, Long parentId, String name) {
		super();
		this.folderId = folderId;
		this.parentId = parentId;
		this.name = name;
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
	public Long getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	public ShareFileNewMessageDTO.Operation getOperation() {
		return operation;
	}

	@Override
	public void setOperation(ShareFileNewMessageDTO.Operation operation) {
		this.operation = operation;
	}

}
