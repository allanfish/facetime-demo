package com.conlect.oatos.dto.status;

import com.conlect.oatos.dto.client.BaseDTO;
import com.conlect.oatos.dto.client.ClientToken;

/**
 * 管理员token
 * 
 * @author yang
 * 
 */
public class AdminToken implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 企业id
	 */
	private long enterpriseId;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * token，不包含用户id和企业id
	 */
	private String token;
	/**
	 * 客户端安全验证token
	 */
	private String clientToken;
	/**
	 * 登录成功后台返回的完整token
	 */
	private String tokenStr;

	public AdminToken() {
	}

	public AdminToken(String t) {
		tokenStr = t;
		String[] strs = t.split(ClientToken.SEPARATOR);
		if (strs.length == 2) {
			enterpriseId = Long.parseLong(strs[0]);
			token = strs[1];
		} else {
			userId = Long.parseLong(strs[0]);
			enterpriseId = Long.parseLong(strs[1]);
			token = strs[2];
		}
		clientToken = String.valueOf(enterpriseId) + ClientToken.SEPARATOR
				+ token;
	}

	public String getClientToken() {
		return clientToken;
	}

	public String getTokenStr() {
		return tokenStr;
	}

	public long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return tokenStr;
	}

}
