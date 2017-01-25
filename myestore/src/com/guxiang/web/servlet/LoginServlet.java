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
		//��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		//����ҵ����ҵ���߼�
		UserService us = new UserService();
		
		//����user�����
		User loginUser = us.login(user);
		
		if (loginUser==null) {
			//�û������������
			request.setAttribute("message", "�Բ����û������������..");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else {
			//��¼�ɹ�
			request.getSession().setAttribute("loginUser", loginUser);
			
			//�ж��Ƿ���Ҫ�Զ���¼
			String autoLogin = request.getParameter("autologin");
			if ("on".equals(autoLogin)) {
				Cookie cookie = new Cookie("autologin", loginUser.getUsername()+"#guxiang#"+loginUser.getPassword());
				cookie.setMaxAge(60*60*24);
				cookie.setPath("/");      //������Ч·��
				response.addCookie(cookie);//д�������
			}
			else {
				//û�й�ѡ ��������
				Cookie cookie = new Cookie("autologin", "");
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
			//������������Ҫ����д
			//response.sendRedirect(request.getContextPath()+"/index.jsp");
			response.sendRedirect("/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
