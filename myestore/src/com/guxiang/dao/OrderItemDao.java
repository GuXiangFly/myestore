package com.guxiang.dao;

import java.sql.SQLException;

import com.guxiang.domain.OrderItem;

public interface OrderItemDao {

	void insertOrderItem(OrderItem orderItem) throws SQLException;

}
