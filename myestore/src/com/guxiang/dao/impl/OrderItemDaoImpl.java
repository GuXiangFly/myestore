package com.guxiang.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.guxiang.dao.OrderItemDao;
import com.guxiang.domain.OrderItem;
import com.guxiang.utils.TransactionUtil;

public class OrderItemDaoImpl implements OrderItemDao {

	@Override
	public void insertOrderItem(OrderItem orderItem) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitems  values (?,?,?)";
		runner.update(TransactionUtil.getConnection(), sql,
				orderItem.getOrder_id(), orderItem.getProduct_id(),
				orderItem.getBuynum());
		
	}

}
