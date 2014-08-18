package com.conlect.oatos.dto.autobean;

/**
 * share folder dto
 * 
 * @author yang
 * 
 */
public interface IShareFolderDTO extends IFolderDTO {

	long getEnterpriseId();

	void setEnterpriseId(long enterpriseId);

	long getUserId();

	void setUserId(long userId);

	Long getMaxSize();

	void setMaxSize(Long maxSize);

	Long getShareLinkId();

	void setShareLinkId(Long shareLinkId);
}
