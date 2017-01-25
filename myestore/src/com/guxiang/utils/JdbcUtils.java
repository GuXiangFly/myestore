package com.guxiang.utils;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author guxiang
 * @time   2017 2017��1��15�� ����1:32:16
 */
public class JdbcUtils {
	private static DataSource dataSource = new ComboPooledDataSource();
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	/**
	 * ��DBUtils��Ҫ�ֶ���������ʱ�����ø÷������һ������
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	
	}
	
}
