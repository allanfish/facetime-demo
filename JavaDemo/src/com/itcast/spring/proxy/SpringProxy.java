package com.itcast.spring.proxy;

import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itcast.model.UserDao;
import com.itcast.model.UserDao2;

public class SpringProxy {

	private final static Logger logger = Logger.getLogger(SpringProxy.class);

	public static void main(String[] args) throws Exception {
		BeanFactory beanFactory = new BeanFactory(SpringProxy.class.getClassLoader().getResourceAsStream("beans.xml"));
		for (Entry<String, Object> entry : beanFactory.getBeans().entrySet())
			logger.info("bean name:" + entry.getKey() + ", bean class:" + entry.getValue().getClass().getSimpleName());

		UserDao userDao = (UserDao) beanFactory.getBean("userDao");
		userDao.create("yufei", 23);
		userDao.delete("yufei");

		UserDao2 userDao2 = (UserDao2) beanFactory.getBean("userDao");
		userDao2.test();
	}
}