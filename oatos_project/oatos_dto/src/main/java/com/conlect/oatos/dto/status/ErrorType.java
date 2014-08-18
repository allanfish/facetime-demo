package com.conlect.oatos.dto.status;

/**
 * 错误类型常量
 * 
 * @author huhao
 * 
 */

public enum ErrorType {
	error404, error500, errorCheckToken, errorRequestData, errorCheckHashkey, errorWrongAccount, errorWrongPWD, errorWrongOldPWD, errorConnotSameBuddy, errorInactive, errorAlreadyActivated, errorCreateFriedGroup, errorFriendAlreadyExist, errorSameFile, errorSameFolder, errorNoSpace, errorUserIsOffline, errorConlectIsExist, errorNullOfGroup, errorExitMemeber, errorUserNoOver, errorUserAlreadyExist, errorEnterpriseNotExist, errorWrongEnterpriseName, errorEmployeeAlreadyExist, errorInvalidProductKey, errorEnterpriseAlreadyExist, errorFileNotFound,
	/**
	 * 文件被他人锁定
	 */
	errorFileLocked, errorNoPermission, errorUserLocked, errorNotSupported, errorMQDisconnected, errorFolderSpaceOver, errorProductKeyFree, errorAccountExpired, errorConferenceMemberOver, errorDownCountOver, errorFlowOver, errorExpirationTimeOver,
	/**
	 * 版本冲突
	 */
	errorVersionConflict,
	/** 注册审核中 */
	errorAuditing,
	/**
	 * 企业服务已停止
	 */
	errorAuditFail, errorSameName,
	/**
	 * ldap中不存在该用户
	 */
	errorUserNoExistInLdap,
	/**
	 * 域用户名或密码错误
	 */
	errorWrongLDAPAccount,
	/**
	 * 域用户已经被锁定
	 */
	errorLDAPUserLocked,
	/**
	 * 购买的增值服务已经到期
	 */
	errorPayExpired,
	/** 企业网盘空间大于免费空间 */
	errorEntDiskGreetThanFreeSize,
	/**
	 * 订单不存在
	 */
	errorOrderNotExist,
	/**
	 * 个人网盘空间超出异常
	 */
	errorPersonDiskOverflow,
	/**
	 * 个人网盘已使用空间超出了分配的大小
	 */
	errorPersonDiskUsedExceedAllocSize,
	/**
	 * 当部门员工存在时, 删除部门错误
	 */
	errorDeleteDepartUserExist,
	/**
	 * 文件夹已经被删除
	 */
	errorFolderDeleted
}
