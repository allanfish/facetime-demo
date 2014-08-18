package com.qycloud.oatos.convert.test.logic;

import java.util.Date;

public class PoolObject {
	
	public static int no = 0;

	private Date createTime;
	
	private Date updateTime;
	
	private int index;

	public PoolObject() {
		no = no + 1;
		index = no;

	}

	public int getIndex() {
		return index;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
