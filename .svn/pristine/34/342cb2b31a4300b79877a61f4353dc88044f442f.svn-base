package com.qycloud.oatos.filemanager.util;

import java.io.File;
import java.io.FilenameFilter;

import com.conlect.oatos.dto.status.CommonUtil;

public class ImageNameFilter implements FilenameFilter {
	
	@Override
	public boolean accept(File dir, String name) {
		return !name.startsWith(".") && CommonUtil.isImage(name);
	}
	
}
