package com.qycloud.oatos.server.test.dao;

import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.User;
import com.qycloud.oatos.server.test.BaseTest;

public class UserMapperTest extends BaseTest {

	@SpringBeanByType
	private UserMapper userMapper;
	

	
	@Test
	public void getUser() {
		User u = userMapper.getUser(23);
		System.out.println(u);
	}
	
	@Test
	public void test() {
		Enterprise ent = enterpriseMapper.getEnterpriseById(enterprise.getEnterpriseId());
		User u = userMapper.getUser(user.getUserId());
		System.out.println(PojoMapper.toJson(ent));
		System.out.println(PojoMapper.toJson(u));
	}
}
