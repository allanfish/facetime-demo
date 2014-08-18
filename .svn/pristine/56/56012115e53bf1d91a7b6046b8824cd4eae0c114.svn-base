package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.ShareFileRecord;

/**
 * 文件操作记录
 * @author yang
 *
 */
public interface ShareFileRecordMapper {

	/**
	 * 插入文件操作记录
	 * @param record
	 */
	void insertRecord(ShareFileRecord record);

	/**
	 * 取文件操作记录
	 * @param userId
	 * @param type
	 * @return
	 */
	List<ShareFileRecord> getRecordByUserIdAndType(
			@Param("userId") long userId, @Param("type") String type);
}
