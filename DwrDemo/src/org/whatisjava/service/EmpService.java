package org.whatisjava.service;

public class EmpService {

	public EmpService() {
		System.out.println("EmpService()...");
	}

	public String hello(String name) {
		System.out.println("hello");
		return "Hello, " + name;
	}
	
	public String good(String str1, String str2) {
		return str1+str2;
	}
}
