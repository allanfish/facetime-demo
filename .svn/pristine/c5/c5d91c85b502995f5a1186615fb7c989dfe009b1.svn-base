package com.itcast.spring.proxy;

import org.apache.log4j.Logger;


public class TransactionInterceptor implements Interceptor {

	private final Logger logger = Logger.getLogger(getClass());

	@Override
	public void after() {
		logger.debug("commit transaction ...");
	}

	@Override
	public void before() {
		logger.debug("begin transaction...");
	}
}
