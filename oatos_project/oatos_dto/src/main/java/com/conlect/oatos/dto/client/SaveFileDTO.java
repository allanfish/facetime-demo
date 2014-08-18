package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.INetworkFileDTO;
import com.conlect.oatos.dto.autobean.ISaveFileDTO;
import com.conlect.oatos.dto.autobean.IShareFileDTO;
import com.conlect.oatos.dto.autobean.IViewFileDTO;

/**
 * 保存文件dto
 * 
 * @author yang
 * 
 */
public class SaveFileDTO extends FileDTO implements ISaveFileDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 文件所有者id，企业id或用户id 企业网盘文件为企业id；个人网盘文件为用户id
	 */
	private long fromId;
	/**
	 * 保存后文件所有者id，企业id或用户id 企业网盘文件为企业id；个人网盘文件为用户id
	 */
	private long toId;
	/**
	 * 文件内容 编辑保存时需要
	 */
	private String content;
	/**
	 * 保存后文件类型
	 */
	private String toType;
	/**
	 * 保存后文件guid
	 */
	private String newGuid;
	/**
	 * 是否是企业网盘文件
	 */
	private boolean entDiskFile = false;

	public SaveFileDTO() {
	}

	public SaveFileDTO(IShareFileDTO fileDTO) {
		setFileId(fileDTO.getFileId());
		setFolderId(fileDTO.getFolderId());
		setName(fileDTO.getName());
		setGuid(fileDTO.getGuid());
		setType(fileDTO.getType());
		setSize(fileDTO.getSize());
		setCreateTime(fileDTO.getCreateTime());
		setUpdateTime(fileDTO.getUpdateTime());
		setCloudDiskIp(fileDTO.getCloudDiskIp());
		setDeleted(fileDTO.getDeleted());
		setThumb(fileDTO.getThumb());
		setRemark(fileDTO.getRemark());
		setVersion(fileDTO.getVersion());
		setEntDiskFile(true);
	}

	public SaveFileDTO(INetworkFileDTO fileDTO) {
		setFileId(fileDTO.getFileId());
		setUserId(fileDTO.getUserId());
		setFolderId(fileDTO.getFolderId());
		setName(fileDTO.getName());
		setGuid(fileDTO.getGuid());
		setType(fileDTO.getType());
		setSize(fileDTO.getSize());
		setCreateTime(fileDTO.getCreateTime());
		setUpdateTime(fileDTO.getUpdateTime());
		setCloudDiskIp(fileDTO.getCloudDiskIp());
		setDeleted(fileDTO.getDeleted());
		setThumb(fileDTO.getThumb());
		setRemark(fileDTO.getRemark());
		setVersion(fileDTO.getVersion());
		setEntDiskFile(false);
	}

	public SaveFileDTO(IViewFileDTO fileDTO) {
		setFileId(fileDTO.getFileId());
		setUserId(fileDTO.getUserId());
		setEnterpriseId(fileDTO.getEnterpriseId());
		setFolderId(fileDTO.getFolderId());
		setName(fileDTO.getName());
		setGuid(fileDTO.getGuid());
		setType(fileDTO.getType());
		setSize(fileDTO.getSize());
		setCreateTime(fileDTO.getCreateTime());
		setUpdateTime(fileDTO.getUpdateTime());
		setCloudDiskIp(fileDTO.getCloudDiskIp());
		setDeleted(fileDTO.getDeleted());
		setThumb(fileDTO.getThumb());
		setRemark(fileDTO.getRemark());
		setVersion(fileDTO.getVersion());
		fromId = fileDTO.getFromId();
		userName = fileDTO.getUserName();
	}

	@Override
	public boolean isEntDiskFile() {
		return entDiskFile;
	}

	@Override
	public void setEntDiskFile(boolean entDiskFile) {
		this.entDiskFile = entDiskFile;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
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
	public long getToId() {
		return toId;
	}

	@Override
	public void setToId(long toId) {
		this.toId = toId;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getToType() {
		return toType;
	}

	@Override
	public void setToType(String toType) {
		this.toType = toType;
	}

	@Override
	public String getNewGuid() {
		return newGuid;
	}

	@Override
	public void setNewGuid(String newGuid) {
		this.newGuid = newGuid;
	}

}
