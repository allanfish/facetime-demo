package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.MailAttach;

/**
 * 邮件附件实体层
 * 
 * @author yang
 * 
 */
public interface MailAttachMapper {

	/**
	 * 添加附件
	 * @param attach
	 */
	void addAttach(MailAttach attach);

	/**
	 * 批量添加附件
	 * @param attachModels
	 */
	void addAttaches(List<MailAttach> attachModels);

	/**
	 * 获取邮件的附件列表
	 * @param mailId
	 * @return
	 */
	List<MailAttach> getAttachByMailId(long mailId);

	/**
	 * 修改附件文件ID
	 * @param attachId
	 * @param fileId
	 */
	void updateFileId(@Param("attachId") long attachId,
			@Param("fileId") long fileId);

	/**
	 * 获取附件信息
	 * @param attachId
	 * @return
	 */
	MailAttach getMailAttachById(long attachId);
}
