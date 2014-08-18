package com.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.dao.UserDao;
import com.jdbc.domain.User;
import com.jdbc.utils.DatabaseUtil;
import com.jdbc.utils.DatabaseUtil.DatabaseType;

public class OracleUserDaoImpl implements UserDao {

	private final DatabaseType dbType;

	public OracleUserDaoImpl(DatabaseType dbType) {
		this.dbType = dbType;
	}

	@Override
	public void createTable() {
		Connection con = DatabaseUtil.getConnection(dbType);
		PreparedStatement ps = null;
		try {
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			con.setAutoCommit(false);
			ps = con.prepareStatement("create table TB_USER (id number(4) primary key , name varchar2(40))");
			ps.execute(); // this api used for DDL statement
			con.commit();

			DatabaseMetaData dmd = con.getMetaData();
			System.out.println("database name:" + dmd.getDatabaseProductName());
			System.out.println("database version:" + dmd.getDatabaseProductVersion());
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DatabaseUtil.closeConn(con, ps, null);
		}
	}

	@Override
	public void deleteUser(User user) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseUtil.getConnection(dbType);
			con.setAutoCommit(false);
			ps = con.prepareStatement("delete from TB_USER where TB_USER.id = ? and TB_USER.name = ?");
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.executeUpdate();
			System.out.println("delete TB_USER by id:" + user.getId());
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DatabaseUtil.closeConn(con, ps, null);
		}
	}

	@Override
	public void dropUser() {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DatabaseUtil.getConnection(dbType);
			con.setAutoCommit(false);
			ps = con.prepareStatement("drop table TB_USER ");
			ps.execute();
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DatabaseUtil.closeConn(con, ps, null);
		}
	}

	@Override
	public User getUserById(Integer id) {

		return null;
	}

	@Override
	public List<User> getUserByName(String name) {
		Connection con = DatabaseUtil.getConnection(dbType);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			ps = con.prepareStatement("select id,name from TB_USER  where TB_USER.name = ?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while (rs.next()) {
				int id = rs.getInt(1);
				String foundName = rs.getString("name");
				userList.add(new User(id, foundName));

				for (int i = 1; i <= cols; i++) {
					System.out.println("column name:" + rsmd.getColumnName(i));
					System.out.println("column type:" + rsmd.getColumnClassName(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeConn(con, ps, rs);
		}
		return userList;
	}

	@Override
	public void save(User user) {
		Connection con = DatabaseUtil.getConnection(dbType);
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement("insert into TB_USER(id,name) values(?,?)");
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.executeUpdate(); // DML statement
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DatabaseUtil.closeConn(con, ps, null);
		}
	}
}
