package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

/**
 * 
 * @author jinkerjiang
 * @deprecated
 */
@Deprecated
public interface IBuddyDTO extends Serializable {

	public long getId();

	public void setId(long id);

	public long getUserId();

	public void setUserId(long userId);

	public long getBuddyUserId();

	public void setBuddyUserId(long buddyUserId);

	public String getBuddyUserMail();

	public void setBuddyUserMail(String buddyUserMail);

	public String getRegisterMail();

	public void setRegisterMail(String registerMail);

	public String getNickName();

	public void setNickName(String nickName);

	public long getBuddyGroupId();

	public void setBuddyGroupId(long buddyGroupId);

	public String getBuddyGroupName();

	public void setBuddyGroupName(String buddyGroupName);

	public String getBuddyStatus();

	public void setBuddyStatus(String buddyStatus);

	public String getIcon();

	public void setIcon(String icon);

	public String getSignature();

	public void setSignature(String signature);

	String getAgent();

	void setAgent(String agent);
}
