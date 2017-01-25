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
			//��Ȩ�����õ���Ҫ
			//�ж��Ƿ��¼
			if (request.getSession().getAttribute("loginUser")==null) {
				request.setAttribute("message", "�Բ��������ʵ���Դ��ҪȨ�� ���¼..");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}else {
				//˵���Ѿ���¼
				User loginUser = (User) request.getSession().getAttribute("loginUser");
				if (loginUser.getRole().equals("admin")) {
					//�h���ǹ���Ա
					if (adminList.contains(path)) {
						chain.doFilter(request, response);
					}
					else {
						throw new  RuntimeException("�Բ������ǹ���Ա����û�����Ȩ��");
					}
				}
				if (loginUser.getRole().equals("user")) {
				
					if (userList.contains(path)) {
						chain.doFilter(request, response);
					}
					else {
						throw new  RuntimeException("�Բ���������ͨ�û�����û�����Ȩ��");
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
