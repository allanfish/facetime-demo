package com.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtil {

	private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/test";
	private static final String MYSQL_USER = "root";
	private static final String MYSQL_PWD = "root";

	private static final String ORA_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String ORA_URL = "jdbc:oracle:thin:@localhost:1521/xe";
	private static final String ORA_USER = "oracle";
	private static final String ORA_PWD = "oracle";

	public static void closeConn(Connection con, Statement sttm, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (sttm != null)
				sttm.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(DatabaseType dbType) {
		if (dbType == null)
			throw new IllegalArgumentException("dbType is null");
		switch (dbType) {
		case MYSQL:
			return getMySQLConnection();
		case ORACLE:
			return getOracleConnection();
		}
		return getMySQLConnection();
	}

	private static Connection getMySQLConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	private static Connection getOracleConnection() {
		Connection con = null;
		try {
			Class.forName(ORA_DRIVER);
			con = DriverManager.getConnection(ORA_URL, ORA_USER, ORA_PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static enum DatabaseType {
		ORACLE, MYSQL
	}
}
