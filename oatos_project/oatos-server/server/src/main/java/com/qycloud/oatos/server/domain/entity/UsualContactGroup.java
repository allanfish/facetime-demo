package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;

import com.conlect.oatos.dto.client.UsualContactGroupDTO;

/**
 * 常用联系人分组表
 * @author huhao
 *
 */

public class UsualContactGroup implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 组的ID
	 */
	private long groupId;
	
	/**
	 * 用户的ID
	 */
	private long userId;
	
	/**
	 * 组的序号
	 */
	private int orderValue;
	
	/**
	 * 组名
	 */
	private String name;
	
	
	/**
	 * 
	 */
	public UsualContactGroup(){
		
	}

	public UsualContactGroup(UsualContactGroupDTO usualContactGroupDTO) {
		this.name = usualContactGroupDTO.getName();
		this.groupId = usualContactGroupDTO.getGroupId();
		this.orderValue = usualContactGroupDTO.getOrderValue();
		this.userId = usualContactGroupDTO.getUserId();
		
	}
	
	public UsualContactGroupDTO toUsualContactGroupDTO() {
		UsualContactGroupDTO usualContactGroupDTO = new UsualContactGroupDTO();
		usualContactGroupDTO.setGroupId(groupId);
		usualContactGroupDTO.setName(name);
		usualContactGroupDTO.setOrderValue(orderValue);
		usualContactGroupDTO.setUserId(userId);
		return usualContactGroupDTO;
		
		
	}
	
	

	/**
	 * @return the groupId
	 */
	public long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}



	/**
	 * @return the order
	 */
	public int getOrderValue() {
		return orderValue;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrderValue(int order) {
		this.orderValue = order;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
	
	
	

}
