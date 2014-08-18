package com.qycloud.oatos.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 对象名称：取配置文件内容
 * 
 * 作者： 秦利军
 * 
 * 完成日期：
 * 
 * 对象内容：根据节点取配置文件内容
 */
public class ConfigUtil {

	// 日志
	public final static Logger logger = Logger.getLogger(ConfigUtil.class);
	private final static Properties props = new Properties();

	static {

		try {
			InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(
			        "property/config.properties");
			props.load(inputStream);
			inputStream.close();
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());

			logger.error(ex);
		}
		catch (Exception ex) {

			System.out.println(ex.getMessage());
			logger.error(ex);
		}
	}

	public static String getValue(String key) {
		String value = props.getProperty(key);
		return value;
	}
}
