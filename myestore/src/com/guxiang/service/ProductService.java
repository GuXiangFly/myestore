package com.guxiang.service;

import java.util.List;

import com.guxiang.dao.ProductDao;
import com.guxiang.dao.factory.DaoFactory;
import com.guxiang.domain.Products;

public class ProductService {

	private ProductDao  pdao = DaoFactory.getInstance().createDao(ProductDao.class);
	
	public void addProduct(Products prodect) {
		// TODO Auto-generated method stub
		pdao.insertProduct(prodect);
		
	}

	public List<Products> getAllProducts() {
		List<Products> list= pdao.getAll();
		return list;
	}

	public Products getProductsById(String id) {
		// TODO Auto-generated method stub
		return pdao.getProductById(id);
	}


}
