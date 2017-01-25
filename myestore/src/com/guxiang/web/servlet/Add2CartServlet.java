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

		// ��ù��ﳵ
		Map<Products, Integer> cart = (Map<Products, Integer>) request
				.getSession().getAttribute("cart");
		if (cart == null) {
			// ��˳��
			cart = new LinkedHashMap<Products, Integer>();
		}
		if (cart.containsKey(p)) {
			//+1s
			Integer count = cart.get(p);
			cart.put(p, count+1);
		}else {
			//û���
			ProductService ps = new ProductService();
			Products pp =ps.getProductsById(id);
			cart.put(pp, 1);
		}
		request.getSession().setAttribute("cart", cart);
		request.getSession().setAttribute("message", "�����Ϣ�ɹ�");
		request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
