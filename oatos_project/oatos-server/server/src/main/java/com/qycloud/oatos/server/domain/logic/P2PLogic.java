package com.qycloud.oatos.server.domain.logic;

import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.status.CallStatus;
import com.conlect.oatos.dto.status.ErrorType;
import com.qycloud.oatos.server.domain.model.CachedUser;
import com.qycloud.oatos.server.util.CacheClient;
import com.qycloud.oatos.server.util.CacheKey;
import com.qycloud.oatos.server.util.LogicException;

/**
 * p2p视频通话，语音通话后台数据服务
 * @author yang
 *
 */
@Service("P2PLogic")
public class P2PLogic {
	/**
	 * 保存用户的标志key
	 * 
	 * @param identity
	 * @param userId
	 */
	public void setIdentityKey(String identity, long userId) {
		// 更新缓存
		String key = CacheKey.Online + userId;
		Object object = CacheClient.getInstance().get(key);
		if (object != null) {
			CachedUser model = (CachedUser) object;
			model.setP2pKey(identity);
			CacheClient.getInstance().set(key, model);
		}
		else {
			throw new LogicException(ErrorType.errorUserIsOffline);
		}
	}

	/**
	 * 取用户的标志key
	 * 
	 * @return
	 */
	public String getFriendIdentityKey(long userId) {
		String key = CacheKey.Online + userId;
		Object object = CacheClient.getInstance().get(key);
		if (object != null) {
			CachedUser model = (CachedUser) object;
			return model.getP2pKey();
		}
		else {
			throw new LogicException(ErrorType.errorUserIsOffline);
		}
	}

	/**
	 * 修改用户的通话状态
	 * @param userId
	 * @param status
	 */
	public void setCallStatus(long userId, String status) {
		String key = CacheKey.Online + userId;
		Object object = CacheClient.getInstance().get(key);
		if (object != null) {
			CachedUser model = (CachedUser) object;
			model.setCallStatus(status);
			CacheClient.getInstance().set(key, model);
		}
		else {
			throw new LogicException(ErrorType.errorUserIsOffline);
		}
	}

	/**
	 * 取用户的通话状态
	 * @param userId
	 * @return
	 */
	public String getCallStatus(long userId) {
		String key = CacheKey.Online + userId;
		Object object = CacheClient.getInstance().get(key);
		if (object != null) {
			CachedUser model = (CachedUser) object;
			return model.getCallStatus();
		} else {
			return CallStatus.Offline;
		}
	}
}
