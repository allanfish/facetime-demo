package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品key
 * @author yang
 *
 */
public class ProductKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 产品key
	 */
	private String key;

	/**
	 * 卖家
	 * @deprecated
	 */
	private String sales;

	/**
	 * 是否已经使用
	 */
	private boolean used;

	/**
	 * 是否免费
	 */
	private boolean free = false;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public ProductKey() {
	}

	public ProductKey(String key, String sales) {
		super();
		this.key = key;
		this.sales = sales;
		this.used = false;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public boolean isFree() {
		return free;
	}

}
