package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.autobean.IUsualContactDTO;

/**
 * 常用联系人DTO
 * 
 * @author PengfeiLiu
 * 
 */
public class UsualContactDTO implements BaseDTO, IUsualContactDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 常用联系人分组id
	 */
	private Long groupId;

	/**
	 * 常用联系人的ID列表
	 */
	private List<Long> usualContactUserList = new ArrayList<Long>();

	public UsualContactDTO() {
	}

	public UsualContactDTO(long userId) {
		this.userId = userId;
	}

	public UsualContactDTO(long userId, List<Long> blackContactList) {
		this.userId = userId;
		this.usualContactUserList = blackContactList;
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
	public List<Long> getUsualContactUserList() {
		return usualContactUserList;
	}

	@Override
	public void setUsualContactUserList(List<Long> blackUserList) {
		this.usualContactUserList = blackUserList;
	}

	@Override
	public Long getGroupId() {
		return groupId;
	}

	@Override
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
