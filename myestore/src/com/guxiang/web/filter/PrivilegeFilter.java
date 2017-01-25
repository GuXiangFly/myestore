package com.guxiang.web.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guxiang.domain.User;

public class PrivilegeFilter implements Filter {

	private List userList = new ArrayList();
	private List adminList = new ArrayList();
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (adminList.contains(path)||userList.contains(path)) {
			//有权限配置的需要
			//判断是否登录
			if (request.getSession().getAttribute("loginUser")==null) {
				request.setAttribute("message", "对不起，您访问的资源需要权限 请登录..");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}else {
				//说明已经登录
				User loginUser = (User) request.getSession().getAttribute("loginUser");
				if (loginUser.getRole().equals("admin")) {
					//説明是管理员
					if (adminList.contains(path)) {
						chain.doFilter(request, response);
					}
					else {
						throw new  RuntimeException("对不起，您是管理员，您没有这个权限");
					}
				}
				if (loginUser.getRole().equals("user")) {
				
					if (userList.contains(path)) {
						chain.doFilter(request, response);
					}
					else {
						throw new  RuntimeException("对不起，您是普通用户，您没有这个权限");
					}
				}
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		try {
			String adminPath = PrivilegeFilter.class.getClassLoader().getResource("admin.txt").getFile();
			String userPath = PrivilegeFilter.class.getClassLoader().getResource("user.txt").getFile();
			
			BufferedReader br1 = new BufferedReader(new FileReader(adminPath));
			String line1= null;
			while ((line1=br1.readLine())!=null) {
				adminList.add(line1);
			}
			
			BufferedReader br2 = new BufferedReader(new FileReader(userPath));
			String line2= null;
			while ((line2=br2.readLine())!=null) {
				userList.add(line2);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
