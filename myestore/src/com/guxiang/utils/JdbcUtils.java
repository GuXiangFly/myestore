package com.guxiang.utils;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author guxiang
 * @time   2017 2017年1月15日 下午1:32:16
 */
public class JdbcUtils {
	private static DataSource dataSource = new ComboPooledDataSource();
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	/**
	 * 当DBUtils需要手动控制事务时，调用该方法获得一个连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	
	}
	
}
