/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qycloud.oatos.convert.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.conlect.oatos.dto.status.MyConst;

/**
 * 读取 Property 配置的工具类
 * 
 * @author Peter Guo
 */
public class ConfigUtil {

	public static final String MAX_OFFICE_TASK = "MaxOfficeTask";

	public static final String MAX_SWF_TASK = "MaxSwfTask";

	// 日志
	private final static Logger logger = Logs.getLogger();

	// 属性
	private final static Properties props = new Properties();

	static {
		InputStream inputStream = null;

		try {
			inputStream = ConfigUtil.class.getClassLoader()
					.getResourceAsStream("property/config.properties");
			props.load(inputStream);
			inputStream.close();
		} catch (IOException ex) {
			logger.error("", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 获取配置项的值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return getValue(key, "");
	}

	public static String getValue(String key, String defaultVal) {
		String value = props.getProperty(key);
		if (value == null)
			return defaultVal;
		return value;
	}

	public static String getServiceUrl(String uri) {
		return getValue(MyConst.AppService) + uri;
	}

	public static int getIntValue(String key) {
		String value = getValue(key);
		return Integer.parseInt(value);
	}
}
