package com.guxiang.web.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guxiang.domain.Products;
import com.guxiang.service.ProductService;

public class Add2CartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Products p = new Products();
		p.setId(id);

		// 获得购物车
		Map<Products, Integer> cart = (Map<Products, Integer>) request
				.getSession().getAttribute("cart");
		if (cart == null) {
			// 有顺序
			cart = new LinkedHashMap<Products, Integer>();
		}
		if (cart.containsKey(p)) {
			//+1s
			Integer count = cart.get(p);
			cart.put(p, count+1);
		}else {
			//没买过
			ProductService ps = new ProductService();
			Products pp =ps.getProductsById(id);
			cart.put(pp, 1);
		}
		request.getSession().setAttribute("cart", cart);
		request.getSession().setAttribute("message", "添加信息成功");
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
