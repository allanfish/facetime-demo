package com.conlect.oatos.dto.autobean;

import java.io.Serializable;
import java.util.Date;

public interface IShareLinkDTO extends Serializable {
	long getLinkId();

	void setLinkId(long linkId);

	String getLinkCode();

	void setLinkCode(String linkCode);

	Long getFolderId();

	void setFolderId(Long folderId);

	Long getFileId();

	void setFileId(Long fileId);

	Long getMaxDownload();

	void setMaxDownload(Long maxDownload);

	long getDownloadCount();

	void setDownloadCount(long downloadCount);

	String getPassword();

	void setPassword(String password);

	Date getExpirationTime();

	void setExpirationTime(Date expirationTime);

	long getCreateUserId();

	void setCreateUserId(long createUserId);

	String getCreateUserName();

	void setCreateUserName(String createUserName);

	Date getCreateTime();

	void setCreateTime(Date createTime);

	long getEnterpriseId();

	void setEnterpriseId(long enterpriseId);

}
