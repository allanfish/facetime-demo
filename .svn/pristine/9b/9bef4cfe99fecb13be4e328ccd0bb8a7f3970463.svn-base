package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.Block;

/**
 * 阻止联系人实体层
 * 
 * @author yang
 * 
 */
public interface BlockListMapper {

	/**
	 * 删除阻止联系人
	 * 
	 * @param userId
	 */
	void deleteBlockListByUserId(long userId);

	/**
	 * 添加阻止联系人
	 * 
	 * @param blockList
	 */
	void addBlockList(List<Block> blockList);

	/**
	 * 获取用户设置的阻止联系人
	 * 
	 * @param userId
	 * @return
	 */
	List<Block> getBlockListByUserId(long userId);

	/**
	 * 删除阻止联系人
	 * 
	 * @param userId
	 */
	void deleteBlockList(long userId);

	/**
	 * 删除管理员设置的阻止联系人
	 * 
	 * @param userId
	 */
	void deleteEnterpriseBlockByUserId(long userId);

	/**
	 * 获取管理员设置的阻止联系人
	 * 
	 * @param userId
	 * @return
	 */
	List<Block> getEnterpriseBlocksByUserId(long userId);

	/**
	 * 删除阻止
	 * 
	 * @param blockModel
	 */
	void deleteBlock(Block blockModel);

	/**
	 * 添加阻止
	 * 
	 * @param blockModel
	 */
	void addBlock(Block blockModel);

	/**
	 * 删除管理员设置的阻止
	 * 
	 * @param blockModel
	 */
	void deleteEnterpriseBlock(Block blockModel);

	/**
	 * 获取被那些人阻止
	 * 
	 * @param userId
	 * @return
	 */
	List<Block> getBlockedByListByUserId(long userId);
}
