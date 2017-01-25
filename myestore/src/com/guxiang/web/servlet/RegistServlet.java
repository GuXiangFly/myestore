package com.guxiang.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.guxiang.domain.User;
import com.guxiang.service.UserService;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��֤��У��
		String checkcode = request.getParameter("checkcode");
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		
		if (checkcode==null||checkcode.equalsIgnoreCase(checkcode_session)) {
			request.setAttribute("message",	"�Բ�����֤�벻��ȷ������������");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		User bean = new User();
		try {
			BeanUtils.populate(bean, request.getParameterMap());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
		//����ҵ��㷽��ȥע��
		UserService us =new UserService();
		us.regist(bean);
		
		//�ɹ�����ת
		request.setAttribute("message", "��ϲ��, ע��ɹ�...");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
