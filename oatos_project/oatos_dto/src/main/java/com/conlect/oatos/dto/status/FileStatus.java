package com.conlect.oatos.dto.status;

public interface FileStatus {

	// =====文件删除状态=====
	/**
	 * active
	 */
	int active = 0;
	/**
	 * deleted in recycle
	 */
	int recycle = 1;
	/**
	 * deleted
	 */
	int deleted = -1;

	// =====文件处理状态=====

	/**
	 * 上传到文件cache
	 */
	String UPLOAD_TO_CACHE = "0";

	/**
	 * 同步到文件服务器
	 */
	String UPLOAD_TO_FS = "1";

	/**
	 * 正在处理转换
	 */
	String CONVERT_START = "2";

	/**
	 * 转换完成
	 * 
	 */
	String CONVERT_DONE = "3";

	/**
	 * 转换失败
	 * 
	 */
	String CONVERT_ERROR = "4";

}
