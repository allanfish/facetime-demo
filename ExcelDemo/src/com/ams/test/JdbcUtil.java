package com.ams.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcUtil {

	private static final String JDBC_PROPERTIES = "D:\\workspace\\projects\\excel_demo\\src\\jdbc.properties";
	private static String DRIVER_CLASS;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;

	private static ThreadLocal<Connection> connLocal = new ThreadLocal<Connection>();
	static {
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(JDBC_PROPERTIES);
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		DRIVER_CLASS = properties.getProperty("jdbc.driverClassName");
		URL = properties.getProperty("jdbc.url");
		USERNAME = properties.getProperty("jdbc.username");
		PASSWORD = properties.getProperty("jdbc.password");
	}

	public static Connection getConnection() {
		Connection conn = connLocal.get();
		if (conn == null) {
			try {
				Class.forName(DRIVER_CLASS);
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static void commit(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Object[]> execute(Connection conn, String sql,
			Object[] params) {
		List<Object[]> result = new ArrayList<Object[]>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				Object obj = params[i];
				ps.setObject(i + 1, obj);
			}
			rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				int columnNum = metaData.getColumnCount();
				Object[] values = new Object[columnNum];
				for (int i = 0; i < values.length; i++) {
					values[i] = rs.getObject(i + 1);
				}
				result.add(values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
