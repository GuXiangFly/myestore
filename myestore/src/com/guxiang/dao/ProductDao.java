package com.guxiang.dao;

import java.sql.SQLException;
import java.util.List;

import com.guxiang.domain.OrderItem;
import com.guxiang.domain.Products;

public interface ProductDao {

	void insertProduct(Products prodect);

	List<Products> getAll();

	Products getProductById(String id);
	
	void minusBuynum(OrderItem orderItem) throws SQLException;

}
