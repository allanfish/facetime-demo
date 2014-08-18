package com.demo.dubbo.provider;

public class DemoServiceImpl implements DemoService {

	@Override
	public void sayHello() {
		System.out.println("hello dubbo!");
	}

}
