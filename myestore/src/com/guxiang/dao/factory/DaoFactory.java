package com.guxiang.dao.factory;

import java.util.ResourceBundle;

public class DaoFactory {

	//˽�л�������Ĺ��췽��
	private DaoFactory(){};
	
	private static DaoFactory instance = new DaoFactory();
	
	public static DaoFactory getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
	
	public <T> T createDao(Class<T> clazz) {
		//��ȡsimplename ��Ȼ��ȥ��properties �����ļ�
		String simpleName = clazz.getSimpleName();
		//ȥ�� dao.properties
		String clazzName = ResourceBundle.getBundle("dao").getString(simpleName);
		try {
			return (T) Class.forName(clazzName).newInstance();
		} catch (Exception e) {
			return null;
		}
		
		
	}

}
