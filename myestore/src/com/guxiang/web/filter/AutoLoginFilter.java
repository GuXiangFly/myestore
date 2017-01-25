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
		// 获得要访问的资源的路径
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 获得想要访问的资源路径
		// 这个型似于 /myestore/servlet1
		String requestURI = request.getRequestURI();

		// web工程的对外访问路径
		// /myestore
		String contextPath = request.getContextPath();

		// 获得资源访问路径
		// servlet1
		String path = requestURI.substring(contextPath.length());
		if ("/regist.jsp".equals(path) || "/RegistServlet".equals(path)
				|| "/login.jsp".equals(path) || "/LoginServlet".equals(path)
				|| "/logout.jsp".equals(path) || "/LogoutServlet".equals(path)) {
			//不改走自动登录
			chain.doFilter(request, response);
		}
		else {
			//走自动登录的逻辑
			if (request.getSession().getAttribute("loginUser")!=null) {
				chain.doFilter(request, response);
			}else {
				//说明没登录
				Cookie[] cookies= request.getCookies();
				Cookie targetCookie = findTargetCookie(cookies,"autologin");
				if (targetCookie==null) {
					chain.doFilter(request, response);
				}else {
					String value = targetCookie.getValue();
					String username= value.split("#guxiang#")[0];
					String password= value.split("#guxiang#")[1];
					
					//调用业务层去登录
					UserService us = new UserService();
					User loginUser = us.autologin(username,password);
					
					if (loginUser==null) {
						//说明了 cookie的信息被篡改过
						Cookie cookie = new Cookie("autologin", "");
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
						
						chain.doFilter(request, response);
					}else {
						//登录成功
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
