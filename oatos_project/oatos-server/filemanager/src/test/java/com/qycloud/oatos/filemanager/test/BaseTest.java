package com.qycloud.oatos.filemanager.test;

import org.junit.After;
import org.junit.Before;
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

	@Before
	public void before() {

	}

	@After
	public void after() {

	}

}
