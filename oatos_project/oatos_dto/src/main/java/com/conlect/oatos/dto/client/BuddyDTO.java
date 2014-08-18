package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IBuddyDTO;
import com.conlect.oatos.dto.status.UserAgent;

/**
 * 好友资料
 * 
 * @author PeterGuo
 * @deprecated
 */
@Deprecated
public class BuddyDTO implements IBuddyDTO {
	private static final long serialVersionUID = 1L;

	// 关系ID
	public long Id;
	// 用户ID
	public long userId;
	// 好友的ID
	public long buddyUserId;
	// 好友的邮件帐户(cmail账户)
	public String buddyUserMail = "";
	// 注册用的邮件
	public String registerMail = "";
	// 好友别名
	public String nickName = "";
	// 好友所在的组ID
	public long buddyGroupId;
	// 好友所在的组名称
	public String buddyGroupName = "";
	// 好友状态
	public String buddyStatus = "";
	// 好友头像
	public String icon = "";
	// 好友签名
	public String signature = "";
	// 软电话/分机
	public String buddyExt = "";

	public String agent = UserAgent.Web;

	public BuddyDTO() {
	}

	public BuddyDTO(long id, long userId, long buddyUserId,
			String buddyUserMail, String nickName, long buddyGroupId,
			String buddyGroupName, String buddyStatus, String icon,
			String signature, String buddyExt) {
		super();
		this.Id = id;
		this.userId = userId;
		this.buddyUserId = buddyUserId;
		this.buddyUserMail = buddyUserMail;
		this.nickName = nickName;
		this.buddyGroupId = buddyGroupId;
		this.buddyGroupName = buddyGroupName;
		this.buddyStatus = buddyStatus;
		this.icon = icon;
		this.signature = signature;
		this.buddyExt = buddyExt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
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
		BuddyDTO other = (BuddyDTO) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

	/**
	 * Get display name from buddy, if nickName is not null and "null" and
	 * space, return userName else return registerMailAccount
	 * 
	 * @return
	 */
	public String displayName() {
		if (nickName != null && !nickName.equalsIgnoreCase("null")
				&& !nickName.trim().equals("")) {
			return nickName;
		}
		return buddyUserMail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" {\"Id\":\"").append(Id).append("\", \"userId\":\"")
				.append(userId).append("\", \"buddyUserId\":\"")
				.append(buddyUserId).append("\", \"buddyUserMail\":\"")
				.append(buddyUserMail).append("\", \"registerMail\":\"")
				.append(registerMail).append("\", \"nickName\":\"")
				.append(nickName).append("\", \"buddyGroupId\":\"")
				.append(buddyGroupId).append("\", \"buddyGroupName\":\"")
				.append(buddyGroupName).append("\", \"buddyStatus\":\"")
				.append(buddyStatus).append("\", \"icon\":\"").append(icon)
				.append("\", \"signature\":\"").append(signature)
				.append("\", \"buddyExt\":\"").append(buddyExt).append("\"}");
		return builder.toString();
	}

	@Override
	public long getId() {
		return Id;
	}

	@Override
	public void setId(long id) {
		Id = id;
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
	public long getBuddyUserId() {
		return buddyUserId;
	}

	@Override
	public void setBuddyUserId(long buddyUserId) {
		this.buddyUserId = buddyUserId;
	}

	@Override
	public String getBuddyUserMail() {
		return buddyUserMail;
	}

	@Override
	public void setBuddyUserMail(String buddyUserMail) {
		this.buddyUserMail = buddyUserMail;
	}

	@Override
	public String getRegisterMail() {
		return registerMail;
	}

	@Override
	public void setRegisterMail(String registerMail) {
		this.registerMail = registerMail;
	}

	@Override
	public String getNickName() {
		return displayName();
	}

	@Override
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public long getBuddyGroupId() {
		return buddyGroupId;
	}

	@Override
	public void setBuddyGroupId(long buddyGroupId) {
		this.buddyGroupId = buddyGroupId;
	}

	@Override
	public String getBuddyGroupName() {
		return buddyGroupName;
	}

	@Override
	public void setBuddyGroupName(String buddyGroupName) {
		this.buddyGroupName = buddyGroupName;
	}

	@Override
	public String getBuddyStatus() {
		return buddyStatus;
	}

	@Override
	public void setBuddyStatus(String buddyStatus) {
		this.buddyStatus = buddyStatus;
	}

	@Override
	public String getIcon() {
		return icon;
	}

	@Override
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String getSignature() {
		return signature;
	}

	@Override
	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String getAgent() {
		return agent;
	}

	@Override
	public void setAgent(String agent) {
		this.agent = agent;
	}

}
