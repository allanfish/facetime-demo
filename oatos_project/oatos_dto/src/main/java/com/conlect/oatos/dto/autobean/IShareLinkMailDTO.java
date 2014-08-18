package com.conlect.oatos.dto.autobean;

import com.conlect.oatos.dto.autobean.mail.IMailDTO;

public interface IShareLinkMailDTO extends IMailDTO {
	long getLinkId();

	void setLinkId(long linkId);

	String getLinkUrl();

	void setLinkUrl(String linkUrl);

	String getEnterpriseName();

	void setEnterpriseName(String enterpriseName);

	String getLocale();

	void setLocale(String locale);

	boolean isNet();

	void setNet(boolean net);
}
