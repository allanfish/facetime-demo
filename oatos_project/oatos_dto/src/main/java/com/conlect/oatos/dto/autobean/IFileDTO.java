package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.Date;

public interface IFileDTO extends Serializable {

	Long getFileId();

	void setFileId(Long fileId);

	long getEnterpriseId();

	void setEnterpriseId(long enterpriseId);

	long getUserId();

	void setUserId(long userId);

	Long getFolderId();

	void setFolderId(Long folderId);

	String getName();

	void setName(String name);

	String getType();

	void setType(String type);

	String getGuid();

	void setGuid(String guid);

	long getSize();

	void setSize(long size);

	Date getCreateTime();

	void setCreateTime(Date createTime);

	Date getUpdateTime();

	void setUpdateTime(Date updateTime);

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);

	int getDeleted();

	void setDeleted(int deleted);

	String getThumb();

	void setThumb(String thumb);

	String getRemark();

	void setRemark(String remark);

	long getVersion();

	void setVersion(long version);

	Integer getPageCount();

	void setPageCount(Integer pageCount);

}
