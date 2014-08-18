package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IChatGroupMemberDTO;

/**
 * 群聊成员
 * 
 * @author PeterGuo
 */
public class ChatGroupMemberDTO implements BaseDTO, IChatGroupMemberDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 群ID
	 */
	public long groupId;

	/**
	 * 申请者邀请要加入的好友ID
	 */
	public long memberUserId;

	/**
	 * 申请者Id
	 */
	public long userId;
	/**
	 * 成员名字
	 */
	public String memberName;
	/**
	 * 用户头像照片
	 */
	public String userHeaderPhoto;

	public ChatGroupMemberDTO() {
	}

	public ChatGroupMemberDTO(long groupId, long memberUserId, long userId,
			String userName, String userHeaderPhoto) {
		this.groupId = groupId;
		this.memberUserId = memberUserId;
		this.userId = userId;
		this.memberName = userName;
		this.userHeaderPhoto = userHeaderPhoto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IChatGroupMemberDTO#getGroupId()
	 */
	@Override
	public long getGroupId() {
		return groupId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IChatGroupMemberDTO#setGroupId(long)
	 */
	@Override
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	/**
	 * {@link com.conlect.oatos.dto.autobean.IBuddyDTO#getBuddyUserId()}
	 */
	@Override
	public long getMemberUserId() {
		return memberUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IChatGroupMemberDTO#setMemberUserId(long)
	 */
	@Override
	public void setMemberUserId(long memberUserId) {
		this.memberUserId = memberUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IChatGroupMemberDTO#getUserId()
	 */
	@Override
	public long getUserId() {
		return userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IChatGroupMemberDTO#setUserId(long)
	 */
	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.conlect.oatos.dto.client.IChatGroupMemberDTO#getMemberName()
	 */
	@Override
	public String getMemberName() {
		return memberName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IChatGroupMemberDTO#setMemberName(java.lang
	 * .String)
	 */
	@Override
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IChatGroupMemberDTO#getUserHeaderPhoto()
	 */
	@Override
	public String getUserHeaderPhoto() {
		return userHeaderPhoto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.conlect.oatos.dto.client.IChatGroupMemberDTO#setUserHeaderPhoto(java
	 * .lang.String)
	 */
	@Override
	public void setUserHeaderPhoto(String userHeaderPhoto) {
		this.userHeaderPhoto = userHeaderPhoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (groupId ^ (groupId >>> 32));
		result = prime * result + (int) (memberUserId ^ (memberUserId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatGroupMemberDTO other = (ChatGroupMemberDTO) obj;
		if (groupId != other.groupId)
			return false;
		if (memberUserId != other.memberUserId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChatGroupMemberDTO [groupId=" + groupId + ", memberUserId="
				+ memberUserId + ", userId=" + userId + ", memberName="
				+ memberName + ", userHeaderPhoto=" + userHeaderPhoto + "]";
	}

}
