package com.qycloud.oatos.server.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.qycloud.oatos.server.dao.ShareFileMapper;
import com.qycloud.oatos.server.domain.entity.ShareFile;
import com.qycloud.oatos.server.test.BaseTest;

/**
 * ShareFileMapper test
 * 
 * @author yang
 * 
 */
public class ShareFileMapperTest extends BaseTest {

	@SpringBeanByType
	private ShareFileMapper shareFileMapper;

	@Test
	public void getFileSizeSumByEntId() {
		long s = shareFileMapper.getFileSizeSumByEntId(21);
		System.out.println(s);
	}

	@Test
	public void getFileSizeSumByFolderId() {
		long s = shareFileMapper.getFileSizeSumByFolderId(1);
		System.out.println(s);
	}

	@Test
	public void updateShareFiles() {
		List<ShareFile> files = new ArrayList<ShareFile>();
		ShareFile f = new ShareFile();
		f.setEnterpriseId(3317L);
		f.setFileId(3355L);
		f.setFolderId(3386L);
		f.setName("789_355498_537406.jpg");
		f.setSize(254);
		f.setGuid("c5ee8d36-bcaf-4997-adc4-b191e1612cb8.jpg");
		f.setVersion(999);
		f.setType("");
		files.add(f);
		
		ShareFile f1 = new ShareFile();
		f1.setEnterpriseId(3317L);
		f1.setFileId(3356L);
		f1.setFolderId(3386L);
		f1.setName("789_355499_179361.jpg");
		f1.setSize(222);
		f1.setGuid("03914b1d-d8fe-45d1-bac5-65e68a8b960b.jpg");
		f1.setVersion(999);
		f1.setType("");
		files.add(f1);
		
		shareFileMapper.updateShareFiles(files);
	}

	@Test
	public void deleteShareFiles() {
		List<ShareFile> files = new ArrayList<ShareFile>();
		ShareFile f = new ShareFile();
		f.setEnterpriseId(3317L);
		f.setFileId(3355L);
		f.setFolderId(3386L);
		f.setName("789_355498_537406.jpg");
		f.setSize(254);
		f.setGuid("c5ee8d36-bcaf-4997-adc4-b191e1612cb8.jpg");
		f.setVersion(999);
		f.setType("");
		files.add(f);
		
		ShareFile f1 = new ShareFile();
		f1.setEnterpriseId(3317L);
		f1.setFileId(3356L);
		f1.setFolderId(3386L);
		f1.setName("789_355499_179361.jpg");
		f1.setSize(222);
		f1.setGuid("03914b1d-d8fe-45d1-bac5-65e68a8b960b.jpg");
		f1.setVersion(999);
		f1.setType("");
		files.add(f1);
		
		shareFileMapper.deleteShareFiles(files);
	}
}
