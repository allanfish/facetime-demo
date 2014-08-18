package com.itcast.model;

import org.apache.log4j.Logger;

public class UserDaoImpl implements UserDao, UserDao2 {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public String create(String name, int age) {
		logger.debug("invoke UserDao create(), named=" + name + ", age=" + age);
		return name;
	}

	@Override
	public void delete(String name) {
		logger.debug("invoke UserDao delete() , name=" + name);
	}

	@Override
	public void test() {
		logger.debug(" invoke UserDao2 test().");
	}

}