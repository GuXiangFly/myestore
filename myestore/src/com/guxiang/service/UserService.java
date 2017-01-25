package com.guxiang.service;

import com.guxiang.dao.UserDao;
import com.guxiang.dao.factory.DaoFactory;
import com.guxiang.domain.User;

/**
 * @author guxiang
 * @time   2017 2017年1月16日 下午10:26:03
 */
public class UserService {

	private UserDao  udao = DaoFactory.getInstance().createDao(UserDao.class);
	
	/**
	 * @param bean ;
	 * 向数据库中插入数据 ，如果用户名 邮箱已经存在 则提示换一个
	 */
	public void regist(User user) {
		// TODO Auto-generated method stub
		udao.insert(user);
	}

	public User login(User user) {
		//调用 dao 去查找
		
		return udao.findByUsernameAndPassword(user);
	}
	
	public User autologin(String username, String password) {
		
		return udao.autologin(username,password);
	}
	
	public void regitsUser(User user) {
		udao.save(user);
	}
	
	public void	 activeUser(String code) {
		User user= udao.findByActiveCode();
		user.setStatus(1);
		udao.update(user);
	}

}
