package com.conlect.oatos.dto.client;

/**
 * 常用联系人分组
 * 
 * @author yang
 * 
 */
@SuppressWarnings("serial")
public class UsualContactGroupDTO implements BaseDTO {

	/**
	 * 分组id
	 */
	private long groupId;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 分组名
	 */
	private String name;

	/**
	 * 排序
	 */
	private int orderValue;

	public UsualContactGroupDTO() {
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(int orderValue) {
		this.orderValue = orderValue;
	}

}
