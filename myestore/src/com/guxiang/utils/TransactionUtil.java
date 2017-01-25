package com.guxiang.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

//把得到连接及事务有关的方法写到此类中
public class TransactionUtil {
	
	// 内部是维护了 一个 map , 这个map 的key 始终 都是 当前 的线程 
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	private static DataSource ds = JdbcUtils.getDataSource();
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	//  这里, 获得一个 connection  对象 
	public static Connection getConnection(){
		try {
			Connection conn = tl.get();
			if(conn==null){
				//从数据连接池 中 取 一个连接 出来 
				conn = ds.getConnection();
				
				//将 取出来  connection 放到 tl中去
				tl.set(conn);
			}
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// 开启 事务  
	// 结果 就是 返回了 一个 connection对象, 并且 将 返回的 connection放到了 threadlocal中 , 
	public static void startTransaction(){
		try {
			Connection conn = tl.get();
			if(conn==null){
				conn = getConnection();
//				tl.set(conn);
			}
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void rollback(){
		try {
			Connection conn = tl.get();
			if(conn==null){
				conn = getConnection();
//				tl.set(conn);
			}
			conn.rollback();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void commit(){
		try {
			Connection conn = tl.get();
			if(conn==null){
				conn = getConnection();
//				tl.set(conn);
			}
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public static void relase(){
		try {
			Connection conn = tl.get();
			if(conn!=null){
				conn.close();
				tl.remove();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
