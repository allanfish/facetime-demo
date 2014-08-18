package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.Date;

public interface IConferenceDocDTO extends Serializable {

	long getFileId();

	void setFileId(long fileId);

	long getConferenceId();

	void setConferenceId(long conferenceId);

	String getName();

	void setName(String name);

	String getGuid();

	void setGuid(String guid);

	long getSize();

	void setSize(long size);

	String getType();

	void setType(String type);

	Date getCreateTime();

	void setCreateTime(Date createTime);

	Long getDiskFileId();

	void setDiskFileId(Long diskFileId);

	Long getUserId();

	void setUserId(Long userId);

	Long getEnterpriseId();

	void setEnterpriseId(Long enterpriseId);

	String getCloudDiskIp();

	void setCloudDiskIp(String cloudDiskIp);

	Integer getPageCount();

	void setPageCount(Integer pageCount);
}
