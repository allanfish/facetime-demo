package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IChatGroupDTO;

/**
 * 群聊组信息
 * 
 * @author PeterGuo
 */
public class ChatGroupDTO implements IChatGroupDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 群ID
	 */
	public long id;
	/**
	 * 群主用户ID
	 */
	public long userId;
	/**
	 * 群名称
	 */
	public String groupName;

	public ChatGroupDTO() {
	}

	public ChatGroupDTO(long id, long userId, String groupName) {
		this.id = id;
		this.userId = userId;
		this.groupName = groupName;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getGroupName() {
		return groupName;
	}

	@Override
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
