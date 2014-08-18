package com.qycloud.oatos.server.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.qycloud.oatos.server.dao.RolePermissionMapper;
import com.qycloud.oatos.server.domain.entity.RolePermission;
import com.qycloud.oatos.server.test.BaseTest;

public class RolePermissionMapperTest extends BaseTest {

	@SpringBeanByType
	private RolePermissionMapper rolePermissionMapper;

	@Test
	public void updateRolePermisssions() {
		List<RolePermission> rps = new ArrayList<RolePermission>();
		RolePermission rp = new RolePermission();
		rp.setRoleId(19);
		rp.setFolderId(18);
		rp.setList(true);
		rp.setRead(true);
		rp.setWrite(true);
		rp.setDownload(true);
		rp.setShare(true);
		rp.setLocal(true);
		rps.add(rp);

		RolePermission rp1 = new RolePermission();
		rp1.setRoleId(19);
		rp1.setFolderId(1678);
		rp1.setList(true);
		rp1.setRead(true);
		rp1.setWrite(true);
		rp1.setDownload(true);
		rp1.setShare(true);
		rps.add(rp1);

		RolePermission rp2 = new RolePermission();
		rp2.setRoleId(19);
		rp2.setFolderId(1679);
		rp2.setList(true);
		rp2.setRead(true);
		rp2.setWrite(true);
		rp2.setDownload(true);
		rp2.setShare(true);
		rps.add(rp2);

		rolePermissionMapper.updateRolePermisssions(rps);
	}
}
