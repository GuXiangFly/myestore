package com.guxiang.dao.impl;

import java.sql.SQLException;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.guxiang.dao.UserDao;
import com.guxiang.domain.User;
import com.guxiang.utils.JdbcUtils;
import com.guxiang.utils.MD5Utils;

public class UserDaoImpl implements UserDao {

	/**
	 * 插入用户，完成注册
	 * @param user 
	 */
	@Override
	public void insert(User user) {
		
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into users values(null,?,?,?,?,null,null,null,null)";
		try {
			 runner.update(sql,user.getUsername(),MD5Utils.md5(user.getPassword()),user.getEmail(),user.getNickname());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findByUsernameAndPassword(User user) {
		
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users where username=? and password=?";
		//由于是返回的一个user  所以 加 new BeanHandler<User>
		try {
			return runner.query(sql, new BeanHandler<User>(User.class),user.getUsername(),MD5Utils.md5(user.getPassword()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	
	}

	@Override
	public User autologin(String username, String password) {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users where username=? and password=?";
		//由于是返回的一个user  所以 加 new BeanHandler<User>
		try {
			return runner.query(sql, new BeanHandler<User>(User.class),username,password);
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}

	/**
	 * 邮件激活 模块（还未完成）
	 */
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 邮件激活 模块（还未完成）
	 */
	@Override
	public User findByActiveCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

}
