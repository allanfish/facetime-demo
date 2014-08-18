package com.jdbc.dao;

import java.util.List;

import com.jdbc.domain.User;

public interface UserDao {

	public void createTable();

	public void deleteUser(User user);

	public void dropUser();

	public User getUserById(Integer id);

	public List<User> getUserByName(String name);

	public void save(User user);
}
