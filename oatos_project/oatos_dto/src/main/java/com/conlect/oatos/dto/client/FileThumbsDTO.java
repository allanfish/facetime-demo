package com.conlect.oatos.dto.client;

import java.util.List;

import com.conlect.oatos.dto.autobean.IFileThumbDTO;
import com.conlect.oatos.dto.autobean.IFileThumbsDTO;

/**
 * 文件缩略图list
 * 
 * @author yang
 * 
 */
public class FileThumbsDTO implements IFileThumbsDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<IFileThumbDTO> thumbList;

	@Override
	public List<IFileThumbDTO> getThumbList() {
		return thumbList;
	}

	@Override
	public void setThumbList(List<IFileThumbDTO> thumbList) {
		this.thumbList = thumbList;
	}

}
