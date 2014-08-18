package com.qycloud.oatos.server.test.logic;

import org.junit.Test;

import com.qycloud.oatos.server.test.BaseTest;

public class SequenceLogicTest extends BaseTest {

	@Test
	public void getNextId() {
		long start = System.currentTimeMillis();
		long count = 10000;
		for (long i = 0; i < count; i++) {
			sequence.getNextId();
		}
		long c = System.currentTimeMillis() - start;
		System.out.println(c);
		System.out.println(1000 * ((float)count) / c);
	}
}
