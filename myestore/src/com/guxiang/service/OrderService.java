package com.guxiang.service;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.DbUtils;

import com.guxiang.dao.OrderDao;
import com.guxiang.dao.OrderItemDao;
import com.guxiang.dao.ProductDao;
import com.guxiang.dao.factory.DaoFactory;
import com.guxiang.domain.Order;
import com.guxiang.domain.OrderItem;
import com.guxiang.domain.User;
import com.guxiang.utils.JdbcUtils;
import com.guxiang.utils.TransactionUtil;

public class OrderService {

	private OrderDao  odao = DaoFactory.getInstance().createDao(OrderDao.class);
	private OrderItemDao  oitemdao = DaoFactory.getInstance().createDao(OrderItemDao.class);
	private ProductDao  pdao = DaoFactory.getInstance().createDao(ProductDao.class);
	public void generateOrder(Order order) {
	
		
		try {
			//生成订单号
			String id = generateOrderId();
			order.setId(id);
			//开启事务
			TransactionUtil.startTransaction();
			odao.insertOrder(order);
			
			List<OrderItem> orderItems = order.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				orderItem.setOrder_id(id);
				//插入数据库
				oitemdao.insertOrderItem(orderItem);
				pdao.minusBuynum(orderItem);
			}
			//提交事务
			TransactionUtil.commit();
		} catch (Exception e) {
			//有异常回滚事务
			TransactionUtil.rollback();
			throw new RuntimeException(e+"生成订单失败");
		}
		finally{
			TransactionUtil.relase();
		}
	}
	
	public static String generateOrderId() {
		int hashCode = UUID.randomUUID().toString().hashCode();
		int abs = Math.abs(hashCode);
		return "order-"+abs;
	}

	public List<Order> getOrderInfo(User loginUser) {
		// TODO Auto-generated method stub
		if ("admin".equals(loginUser.getRole())) {
			return odao.getAllOrdersInfo();
		}
		else {
			return odao.getCorrespondOrdersInfo(loginUser);
		}
	}

	/**
	 * 修改订单状态
	 *  @param r6_Order 
	 */
	public void updateOrderState(String r6_Order) {
		// TODO Auto-generated method stub
		odao.updateOrderStatus(r6_Order);
	}
	
}
