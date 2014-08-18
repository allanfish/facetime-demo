package com.itcast.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.log4j.Logger;

public class CollectionProxy {

	private static final Logger logger = Logger.getLogger(CollectionProxy.class);

	public static void main(String[] args) throws Exception {
		Class<?> proxyClass = Proxy.getProxyClass(CollectionProxy.class.getClassLoader(),
				new Class[] { Collection.class });

		printSuperInterfaceInfo(proxyClass);
		printConstructorInfo(proxyClass);
		printMethodInfo(proxyClass);

	}

	public static void printConstructorInfo(Class<?> proxyClass) {
		Constructor<?>[] constructors = proxyClass.getConstructors();
		for (int i = 0; i < constructors.length; i++) {
			Constructor<?> constructor = constructors[i];
			String constructorName = constructor.getName();
			Class<?>[] paramTypes = constructor.getParameterTypes();
			StringBuilder constructorStructure = new StringBuilder();
			constructorStructure.append("constructor structure:" + constructorName).append("(");
			if (paramTypes != null && paramTypes.length > 0) {
				for (Class<?> paramType : paramTypes)
					constructorStructure.append(paramType.getSimpleName()).append(",");
				constructorStructure.replace(constructorStructure.lastIndexOf(","), constructorStructure.length(), "");
			}
			constructorStructure.append(")");
			logger.info(constructorStructure);
		}
	}

	public static void printMethodInfo(Class<?> proxyClass) {
		Method[] methods = proxyClass.getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			Class<?>[] paramTypes = method.getParameterTypes();
			StringBuilder methodSigns = new StringBuilder();
			methodSigns.append("method signurature: " + methodName + "(");
			if (paramTypes != null && paramTypes.length > 0) {
				for (Class<?> type : paramTypes)
					methodSigns.append(type.getSimpleName() + ",");
				methodSigns.replace(methodSigns.length() - 1, methodSigns.length(), "");
			}
			methodSigns.append(")");
			logger.info(methodSigns);
		}
	}

	public static void printSuperInterfaceInfo(Class<?> proxyClass) {
		Type[] superInterfaces = proxyClass.getInterfaces();
		StringBuilder superInterfaceInfo = new StringBuilder();
		superInterfaceInfo.append("this proxy implement ");
		for (Type type : superInterfaces)
			superInterfaceInfo.append(type.toString() + ",");
		superInterfaceInfo.replace(superInterfaceInfo.length() - 1, superInterfaceInfo.length(), "");
		logger.info(superInterfaceInfo);
	}

	static class MyHandler implements InvocationHandler {

		private Object target;

		public MyHandler() {

		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return method.invoke(target, args);
		}

		public void setTarget(Object target) {
			this.target = target;
		}

	}
}
