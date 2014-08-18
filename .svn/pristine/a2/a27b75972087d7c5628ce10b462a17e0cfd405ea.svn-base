package com.qycloud.oatos.server.test.dao;

import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.AdminMapper;
import com.qycloud.oatos.server.domain.entity.Admin;
import com.qycloud.oatos.server.test.BaseTest;

/**
 * AdminMapper test
 * @author yang
 *
 */
public class AdminMapperTest extends BaseTest {

	@SpringBeanByType
	private AdminMapper adminMapper;
	
	@Test
	public void getAdminsByEntId() {
		List<Admin> admins = adminMapper.getAdminsByEntId(21);
		System.out.println(PojoMapper.toJson(admins));
	}
}
