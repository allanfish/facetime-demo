package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.ConferenceDoc;

/**
 * 视频会议实体层
 * 
 * @author yang
 * 
 */
public interface ConferenceDocMapper {

	/**
	 * 添加视频会议文档
	 * 
	 * @param doc
	 */
	void addConferenceDoc(ConferenceDoc doc);

	/**
	 * 批量添加视频会议文档
	 * 
	 * @param docList
	 */
	void addConferenceDocs(List<ConferenceDoc> docList);

	/**
	 * 获取视频会议文档
	 * 
	 * @param conId
	 * @return
	 */
	List<ConferenceDoc> getConferenceDocByConId(long conId);

	/**
	 * 删除视频会议文档
	 * 
	 * @param docList
	 */
	void deleteConferenceDocs(List<ConferenceDoc> docList);

	/**
	 * 根据文件名获取视频会议文档
	 * 
	 * @param doc
	 * @return
	 */
	ConferenceDoc getConferenceDocByConIdAndName(ConferenceDoc doc);

	/**
	 * 修改文档总页数
	 * 
	 * @param doc
	 */
	void updateDocPageCount(ConferenceDoc doc);

	/**
	 * 取单个文档信息
	 * @param fileId
	 * @return
	 */
	ConferenceDoc getConferenceDocById(long fileId);
}
