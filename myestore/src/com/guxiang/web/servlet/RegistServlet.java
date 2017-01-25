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
		//验证码校验
		String checkcode = request.getParameter("checkcode");
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		
		if (checkcode==null||checkcode.equalsIgnoreCase(checkcode_session)) {
			request.setAttribute("message",	"对不起，验证码不正确，请重新输入");
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
		
		//调用业务层方法去注册
		UserService us =new UserService();
		us.regist(bean);
		
		//成功后跳转
		request.setAttribute("message", "恭喜您, 注册成功...");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
