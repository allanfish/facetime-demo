package com.qycloud.oatos.filecache.test.logic;

import java.io.File;

public class GetLib {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File dir = new File("E:/fileconverter/lib");
//			File[] libs = dir.listFiles();
			String[] libs = dir.list();
			StringBuilder sb = new StringBuilder();
			for (String str : libs) {
				sb.append(" lib/" + str);
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}

}
