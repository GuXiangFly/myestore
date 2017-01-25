package com.guxiang.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guxiang
 * @time   2017 2017年1月17日 上午2:08:31
 *  注销功能的servlet
 */
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//注销的两种方式：
		//1.直接将session对象销毁，invalidate
		//2.将存放在session中的 loginUser移除 ，removeAttribute
		
		request.getSession().invalidate();
		
		//销毁cookie
		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		//重定向到网站首页
		response.sendRedirect("/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
