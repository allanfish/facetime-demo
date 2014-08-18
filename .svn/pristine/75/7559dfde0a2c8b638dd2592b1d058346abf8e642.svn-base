package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IGetHistoryDTO;

/**
 * 取聊天记录参数dto
 * 
 * @author yang
 * 
 */
public class GetHistoryDTO implements IGetHistoryDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 与之聊天的用户id
	 */
	private long buddyUserId;

	/**
	 * 跳过多少行
	 */
	private int skipResults;

	/**
	 * 每页行数
	 */
	private int maxResults;

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
	public int getSkipResults() {
		return skipResults;
	}

	@Override
	public void setSkipResults(int skipResults) {
		this.skipResults = skipResults;
	}

	@Override
	public int getMaxResults() {
		return maxResults;
	}

	@Override
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
}
