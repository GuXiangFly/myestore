package com.guxiang.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guxiang
 * @time   2017 2017��1��17�� ����2:08:31
 *  ע�����ܵ�servlet
 */
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ע�������ַ�ʽ��
		//1.ֱ�ӽ�session�������٣�invalidate
		//2.�������session�е� loginUser�Ƴ� ��removeAttribute
		
		request.getSession().invalidate();
		
		//����cookie
		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		//�ض�����վ��ҳ
		response.sendRedirect("/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
