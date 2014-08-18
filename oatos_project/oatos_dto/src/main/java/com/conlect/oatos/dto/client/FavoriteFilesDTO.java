package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业个人收藏夹列表
 * 
 * @author huhao
 * 
 */
public class FavoriteFilesDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private long userId;

	/**
	 * 文件id,list
	 */
	private List<Long> favoriteFileIdList = new ArrayList<Long>();

	/**
	 * @param userId
	 * @param favoriteFileIdList
	 */
	public FavoriteFilesDTO(long userId, List<Long> favoriteFileIdList) {
		this.userId = userId;
		this.favoriteFileIdList = favoriteFileIdList;
	}

	public FavoriteFilesDTO() {

	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the favoriteFileIdList
	 */
	public List<Long> getFavoriteFileIdList() {
		return favoriteFileIdList;
	}

	/**
	 * @param favoriteFileIdList
	 *            the favoriteFileIdList to set
	 */
	public void setFavoriteFileIdList(List<Long> favoriteFileIdList) {
		this.favoriteFileIdList = favoriteFileIdList;
	}

}
