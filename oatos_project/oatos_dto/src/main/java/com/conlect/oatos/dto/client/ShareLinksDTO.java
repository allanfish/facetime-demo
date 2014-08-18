package com.conlect.oatos.dto.client;

import java.util.ArrayList;
import java.util.List;

/**
 * 外链list
 * 
 * @author yang
 * 
 */
public class ShareLinksDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ShareLinkDTO> linkDTOs = new ArrayList<ShareLinkDTO>();

	public List<ShareLinkDTO> getLinkDTOs() {
		return linkDTOs;
	}

	public void setLinkDTOs(List<ShareLinkDTO> linkDTOs) {
		this.linkDTOs = linkDTOs;
	}

}
