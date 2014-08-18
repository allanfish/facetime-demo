/**
 * 
 */
package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.ShareFile;

/**
 * @author huhao
 * 
 */
public interface FavoriteFileMapper {

	/**
	 * 新增个人收藏夹
	 * 
	 * @param userId
	 * @param favoriteFileList
	 */
	void addFavoriteFile(@Param("userId") long userId,
			@Param("favoriteFileList") List<Long> favoriteFileList);

	/**
	 * 删除个人收藏夹
	 * 
	 * @param userId
	 * @param fileId
	 */
	void delFavoriteFile(@Param("userId") long userId,
			@Param("fileId") Long fileId);

	/**
	 * 获得个人收藏夹
	 * 
	 * @param userId
	 * @return
	 */
	List<ShareFile> getFavoriteFile(long userId);

	/**
	 * 取收藏的文件id
	 * 
	 * @param userId
	 * @return
	 */
	List<Long> getFavoriteFileIdByUserId(long userId);
}
