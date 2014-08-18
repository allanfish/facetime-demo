/**
 * 
 */
package com.qycloud.oatos.server.domain.entity;

import java.io.Serializable;


/**
 * 企业个人收藏夹实体
 * @author huhao
 *
 */
public class FavoriteFile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int fileId;
	
	public FavoriteFile(){
		
	}
	
	/**
	 * @param userId
	 * @param fileId
	 */
	public FavoriteFile(int userId, int fileId) {
		this.userId = userId;
		this.fileId = fileId;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the fileId
	 */
	public int getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	
	
	
}
