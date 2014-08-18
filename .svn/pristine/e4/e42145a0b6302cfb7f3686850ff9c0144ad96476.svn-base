package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IClientToken;

/**
 * 用户认证令牌对象
 * 
 * @author PeterGuo
 */
public class ClientToken implements BaseDTO, IClientToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1239249104571832191L;
	// 令牌节分割符
	public static final String SEPARATOR = "@";
	// 用户ID
	private long userId;
	// 用户令牌, 默认是空字符串
	private String token = "";

	public ClientToken() {
	}

	public ClientToken(String userToken) {
		int post = userToken.indexOf(SEPARATOR);

		if (post > 0) {
			this.userId = Long.parseLong(userToken.substring(0, post));
			this.token = userToken.substring(post + 1);
		} else {
			this.userId = Long.parseLong(userToken);
		}
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
	public String getToken() {
		return token;
	}

	@Override
	public void setToken(String token) {
		this.token = token;
	}

	public String getUserIdAndToken() {
		return userId + SEPARATOR + token;
	}

	public String getUserIdAsString() {
		return String.valueOf(userId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		ClientToken other = (ClientToken) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" {\"userId\":\"").append(userId)
				.append("\", \"token\":\"").append(token).append("\"}");
		return builder.toString();
	}

}
