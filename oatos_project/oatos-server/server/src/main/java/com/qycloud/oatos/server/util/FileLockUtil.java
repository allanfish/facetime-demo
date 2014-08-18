package com.qycloud.oatos.server.util;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;
import com.qycloud.oatos.server.domain.model.SimpleUserModel;

public class FileLockUtil {

	/**
	 * 锁定文件
	 * @param userId
	 * @param fileId
	 * @param userName
	 * @return
	 */
	public static String lockFile(long userId, long fileId, String userName) {
		// 检查文件锁定状态
		String result = checkLockFile(userId, fileId);
		if (CommConstants.OK_MARK.equals(result)) {
			// 锁定文件
			String key = CacheKey.FileLock + fileId;
			SimpleUserModel lock = new SimpleUserModel(userId, userName);
			CacheClient.getInstance().set(key, lock);
		}
		return result;
	}

	/**
	 * 取文件的锁定状态
	 * @param fileId
	 * @return
	 */
	public static SimpleUserModel getFileLockStatus(long fileId) {
		SimpleUserModel lock = null;
		String key = CacheKey.FileLock + fileId;
		Object object = CacheClient.getInstance().get(key);
		if (object != null) {
			lock = (SimpleUserModel) object;
		}
		return lock;
	}

	/**
	 * 检查文件锁定状态
	 * @param userId
	 * @param fileId
	 * @return
	 */
	public static String checkLockFile(long userId, long fileId) {
		String result = CommConstants.OK_MARK;
		SimpleUserModel lock = getFileLockStatus(fileId);
		if (lock != null && lock.getUserId() != userId) {
			result = ErrorType.errorFileLocked.name() + ":" + lock.getName();
		}
		return result;
	}

}
