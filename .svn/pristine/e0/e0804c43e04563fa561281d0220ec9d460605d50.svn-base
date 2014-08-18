package com.qycloud.oatos.server.domain.entity;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;
import com.conlect.oatos.dto.client.ShareFolderUpdateDTO;

/**
 * 企业网盘文件夹历史记录
 * @author yang
 *
 */
public class ShareFolderHistory extends ShareFolder {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作<br>
	 * {@link com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation}
	 */
	private String operation;

	/**
	 * 备注
	 */
	private String comment;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public ShareFolderHistory() {
	}

	public ShareFolderHistory(ShareFolder folder) {
		BeanUtils.copyProperties(folder, this);
	}

	public ShareFolderHistory(ShareFolderUpdateDTO historyDTO) {
		super(historyDTO);
		operation = historyDTO.getOperation().name();
		comment = historyDTO.getComment();
		userId = historyDTO.getUserId();
		userName = historyDTO.getUserName();
		updateTime = historyDTO.getUpdateTime();
	}

	public ShareFolderUpdateDTO toHistoryDTO() {
		ShareFolderUpdateDTO historyDTO = new ShareFolderUpdateDTO();
		historyDTO.setFolderId(getFolderId());
		historyDTO.setEnterpriseId(getEnterpriseId());
		historyDTO.setName(getName());
		historyDTO.setParentId(getParentId());
		historyDTO.setDeleted(getDeleted());
		historyDTO.setMaxSize(getMaxSize());
		historyDTO.setShareLinkId(getShareLinkId());
		historyDTO.setVersion(getVersion());
		historyDTO.setThumb(getThumb());
		historyDTO.setRemark(getRemark());
		historyDTO.setOperation(Operation.valueOf(operation));
		historyDTO.setComment(comment);
		historyDTO.setUserId(userId);
		historyDTO.setUserName(userName);
		historyDTO.setUpdateTime(updateTime);
		return historyDTO;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
