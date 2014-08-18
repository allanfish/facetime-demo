package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 常用联系人分组list
 * @author yang
 * 
 */
public class UsualContactGroupsDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分组list
	 */
	private List<UsualContactGroupDTO> groupList;

	public UsualContactGroupsDTO() {
	}

	public UsualContactGroupsDTO(List<UsualContactGroupDTO> groupList) {
		this.groupList = groupList;
	}

	public List<UsualContactGroupDTO> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<UsualContactGroupDTO> groupList) {
		this.groupList = groupList;
	}
}
