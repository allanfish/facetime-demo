/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qycloud.oatos.server.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author temp
 */
public class ChatGroupModel implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
	private long groupId;
    private String groupName;
    private Date createTime;
    private long creater;
    //群成员 
    private List<GroupMemberModel> members = new ArrayList<GroupMemberModel>();
    
    private String groupType;
    

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public long getCreater()
    {
        return creater;
    }

    public void setCreater(long creater)
    {
        this.creater = creater;
    }

    public long getGroupId()
    {
        return groupId;
    }

    public void setGroupId(long groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

	public void setGroupType(String groupType)
    {
	    this.groupType = groupType;
    }

	public String getGroupType()
    {
	    return groupType;
    }

	public void setMembers(List<GroupMemberModel> members)
    {
	    this.members = members;
    }

	public List<GroupMemberModel> getMembers()
    {
	    return members;
    }

}
