package com.qycloud.oatos.server.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.qycloud.oatos.server.dao.ShareFolderMapper;
import com.qycloud.oatos.server.domain.entity.ShareFolder;
import com.qycloud.oatos.server.test.BaseTest;

public class ShareFolderMapperTest extends BaseTest {

	@SpringBeanByType
	private ShareFolderMapper shareFolderMapper;

	@Test
	public void updateShareFolders() {
		List<ShareFolder> folders = new ArrayList<ShareFolder>();

		ShareFolder f = new ShareFolder();
		f.setEnterpriseId(3317L);
		f.setFolderId(3386);
		f.setParentId(3324L);
		f.setDeleted(0);
		f.setName("New folder");
		f.setVersion(32);
		f.setMaxSize(1024L);
		folders.add(f);

		ShareFolder f1 = new ShareFolder();
		f1.setEnterpriseId(3317L);
		f1.setFolderId(19039);
		f1.setDeleted(0);
		f1.setName("New folder (1)");
		f1.setVersion(32);
		folders.add(f1);

		shareFolderMapper.updateShareFolders(folders);
	}

	@Test
	public void deleteShareFolders() {
		List<ShareFolder> folders = new ArrayList<ShareFolder>();

		ShareFolder f = new ShareFolder();
		f.setEnterpriseId(3317L);
		f.setFolderId(3386);
		f.setParentId(3324L);
		f.setDeleted(0);
		f.setName("New folder");
		f.setVersion(32);
		f.setMaxSize(1024L);
		folders.add(f);

		ShareFolder f1 = new ShareFolder();
		f1.setEnterpriseId(3317L);
		f1.setFolderId(19039);
		f1.setDeleted(0);
		f1.setName("New folder (1)");
		f1.setVersion(32);
		folders.add(f1);

		shareFolderMapper.deleteShareFolders(folders);
	}
}
