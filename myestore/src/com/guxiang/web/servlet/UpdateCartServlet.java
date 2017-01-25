package com.guxiang.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guxiang.domain.Products;

public class UpdateCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		
		//放入购物车
		Map<Products, Integer> cart = (Map<Products, Integer>)request.getSession().getAttribute("cart");
		if (cart!=null) {
			Products product = new Products();
			product.setId(id);
			cart.put(product, Integer.parseInt(count));
		}
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
