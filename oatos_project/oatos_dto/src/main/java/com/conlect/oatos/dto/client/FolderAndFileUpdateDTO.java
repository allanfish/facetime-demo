package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

/**
 * 个人网盘文件夹和文件更新dto
 * 
 * @author yang
 * 
 */
public class FolderAndFileUpdateDTO extends FolderAndFileDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 更新操作<br> {@link ShareFileNewMessageDTO.Operation}
	 */
	private Operation operation;

	/**
	 * 备注
	 */
	private String comment;

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
