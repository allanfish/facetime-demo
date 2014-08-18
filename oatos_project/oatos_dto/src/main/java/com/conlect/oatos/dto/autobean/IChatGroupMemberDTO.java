package com.conlect.oatos.dto.autobean;

public interface IChatGroupMemberDTO {

	public abstract long getGroupId();

	public abstract void setGroupId(long groupId);

	public abstract long getMemberUserId();

	public abstract void setMemberUserId(long memberUserId);

	public abstract long getUserId();

	public abstract void setUserId(long userId);

	public abstract String getMemberName();

	public abstract void setMemberName(String memberName);

	public abstract String getUserHeaderPhoto();

	public abstract void setUserHeaderPhoto(String userHeaderPhoto);

}