package com.qycloud.test;

import java.util.ArrayList;
import java.util.List;

public class TestList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> strs = null;
		System.out.println(!"".equals(strs));
		strs = new ArrayList<String>();
		System.out.println(!"".equals(strs));
		strs.add("a");
		System.out.println(!"".equals(strs));
	}

}
