package com.guxiang.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guxiang.domain.User;
import com.guxiang.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		//调用业务层的业务逻辑
		UserService us = new UserService();
		
		//返回user对象好
		User loginUser = us.login(user);
		
		if (loginUser==null) {
			//用户名和密码错误
			request.setAttribute("message", "对不起，用户名和密码错误..");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			//登录成功
			request.getSession().setAttribute("loginUser", loginUser);
			
			//判断是否需要自动登录
			String autoLogin = request.getParameter("autologin");
			if ("on".equals(autoLogin)) {
				Cookie cookie = new Cookie("autologin", loginUser.getUsername()+"#guxiang#"+loginUser.getPassword());
				cookie.setMaxAge(60*60*24);
				cookie.setPath("/");      //设置有效路径
				response.addCookie(cookie);//写入浏览器
			}
			else {
				//没有勾选 清空浏览器
				Cookie cookie = new Cookie("autologin", "");
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
			//顶级域名不需要这样写
			//response.sendRedirect(request.getContextPath()+"/index.jsp");
			response.sendRedirect("/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
