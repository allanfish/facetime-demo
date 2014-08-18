package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.client.mail.MailDTO;

/**
 * 外链邮件dto
 * 
 * @author yang
 * 
 */
public class ShareLinkMailDTO extends MailDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 外链id
	 */
	private long linkId;

	/**
	 * 外链地址
	 */
	private String linkUrl;

	/**
	 * 企业名
	 */
	private String enterpriseName;

	/**
	 * 语言
	 */
	private String locale;
	/**
	 * @deprecated
	 */
	@Deprecated
	private boolean net;

	public ShareLinkMailDTO() {
	}

	public ShareLinkMailDTO(long linkId, String linkUrl) {
		this.linkId = linkId;
		this.linkUrl = linkUrl;
	}

	public long getLinkId() {
		return linkId;
	}

	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean isNet() {
		return net;
	}

	public void setNet(boolean net) {
		this.net = net;
	}
}
