package com.itcast.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;

import com.itcast.model.UserDao;
import com.itcast.model.UserDao2;
import com.itcast.model.UserDaoImpl;

public class DynamicProxy {

	private static Logger logger = Logger.getLogger(DynamicProxy.class.getClass());

	public static void main(String[] args) throws Exception {
		// 2. another way to obtain proxy object
		Class<?> proxyClass = Proxy.getProxyClass(DynamicProxy.class.getClassLoader(), new Class<?>[] { UserDao.class,
				UserDao2.class });
		Object proxy = proxyClass.getConstructor(InvocationHandler.class).newInstance(
				new DaoInvocationHandler(new UserDaoImpl()));
		CollectionProxy.printSuperInterfaceInfo(proxyClass);
		CollectionProxy.printConstructorInfo(proxyClass);
		CollectionProxy.printMethodInfo(proxyClass);

		// 1. a way to obtain proxy object
		// Object proxy = Proxy.newProxyInstance(
		// DynamicProxy.class.getClassLoader(), new Class[] {
		// UserDao.class, UserDao2.class },
		// new DaoInvocationHandler(new UserDaoImpl()));

		UserDao userDao = (UserDao) proxy;
		String name = userDao.create("yufei", 23);
		logger.debug("return value of create() :" + name);
		userDao.delete("yufei");

		UserDao2 userDao2 = (UserDao2) proxy;
		userDao2.test();
	}

	static class DaoInvocationHandler implements InvocationHandler {

		private final Object target;

		public DaoInvocationHandler(Object target) {
			super();
			this.target = target;
		}

		public void after() {
			logger.info("after invoke business method:");
		}

		public void before(Object proxy, Method method, Object[] args) {

			logger.info("before invoke business method:");
			logger.info(proxy.getClass().getName() + ", method:" + method.getName() + ", args count:"
					+ (args != null ? args.length : 0));
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			before(proxy, method, args);
			Object retVal = method.invoke(target, args);
			after();
			return retVal;
		}
	}
}
