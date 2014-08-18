package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频会议文档list
 * 
 * @author yang
 * 
 */
public class ConferenceDocsDTO implements BaseDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * 会议文档dto列表
	 */
	private List<ConferenceDocDTO> docList = new ArrayList<ConferenceDocDTO>();
	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	public ConferenceDocsDTO() {

	}

	public List<ConferenceDocDTO> getDocList() {
		return docList;
	}

	public void setDocList(List<ConferenceDocDTO> docList) {
		this.docList = docList;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}

}
