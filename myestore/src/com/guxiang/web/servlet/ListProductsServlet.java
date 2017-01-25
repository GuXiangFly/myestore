package com.guxiang.web.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guxiang.domain.Products;
import com.guxiang.service.ProductService;

public class ListProductsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用业务层去查询
		ProductService ps = new ProductService();
		List<Products> products= ps.getAllProducts();
		
		request.getSession().setAttribute("products", products);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
