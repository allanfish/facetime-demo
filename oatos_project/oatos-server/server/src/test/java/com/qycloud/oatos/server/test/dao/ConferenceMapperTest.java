package com.qycloud.oatos.server.test.dao;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.qycloud.oatos.server.dao.ConferenceMapper;
import com.qycloud.oatos.server.test.BaseTest;

public class ConferenceMapperTest extends BaseTest {

	@SpringBeanByType
	private ConferenceMapper conferenceMapper;

	@Test
	public void getConferenceMemberCount() {
		long c = conferenceMapper.getConferenceMemberCount(1);
		System.out.println(c);
	}
}
