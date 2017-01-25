package com.guxiang.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guxiang.domain.Order;
import com.guxiang.domain.User;
import com.guxiang.service.OrderService;

public class ListOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loginUser=  (User) request.getSession().getAttribute("loginUser");
		OrderService os = new OrderService();
		List<Order> orders = os.getOrderInfo(loginUser);
		
		request.getSession().setAttribute("orders", orders);
		request.getRequestDispatcher("/listorders.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
