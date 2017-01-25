package com.guxiang.dao.factory;

import java.util.ResourceBundle;

public class DaoFactory {

	//私有化工具类的构造方法
	private DaoFactory(){};
	
	private static DaoFactory instance = new DaoFactory();
	
	public static DaoFactory getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
	
	public <T> T createDao(Class<T> clazz) {
		//获取simplename ，然后去读properties 配置文件
		String simpleName = clazz.getSimpleName();
		//去读 dao.properties
		String clazzName = ResourceBundle.getBundle("dao").getString(simpleName);
		try {
			return (T) Class.forName(clazzName).newInstance();
		} catch (Exception e) {
			return null;
		}
		
		
	}

}
