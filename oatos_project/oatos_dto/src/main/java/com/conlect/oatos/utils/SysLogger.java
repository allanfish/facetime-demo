package com.conlect.oatos.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SysLogger {

	public static final Logger defaultLogger = Logger.getLogger("default");
	public static final Logger osLogger = Logger.getLogger("os");
	public static final Logger serverLogger = Logger.getLogger("server");
	public static final Logger loginLogger = Logger.getLogger("login");
	public static final Logger registerLogger = Logger.getLogger("register");
	public static final Logger payLogger = Logger.getLogger("pay");

	private static Logger shareLogger;
	private static Logger adminLogger;

	static {
		PropertyConfigurator.configure(new SysLogger().getClass()
				.getClassLoader()
				.getResource("com/conlect/oatos/utils/log4j.properties"));
	}

	public static Logger getPayLogger() {
		return payLogger;
	}

	public static Logger getDefaultLogger() {
		return defaultLogger;
	}

	public static Logger getOsLogger() {
		return osLogger;
	}

	public static Logger getServerLogger() {
		return serverLogger;
	}

	public static String stackToString(Exception exp) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			exp.printStackTrace(pw);
			return sw.toString();
		} catch (Exception e2) {
			return "(bad stack2string)".concat(exp.getMessage());
		}
	}

	public static Logger getShareLogger() {
		if (shareLogger == null) {
			shareLogger = Logger.getLogger("share");
		}
		return shareLogger;
	}

	public static Logger getAdminLogger() {
		if (adminLogger == null) {
			adminLogger = Logger.getLogger("admin");
		}
		return adminLogger;
	}
}
