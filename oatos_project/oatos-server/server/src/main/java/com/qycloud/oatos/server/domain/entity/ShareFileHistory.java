package com.qycloud.oatos.server.domain.entity;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.ShareFileNewMessageDTO.Operation;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.status.CommConstants;

/**
 * 企业网盘文件历史记录
 * @author yang
 *
 */
public class ShareFileHistory extends ShareFile {

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

	public ShareFileHistory() {
	}

	public ShareFileHistory(ShareFile file) {
		BeanUtils.copyProperties(file, this);
	}

	public ShareFileHistory(ShareFileUpdateDTO historyDTO) {
		super(historyDTO);
		operation = historyDTO.getOperation().name();
		comment = historyDTO.getComment();
		userId = historyDTO.getUserId();
		userName = historyDTO.getUserName();
	}

	public ShareFileUpdateDTO toHistoryDTO() {
		ShareFileUpdateDTO historyDTO = new ShareFileUpdateDTO();
		historyDTO.setFileId(getFileId());
		historyDTO.setFolderId(getFolderId());
		historyDTO.setEnterpriseId(getEnterpriseId());
		historyDTO.setName(getName());
		historyDTO.setSize(getSize());
		historyDTO.setType(CommConstants.FILE_TYPE_SHAREDISK);
		historyDTO.setGuid(getGuid());
		historyDTO.setCreateTime(getCreateTime());
		historyDTO.setUpdateTime(getUpdateTime());
		historyDTO.setDeleted(getDeleted());
		historyDTO.setShareLinkId(getShareLinkId());
		historyDTO.setVersion(getVersion());
		historyDTO.setThumb(getThumb());
		historyDTO.setRemark(getRemark());
		historyDTO.setOperation(Operation.valueOf(operation));
		historyDTO.setComment(comment);
		historyDTO.setUserId(userId);
		historyDTO.setUserName(userName);
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

}
