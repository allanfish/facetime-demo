package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IPrivateFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

/**
 * 个人网盘文件修改dto
 * 
 * @author yang
 * 
 */
public class PrivateFileUpdateDTO extends NetworkFileDTO implements
		IPrivateFileUpdateDTO {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作<br> {@link ShareFileNewMessageDTO.Operation}
	 */
	private Operation operation;

	/**
	 * 备注
	 */
	private String comment;

	public PrivateFileUpdateDTO() {
	}

	@Override
	public Operation getOperation() {
		return operation;
	}

	@Override
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

}
