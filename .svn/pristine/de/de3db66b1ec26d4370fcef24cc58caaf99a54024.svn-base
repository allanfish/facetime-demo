package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.conlect.oatos.dto.client.ConferenceMemberDTO;

/**
 * 视频会议成员
 * @author yang
 * 
 */
public class ConferenceMember implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
	 * 会议ID
	 */
	private long conferenceId;
	/**
	 * 用户ID
	 */
	private long userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户头像
	 */
	private String userIcon;
	/**
	 * 成员状态<br>
	 * {@link com.conlect.oatos.dto.status.ConferenceMemberStatus}
	 */
	private String status;

	/**
	 * 邀请者ID
	 */
	private Long inviteUserId;
	/**
	 * 邀请者姓名
	 */
	private String inviteUserName;
	/**
	 * 邀请时间
	 */
	private Date inviteTime;

	public ConferenceMember() {
	}

	public ConferenceMember(ConferenceMemberDTO memberDTO) {
		conferenceId = memberDTO.getConferenceId();
		userId = memberDTO.getUserId();
		userName = memberDTO.getUserName();
		userIcon = memberDTO.getUserIcon();
		status = memberDTO.getStatus();
		inviteUserId = memberDTO.getInviteUserId();
		inviteUserName = memberDTO.getInviteUserName();
		inviteTime = memberDTO.getInviteTime();
	}

	public ConferenceMemberDTO toConferenceMemberDTO() {
		ConferenceMemberDTO memberDTO = new ConferenceMemberDTO();
		memberDTO.setConferenceId(conferenceId);
		memberDTO.setUserId(userId);
		memberDTO.setUserName(userName);
		memberDTO.setUserIcon(userIcon);
		memberDTO.setStatus(status);
		memberDTO.setInviteUserId(inviteUserId);
		memberDTO.setInviteUserName(inviteUserName);
		memberDTO.setInviteTime(inviteTime);
		return memberDTO;
	}

	public long getConferenceId() {
		return conferenceId;
	}

	public void setConferenceId(long conferenceId) {
		this.conferenceId = conferenceId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getInviteUserId() {
		return inviteUserId;
	}

	public void setInviteUserId(Long inviteUserId) {
		this.inviteUserId = inviteUserId;
	}

	public String getInviteUserName() {
		return inviteUserName;
	}

	public void setInviteUserName(String inviteUserName) {
		this.inviteUserName = inviteUserName;
	}

	public Date getInviteTime() {
		return inviteTime;
	}

	public void setInviteTime(Date inviteTime) {
		this.inviteTime = inviteTime;
	}

}
