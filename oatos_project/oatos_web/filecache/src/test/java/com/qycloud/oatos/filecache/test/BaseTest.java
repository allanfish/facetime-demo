package com.qycloud.oatos.filecache.test;

import org.junit.BeforeClass;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

/**
 * base test class
 * 
 * @author yang
 * 
 */
@SpringApplicationContext({ "oatos-servlet.xml" })
public class BaseTest extends UnitilsJUnit4 {

	protected static String token = "";
	
	@BeforeClass
	public static void beforeClass() {
		token = "3317@2db2d2dd2521f3bea04272f2a40fd20fd0428d85pmbprnkksr22fgth1sinvrtz61367110596543";
	}

}
