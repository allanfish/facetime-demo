package com.qycloud.oatos.server.domain.model;

import java.io.Serializable;

public class GroupMemberModel implements Serializable
{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private long userId;
	private long groupId;
	private int confirm;
	private String userName;
	private String userHeaderPhoto;
	
	public void setUserId(long userId)
    {
	    this.userId = userId;
    }
	public long getUserId()
    {
	    return userId;
    }
	
	public void setGroupId(long groupId)
    {
	    this.groupId = groupId;
    }
	public long getGroupId()
    {
	    return groupId;
    }
	public void setConfirm(int confirm)
    {
	    this.confirm = confirm;
    }
	public int getConfirm()
    {
	    return confirm;
    }
	public void setUserName(String userName)
    {
	    this.userName = userName;
    }
	public String getUserName()
    {
	    return userName;
    }
	public void setUserHeaderPhoto(String userHeaderPhoto)
    {
	    this.userHeaderPhoto = userHeaderPhoto;
    }
	public String getUserHeaderPhoto()
    {
	    return userHeaderPhoto;
    }

}
