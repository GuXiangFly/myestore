package com.guxiang.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guxiang.domain.User;
import com.guxiang.service.UserService;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// ���Ҫ���ʵ���Դ��·��
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// �����Ҫ���ʵ���Դ·��
		// ��������� /myestore/servlet1
		String requestURI = request.getRequestURI();

		// web���̵Ķ������·��
		// /myestore
		String contextPath = request.getContextPath();

		// �����Դ����·��
		// servlet1
		String path = requestURI.substring(contextPath.length());
		if ("/regist.jsp".equals(path) || "/RegistServlet".equals(path)
				|| "/login.jsp".equals(path) || "/LoginServlet".equals(path)
				|| "/logout.jsp".equals(path) || "/LogoutServlet".equals(path)) {
			//�������Զ���¼
			chain.doFilter(request, response);
		}
		else {
			//���Զ���¼���߼�
			if (request.getSession().getAttribute("loginUser")!=null) {
				chain.doFilter(request, response);
			}else {
				//˵��û��¼
				Cookie[] cookies= request.getCookies();
				Cookie targetCookie = findTargetCookie(cookies,"autologin");
				if (targetCookie==null) {
					chain.doFilter(request, response);
				}else {
					String value = targetCookie.getValue();
					String username= value.split("#guxiang#")[0];
					String password= value.split("#guxiang#")[1];
					
					//����ҵ���ȥ��¼
					UserService us = new UserService();
					User loginUser = us.autologin(username,password);
					
					if (loginUser==null) {
						//˵���� cookie����Ϣ���۸Ĺ�
						Cookie cookie = new Cookie("autologin", "");
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						
						chain.doFilter(request, response);
					}else {
						//��¼�ɹ�
						request.getSession().setAttribute("loginUser", loginUser);
						chain.doFilter(request, response);
					}
				}
				
			}
		}
	}

	private  Cookie findTargetCookie(Cookie[] cookies,String name) {
		if (cookies==null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
