package com.qycloud.oatos.server.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.conlect.oatos.dto.client.ShareFileDTO;
import com.conlect.oatos.dto.client.ShareFolderAndFileDTO;
import com.conlect.oatos.dto.client.ShareFolderDTO;
import com.qycloud.oatos.server.domain.entity.ShareFile;
import com.qycloud.oatos.server.domain.entity.ShareFolder;

public class ShareFolderAndFileModel {

	private List<ShareFolder> folderList = new ArrayList<ShareFolder>();
	private List<ShareFile> fileList = new ArrayList<ShareFile>();

	private Set<Long> folderIds = new HashSet<Long>();

	private Set<Long> parentFolderIds = new HashSet<Long>();

	public ShareFolderAndFileModel(ShareFolderAndFileDTO folderAndFileDTO) {
		if (folderAndFileDTO.getForderList() != null) {
	        for (ShareFolderDTO f : folderAndFileDTO.getForderList()) {
	            folderList.add(new ShareFolder(f));
	            folderIds.add(f.getFolderId());
	            if (f.getParentId() != null && !folderIds.contains(f.getParentId())) {
	            	parentFolderIds.add(f.getParentId());
                }
            }
		}

		if (folderAndFileDTO.getFileList() != null) {
			for (ShareFileDTO f : folderAndFileDTO.getFileList()) {
				fileList.add(new ShareFile(f));
	            if (!folderIds.contains(f.getFolderId())) {
	            	parentFolderIds.add(f.getFolderId());
                }
			}
        }
	}

	public List<ShareFolder> getFolderList() {
		return folderList;
	}

	public List<ShareFile> getFileList() {
		return fileList;
	}

	public Set<Long> getParentFolderIds() {
		return parentFolderIds;
	}

}
