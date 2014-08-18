package com.conlect.oatos.dto.client;

import java.util.List;

/**
 * 企业网盘文件list
 * 
 * @author yang
 * 
 */
public class ShareFilesDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private long userId;

	/**
	 * 企业网盘文件list
	 */
	private List<ShareFileDTO> shareFileList;

	/**
	 * 企业网盘已使用空间
	 * 
	 * @deprecated
	 */
	@Deprecated
	private long usedSize;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<ShareFileDTO> getShareFileList() {
		return shareFileList;
	}

	public void setShareFileList(List<ShareFileDTO> shareFileList) {
		this.shareFileList = shareFileList;
	}

	/**
	 * @deprecated
	 * @return
	 */
	@Deprecated
	public long getUsedSize() {
		return usedSize;
	}

	/**
	 * @deprecated
	 * @param usedSize
	 */
	@Deprecated
	public void setUsedSize(long usedSize) {
		this.usedSize = usedSize;
	}

}
