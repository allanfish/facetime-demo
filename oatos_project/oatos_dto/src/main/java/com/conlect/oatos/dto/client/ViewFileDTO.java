package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IConferenceDocDTO;
import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.IShareFileDTO;
import com.conlect.oatos.dto.autobean.IViewFileDTO;
import com.conlect.oatos.dto.status.CommConstants;

/**
 * 浏览文件dto
 * 
 * @author yang
 * 
 */
public class ViewFileDTO extends FileDTO implements IViewFileDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 文件所有者id 企业网盘文件为企业id；个人网盘文件为用户id
	 */
	private long fromId;

	/**
	 * 当前页码
	 */
	private int currentPage = 1;

	/**
	 * 是否保存
	 */
	private boolean saved = true;
	/**
	 * 是否检查权限
	 */
	private boolean checkPermission;

	@Override
	public boolean isCheckPermission() {
		return checkPermission;
	}

	@Override
	public void setCheckPermission(boolean checkPermission) {
		this.checkPermission = checkPermission;
	}

	public ViewFileDTO() {
	}

	public ViewFileDTO(INetworkFileDTO fileDTO) {
		setUserId(fileDTO.getUserId());
		setFileId(fileDTO.getFileId());
		setFromId(fileDTO.getUserId());
		setGuid(fileDTO.getGuid());
		setName(fileDTO.getName());
		setFolderId(fileDTO.getFolderId());
		setSize(fileDTO.getSize());
		setType(CommConstants.FILE_TYPE_ONLINEDISK);
		setSaved(true);
		setThumb(fileDTO.getThumb());
		setRemark(fileDTO.getRemark());
		setPageCount(fileDTO.getPageCount());
		checkPermission = false;
	}

	public ViewFileDTO(IShareFileDTO fileDTO) {
		setFileId(fileDTO.getFileId());
		setUserId(fileDTO.getUserId());
		setEnterpriseId(fileDTO.getEnterpriseId());
		setFromId(fileDTO.getEnterpriseId());
		setFolderId(fileDTO.getFolderId());
		setName(fileDTO.getName());
		setGuid(fileDTO.getGuid());
		setSize(fileDTO.getSize());
		setType(CommConstants.FILE_TYPE_SHAREDISK);
		setSaved(true);
		setThumb(fileDTO.getThumb());
		setRemark(fileDTO.getRemark());
		setPageCount(fileDTO.getPageCount());
		checkPermission = true;
	}

	public ViewFileDTO(IConferenceDocDTO fileDTO) {
		setFileId(fileDTO.getDiskFileId());
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getType())) {
			setFromId(fileDTO.getUserId());
		} else {
			setFromId(fileDTO.getEnterpriseId());
		}
		setFolderId(fileDTO.getConferenceId());
		setName(fileDTO.getName());
		setGuid(fileDTO.getGuid());
		setSize(fileDTO.getSize());
		setPageCount(fileDTO.getPageCount());
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getType())
				|| CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())) {
			setType(fileDTO.getType());
		} else {
			setType(CommConstants.FILE_TYPE_CONFERENCE_DOC);
		}
		saved = true;
		checkPermission = false;
	}

	@Override
	public boolean isSaved() {
		if (!saved) {
			if (CommConstants.FILE_TYPE_ONLINEDISK.equals(getType())
					|| CommConstants.FILE_TYPE_SHAREDISK.equals(getType())) {
				saved = true;
			}
		}
		return saved;
	}

	@Override
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	@Override
	public long getFromId() {
		return fromId;
	}

	@Override
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	@Override
	public int getCurrentPage() {
		return currentPage;
	}

	@Override
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
