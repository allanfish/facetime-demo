package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

import com.conlect.oatos.dto.autobean.IFolderAndFileParamDTO;

/**
 * 
 * 查询网盘文件夹和文件的参数DTO
 * 
 */
public class FolderAndFileParamDTO implements IFolderAndFileParamDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 一个或者多个文件夹id
	 */
	private List<Long> folderIds = new ArrayList<Long>(0);

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public List<Long> getFolderIds() {
		return folderIds;
	}

	@Override
	public void setFolderIds(List<Long> folderIds) {
		this.folderIds = folderIds;
	}
}
