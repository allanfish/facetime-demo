package com.qycloud.oatos.server.test;

import java.text.DecimalFormat;

import org.junit.Test;

import com.conlect.oatos.utils.StopWatch;
import com.qycloud.oatos.server.security.Security;

public class SecurityTest {

	@Test
	public void test() {

		System.out.println(Security.randomCharString(32));

		String tokenString = Security.CreateToken("1108");
		System.out.println(tokenString);

		StopWatch watch = new StopWatch();

		watch.start("security");

		long counter = 10000000;

		for (int i = 0; i < counter; i++) {
			Security.CheckToken(tokenString);
		}

		watch.stop();

		long time = watch.getLastTaskTimeMillis();

		System.out.println("次数：" + 1000000.0 / (time / 1000.0));
	}

	@Test
	public void createPassword() {
		String username = "root";// jdbc, jdbc-58, private-cloud
		//		String username = "oatosuser"; // jdbc-gz, jdbc-net
		System.out.println("security username: " + Security.byteStringToHexString(username));
		System.out
				.println("real username: " + Security.hexStringToByteString(Security.byteStringToHexString(username)));

		//		String realpwd = "root"; // jdbc-gz, jdbc-net
		// String realpwd = "guoguo"; // jdbc, jdbc-58
		String realpwd = "oatos@mysql"; // private-cloud

		System.out.println("security pwd: " + Security.byteStringToHexString(realpwd));
		System.out.println("real pwd: " + Security.hexStringToByteString(Security.byteStringToHexString(realpwd)));

	}
	
	@Test
	public void createDBPassword() {
		String username = "oatos_dbuser";
		String realpwd = "cqkd965bgfdf2";
		System.out.println("real username: " + username);
		System.out.println("real pwd: " + realpwd);
		
		System.out.println("security username: " + Security.byteStringToHexString(username));

		System.out.println("security pwd: " + Security.byteStringToHexString(realpwd));
	}

	@Test
	public void testNumber() {
		int num = 100;
		DecimalFormat format = new DecimalFormat("#.##");
		System.out.println(format.format(num / 365.00));
	}
	
	@Test
	public void createToken() {
		System.out.println(Security.CreateToken("1"));
	}
}
