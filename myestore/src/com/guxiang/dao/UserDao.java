package com.guxiang.dao;

import com.guxiang.domain.User;

public interface UserDao {

	void insert(User user);

	User findByUsernameAndPassword(User user);

	User autologin(String username, String password);

	void save(User user);

	User findByActiveCode();

	void update(User user);

}
