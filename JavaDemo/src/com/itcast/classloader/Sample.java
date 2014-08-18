package com.itcast.classloader;

import java.util.Date;

public class Sample extends Date {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "hello, class loader";
	}

}
