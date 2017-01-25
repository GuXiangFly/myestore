package com.guxiang.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.runner.Runner;

import com.guxiang.dao.ProductDao;
import com.guxiang.domain.OrderItem;
import com.guxiang.domain.Products;
import com.guxiang.utils.JdbcUtils;
import com.guxiang.utils.TransactionUtil;

public class ProductDaoImpl implements ProductDao {

	public static String generatedId() {
		String value = UUID.randomUUID().toString();
		int hashCode = value.hashCode();
		return "product-" + Math.abs(hashCode);
	}

	@Override
	public void insertProduct(Products product) {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into products values(?,?,?,?,?,?,?)";
		try {
			runner.update(sql, generatedId(), product.getName(),
					product.getPrice(), product.getCategory(),
					product.getCount(), product.getImageurl(),
					product.getDescription());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Products> getAll() {
	
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from products";
		try {
			return runner.query(sql,new BeanListHandler<Products>(Products.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Products getProductById(String id) {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from products where id=?";
		try {
			return runner.query(sql,new BeanHandler<Products>(Products.class),id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void minusBuynum(OrderItem orderItem) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner runner = new QueryRunner();
		String sql = "select count from products where id=?";
		int count =(Integer) runner.query(TransactionUtil.getConnection(),sql, new ScalarHandler(),orderItem.getProduct_id());
		if (orderItem.getBuynum()>count) {
			throw new RuntimeException("库存不足，请重新下单");
		}
		String sql2 = "update products set count =count-? where id =?";
		runner.update(TransactionUtil.getConnection(),sql2,orderItem.getBuynum(),orderItem.getProduct_id());
		
	}

}
