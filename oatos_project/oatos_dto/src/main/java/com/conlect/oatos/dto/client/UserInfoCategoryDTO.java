package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 
 * 用户信息分类DTO
 * 
 * @author YUFEI
 */
public class UserInfoCategoryDTO implements BaseDTO {

	private static final long serialVersionUID = -4960241003267462110L;
	/**
	 * 在线用户列表
	 */
	private List<Long> onlineUserIds;

	/**
	 * 离线用户列表
	 */
	private List<Long> offlineUserIds;

	public UserInfoCategoryDTO() {
		super();
	}

	public UserInfoCategoryDTO(List<Long> onlineUserIds,
			List<Long> offlineUserIds) {
		super();
		this.onlineUserIds = onlineUserIds;
		this.offlineUserIds = offlineUserIds;
	}

	public List<Long> getOnlineUserIds() {
		return onlineUserIds;
	}

	public void setOnlineUserIds(List<Long> onlineUserIds) {
		this.onlineUserIds = onlineUserIds;
	}

	public List<Long> getOfflineUserIds() {
		return offlineUserIds;
	}

	public void setOfflineUserIds(List<Long> offlineUserIds) {
		this.offlineUserIds = offlineUserIds;
	}

}
