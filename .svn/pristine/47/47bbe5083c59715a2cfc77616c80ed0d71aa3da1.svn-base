package com.conlect.oatos.dto.client;

import java.util.Date;

import com.conlect.oatos.dto.autobean.IPrivateFolderUpdateDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

/**
 * 个人网盘文件夹修改dto
 * 
 * @author yang
 * 
 */
public class PrivateFolderUpdateDTO extends NetworkFolderDTO implements
		IPrivateFolderUpdateDTO {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作<br> {@link ShareFileNewMessageDTO.Operation}
	 */
	private Operation operation;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 备注
	 */
	private String comment;

	public PrivateFolderUpdateDTO() {
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
	public Date getUpdateTime() {
		return updateTime;
	}

	@Override
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
