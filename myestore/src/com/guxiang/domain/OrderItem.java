package com.guxiang.domain;

/**
 * @author guxiang
 * @time   2017 2017年1月15日 下午12:43:22
 * 
--关系表
create table orderitems(
	order_id varchar(50),
	product_id varchar(50),
	foreign key(order_id) references orders(id),
	foreign key(product_id) references products(id),
	buynum int
);
 */
public class OrderItem {
 
	private String order_id;
	private String product_id;
	private int buynum;
	
	private String name;
	private double price;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the order_id
	 */
	public String getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the product_id
	 */
	public String getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	/**
	 * @return the buynum
	 */
	public int getBuynum() {
		return buynum;
	}

	/**
	 * @param buynum the buynum to set
	 */
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}


	 
}
 
