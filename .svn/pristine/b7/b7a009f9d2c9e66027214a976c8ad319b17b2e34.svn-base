package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.autobean.IBlockDTO;

/**
 * block dto
 * 
 * @author yang
 * 
 */
public class BlockDTO implements IBlockDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 阻止通信的用户ID列表
	 */
	private List<Long> blockUserList = new ArrayList<Long>();

	public BlockDTO() {
	}

	public BlockDTO(long userId) {
		this.userId = userId;
	}

	public BlockDTO(long userId, List<Long> blackList) {
		this.userId = userId;
		this.blockUserList = blackList;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public List<Long> getBlockUserList() {
		return blockUserList;
	}

	@Override
	public void setBlockUserList(List<Long> blackUserList) {
		this.blockUserList = blackUserList;
	}

}
