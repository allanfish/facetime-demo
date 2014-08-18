package com.itcast.spring.proxy;

public interface Interceptor {
	public void after();

	public void before();
}