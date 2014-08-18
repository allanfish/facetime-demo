package com.qycloud.oatos.server.dao;

import java.util.List;

import com.qycloud.oatos.server.domain.entity.ProductKey;

/**
 * 产品key实体层
 * @author yang
 *
 */
public interface ProductKeyMapper {

	/**
	 * 插入key
	 * @param keyModel
	 */
	void addKey(ProductKey keyModel);

	/**
	 * 修改key信息
	 * @param keyModel
	 */
	void updateKey(ProductKey keyModel);

	/**
	 * 获取key信息
	 * @param key
	 * @return
	 */
	ProductKey getKey(String key);

	/**
	 * 按sale取key信息
	 * @param sales
	 * @return
	 */
	List<ProductKey> getKeyBySales(String sales);

	/**
	 * 取免费的key
	 * @return
	 */
	List<ProductKey> getFreeKeys();

	/**
	 * 取一个正式的key
	 * @return
	 */
	ProductKey getRandomPayedKey();
}
