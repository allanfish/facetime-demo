package com.itcast.spring.proxy;

import org.apache.log4j.Logger;

class LogInterceptor implements Interceptor {

	private final Logger logger = Logger.getLogger(getClass());

	@Override
	public void after() {
		logger.info("after method invoke ...");
	}

	@Override
	public void before() {
		logger.info("before method invoke ...");
	}
}