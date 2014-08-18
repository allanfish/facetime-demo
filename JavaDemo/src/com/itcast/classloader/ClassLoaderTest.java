package com.itcast.classloader;

import java.util.Date;

public class ClassLoaderTest {

	public static void main(String[] args) throws Exception {
		// ClassLoaderTest.class.getClassLoader().loadClass("com.itcast.classloader.Sample");
		Class<?> clazz = new MyClassLoader().loadClass("com.itcast.classloader.Sample");
		Date date = (Date) clazz.newInstance();
		System.out.println(date.toString());
	}
}
