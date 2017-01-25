package com.guxiang.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.guxiang.dao.OrderDao;
import com.guxiang.domain.Order;
import com.guxiang.domain.OrderItem;
import com.guxiang.domain.User;
import com.guxiang.utils.JdbcUtils;
import com.guxiang.utils.TransactionUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void insertOrder(Order order) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,0,?)";
		String time = new Date().toLocaleString();
		runner.update(TransactionUtil.getConnection(), sql, order.getId(),
				order.getMoney(), time, order.getReceiverinfo(),
				order.getUser_id());

	}

	@Override
	public List<Order> getAllOrdersInfo() {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		
		String sql1 = "select orders.*,users.nickname from orders,users  where orders.user_id=users.id";
		try {
			 List<Order> orders = runner.query(sql1, new BeanListHandler<Order>(Order.class));
		String sql2 = "select orderitems.*,products.name, products.price from orderitems,products where orderitems.product_id=products.id and orderitems.order_id=?";
		for (Order order : orders) {
			 List<OrderItem> orderItems = runner.query(sql2, new BeanListHandler<OrderItem>(OrderItem.class),order.getId());
			 order.setOrderItems(orderItems);
		}
		return orders;
		
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public List<Order> getCorrespondOrdersInfo(User loginUser) {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		
		String sql1 = "select orders.*,users.nickname from orders,users  where orders.user_id=users.id and users.id=?";
		try {
			 List<Order> orders = runner.query(sql1, new BeanListHandler<Order>(Order.class),loginUser.getId());
		String sql2 = "select orderitems.*,products.name, products.price from orderitems,products where orderitems.product_id=products.id and orderitems.order_id=?";
		for (Order order : orders) {
			 List<OrderItem> orderItems = runner.query(sql2, new BeanListHandler<OrderItem>(OrderItem.class),order.getId());
			 order.setOrderItems(orderItems);
		}
		return orders;
		
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	public void updateOrderStatus(String r6_Order) {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "update orders set paystate=1 where id=?";
		try {
			runner.update(sql,r6_Order);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
