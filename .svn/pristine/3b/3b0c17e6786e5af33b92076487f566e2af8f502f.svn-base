package com.demo.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.dubbo.provider.DemoService;

public class Consumer {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService");
		demoService.sayHello();
	}
}
