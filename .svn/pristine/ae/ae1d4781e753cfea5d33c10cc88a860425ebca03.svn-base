package com.conlect.oatos.test;

import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

import com.conlect.oatos.mail.MailConfigure;

public class MailConfigureTest {

	@Test
	public void test() {
		for (Entry<String, Properties> entry : MailConfigure.defaultConfigs
				.entrySet()) {
			System.out.println();
			System.out.println("mail config key: " + entry.getKey());
			for (Object config : entry.getValue().keySet()) {
				System.out.println(config.toString() + "="
						+ entry.getValue().get(config));
			}
		}
	}
}
