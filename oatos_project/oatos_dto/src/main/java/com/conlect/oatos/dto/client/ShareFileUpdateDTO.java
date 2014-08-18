package com.conlect.oatos.dto.client;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.IShareFileUpdateDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

/**
 * 企业网盘文件修改dto
 * 
 * @author yang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ShareFileUpdateDTO extends ShareFileDTO implements
		IShareFileUpdateDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 操作<br> {@link com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation}
	 */
	private Operation operation;

	/**
	 * 备注
	 */
	private String comment;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 版本恢复时用户id
	 */
	private long restoreUserId;

	public ShareFileUpdateDTO() {
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

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public long getRestoreUserId() {
		return restoreUserId;
	}

	@Override
	public void setRestoreUserId(long restoreUserId) {
		this.restoreUserId = restoreUserId;
	}

}
