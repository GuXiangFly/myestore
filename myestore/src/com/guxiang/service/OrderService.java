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
			//���ɶ�����
			String id = generateOrderId();
			order.setId(id);
			//��������
			TransactionUtil.startTransaction();
			odao.insertOrder(order);
			
			List<OrderItem> orderItems = order.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				orderItem.setOrder_id(id);
				//�������ݿ�
				oitemdao.insertOrderItem(orderItem);
				pdao.minusBuynum(orderItem);
			}
			//�ύ����
			TransactionUtil.commit();
		} catch (Exception e) {
			//���쳣�ع�����
			TransactionUtil.rollback();
			throw new RuntimeException(e+"���ɶ���ʧ��");
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
	 * �޸Ķ���״̬
	 *  @param r6_Order 
	 */
	public void updateOrderState(String r6_Order) {
		// TODO Auto-generated method stub
		odao.updateOrderStatus(r6_Order);
	}
	
}
