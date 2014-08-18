package com.qycloud.oatos.server.test.logic;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.conlect.oatos.dto.client.DepartmentAndUserDTO;
import com.conlect.oatos.http.PojoMapper;
import com.qycloud.oatos.server.domain.logic.UserLogic;
import com.qycloud.oatos.server.test.BaseTest;

public class UserLogicTest extends BaseTest {

	@SpringBeanByType
	private UserLogic userLogic;
	
	@Test
	public void getDepartmentAndUserWithStatusByUserId() {
		DepartmentAndUserDTO dto = userLogic.getDepartmentAndUserWithStatusByUserId(user.getUserId());
		System.out.println(PojoMapper.toJson(dto));
	}
}
