package com.jdbc.testcase;

import java.util.List;

import com.jdbc.dao.UserDao;
import com.jdbc.dao.impl.MySQLUserDaoImpl;
import com.jdbc.dao.impl.OracleUserDaoImpl;
import com.jdbc.domain.User;
import com.jdbc.utils.DatabaseUtil.DatabaseType;

public class UserDaoTest {

	public static void main(String[] args) {
		testMySQL();
		testOracle();
	}

	public static void testMySQL() {
		UserDao userDao = new MySQLUserDaoImpl(DatabaseType.MYSQL);
		userDao.dropUser();
		userDao.createTable();

		userDao.save(new User("yufei"));
		List<User> userList = userDao.getUserByName("yufei");
		for (User user : userList)
			System.out.println(user);
	}

	/**
	 * 1. the 'user' is keyword in Oracle. <br>
	 * 2. number type is defined as 'int' in mysql. <br>
	 * 3. oracle is case insensitive, All tables in Oracle is upper case. <br>
	 * 4. no auto_increment in oracle. you need triggler <br>
	 */
	public static void testOracle() {
		UserDao userDao = new OracleUserDaoImpl(DatabaseType.ORACLE);
		userDao.dropUser();
		userDao.createTable();

		userDao.save(new User(1, "yufei"));
		List<User> userList = userDao.getUserByName("yufei");
		for (User user : userList)
			System.out.println(user);
		userDao.deleteUser(new User(1, "yufei"));
	}
}
