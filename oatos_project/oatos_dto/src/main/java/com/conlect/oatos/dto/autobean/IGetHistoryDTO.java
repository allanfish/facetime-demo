package com.conlect.oatos.dto.autobean;

import java.io.Serializable;

public interface IGetHistoryDTO extends Serializable {

	long getUserId();

	void setUserId(long userId);

	long getBuddyUserId();

	void setBuddyUserId(long buddyUserId);

	int getSkipResults();

	void setSkipResults(int skipResults);

	int getMaxResults();

	void setMaxResults(int maxResults);
}
