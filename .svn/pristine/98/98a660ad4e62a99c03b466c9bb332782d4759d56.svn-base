package com.qycloud.oatos.server.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.qycloud.oatos.server.dao.RoleMapper;
import com.qycloud.oatos.server.domain.entity.Role;
import com.qycloud.oatos.server.test.BaseTest;

public class RoleMapperTest extends BaseTest {

	@SpringBeanByType
	private RoleMapper roleMapper;

	@Test
	public void addRoles() {
		List<Role> roles = new ArrayList<Role>();

		for (int i = 0; i < 5; i++) {
			Role r = new Role();
			r.setRoleId(sequence.getNextId());
			r.setEnterpriseId(enterprise.getEnterpriseId());
			r.setName("Role-test-" + i);

			roles.add(r);
		}
		roleMapper.addRoles(roles);
	}
}
