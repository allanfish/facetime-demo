package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.autobean.IBlocksDTO;

/**
 * block list dto
 * 
 * @author yang
 * 
 */
public class BlocksDTO implements IBlocksDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private List<Long> userList = new ArrayList<Long>();

	/**
	 * 阻止通信的用户ID
	 */
	private List<Long> blockUserList = new ArrayList<Long>();

	@Override
	public List<Long> getUserList() {
		return userList;
	}

	@Override
	public void setUserList(List<Long> userList) {
		this.userList = userList;
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
