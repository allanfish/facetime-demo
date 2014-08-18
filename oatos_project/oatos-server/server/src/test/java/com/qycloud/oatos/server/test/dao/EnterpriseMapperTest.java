package com.qycloud.oatos.server.test.dao;

import org.junit.Test;

import com.qycloud.oatos.server.test.BaseTest;

public class EnterpriseMapperTest extends BaseTest {

	@Test
	public void updateShareLinkDownCount() {
		enterpriseMapper.updateShareLinkDownCount(21, 1000);
	}
}
