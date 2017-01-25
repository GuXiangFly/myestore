package com.guxiang.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.guxiang.domain.Order;
import com.guxiang.domain.OrderItem;
import com.guxiang.domain.Products;
import com.guxiang.domain.User;
import com.guxiang.service.OrderService;

/**
 * @author guxiang
 * @time   2017 2017��1��21�� ����1:49:47
 * ���ɶ�����servlet
 */
public class GenerateOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		order.setUser_id(loginUser.getId());
		//׼��һ���������� orderitems
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
	    Map<Products, Integer> cart=(Map<Products, Integer>) request.getSession().getAttribute("cart");
	    Set<Products> keys = cart.keySet();
	    
	    for (Products product : keys) {
			OrderItem oi = new OrderItem();
			oi.setProduct_id(product.getId());
			oi.setBuynum(cart.get(product));
			orderItems.add(oi);
		}
	    order.setOrderItems(orderItems);
	    
	    //�Ƴ����ﳵ
	    request.getSession().removeAttribute("cart");
	    
	    //����ҵ��㷽��
	    OrderService os = new OrderService();
	    os.generateOrder(order);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
