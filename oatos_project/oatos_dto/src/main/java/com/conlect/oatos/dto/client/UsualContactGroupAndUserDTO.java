package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 常用联系人分组list及联系人信息list
 * 
 * @author yang
 * 
 */
@SuppressWarnings("serial")
public class UsualContactGroupAndUserDTO implements BaseDTO {

	/**
	 * 分组list
	 */
	private List<UsualContactGroupDTO> groupList;

	/**
	 * 联系人信息list
	 */
	private List<UserInfoDTO> userList;

	public UsualContactGroupAndUserDTO() {
	}

	public List<UsualContactGroupDTO> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<UsualContactGroupDTO> groupList) {
		this.groupList = groupList;
	}

	public List<UserInfoDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfoDTO> userList) {
		this.userList = userList;
	}
}
