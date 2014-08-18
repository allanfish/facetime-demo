package com.qycloud.oatos.filemanager.util;

import java.io.File;
import java.util.Comparator;

public class FileDESCComparator implements Comparator<File> {
	
	@Override
	public int compare(File o1, File o2) {
		if (o1.lastModified() > o2.lastModified()) {
			return -1;
		} else if (o1.lastModified() < o2.lastModified()) { return 1; }
		return 0;
	}
	
}
