package com.conlect.oatos.dto.client;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.conlect.oatos.dto.autobean.IShareFolderUpdateDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;

/**
 * 企业网盘文件夹修改dto
 * 
 * @author yang
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ShareFolderUpdateDTO extends ShareFolderDTO implements
		IShareFolderUpdateDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 操作<br> {@link ShareFileNewMessageDTO.Operation}
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
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	public ShareFolderUpdateDTO() {
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
	public Date getUpdateTime() {
		return updateTime;
	}

	@Override
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}
}
