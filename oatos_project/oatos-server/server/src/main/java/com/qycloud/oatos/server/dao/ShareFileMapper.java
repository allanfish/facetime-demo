package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.ShareFile;

/**
 * 企业网盘文件实体层
 * @author yang
 * 
 */
public interface ShareFileMapper {

	/**
	 * 插入文件信息
	 * @param shareFile
	 */
	void addShareFile(ShareFile shareFile);

	/**
	 * 修改文件信息
	 * @param shareFile
	 */
	void updateShareFile(ShareFile shareFile);

	/**
	 * 批量更新文件
	 * @param files
	 */
	void updateShareFiles(List<ShareFile> files);

	/**
	 * 删除文件
	 * @param fileId
	 */
	void deleteShareFile(ShareFile file);

	/**
	 * 批量删除文件
	 * @param files
	 */
	void deleteShareFiles(List<ShareFile> files);

	/**
	 * 获取单个文件信息
	 * @param fileId
	 * @return
	 */
	ShareFile getShareFileById(long fileId);

	/**
	 * 取所有文件，不包括已删除和回收站中文件
	 * 
	 * @param enterpriseId
	 * @return
	 */
	List<ShareFile> getShareFilesByEntId(long entId);

	/**
	 * 取同名文件
	 * 
	 * @param shareFile
	 * @return
	 */
	ShareFile getSameShareFile(ShareFile shareFile);

	/**
	 * 取某个文件夹下文件
	 * 
	 * @param folderId
	 * @return
	 */
	List<ShareFile> getShareFilesByFolderId(long folderId);

	/**
	 * 取企业网盘已用空间
	 * 
	 * @param entId
	 * @return
	 */
	long getFileSizeSumByEntId(long entId);

	/**
	 * 取所有文件，包括回收站，不包括已删除的文件
	 * 
	 * @param enterpriseId
	 * @return
	 */
	List<ShareFile> getAllShareFilesByEntId(long enterpriseId);

	/**
	 * 取某个文件下文件总大小
	 * 
	 * @param folderId
	 * @return
	 */
	long getFileSizeSumByFolderId(long folderId);

	/**
	 * 修改文件页数
	 * @param fileId
	 * @param pageCount
	 */
	void updateShareFilePageCount(@Param("fileId") long fileId,
			@Param("pageCount") Integer pageCount);
	
	/**
	 * 通过企业ID获得企业网盘回收站中文件列表
	 * @param enterpriseId
	 * @return
	 */
	List<ShareFile> getShareFilesInRecycleByEnterpriseId(long enterpriseId);
}
