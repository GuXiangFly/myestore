package com.guxiang.dao;

import java.sql.SQLException;
import java.util.List;

import com.guxiang.domain.Order;
import com.guxiang.domain.User;

/**
 * @author guxiang
 * @time   2017 2017年1月25日 上午11:57:45
 */
public interface OrderDao {
	
	void insertOrder(Order order) throws SQLException;

	List<Order> getAllOrdersInfo();

	List<Order> getCorrespondOrdersInfo(User loginUser);

	void updateOrderStatus(String r6_Order);

}
