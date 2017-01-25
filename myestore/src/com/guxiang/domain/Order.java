/**
 * author:guxiang
 * create on 2017 2017年1月15日 下午12:45:21
 */
package com.guxiang.domain;

import java.util.Date;
import java.util.List;

/**
 * @author guxiang
 * @time   2017 2017年1月15日 下午12:45:21
 *  create table orders(
	id varchar(50) PRIMARY key,
	moneys double,
	ordertime datetime,
	receverinfo varchar(255),
	paystate int,
	user_id int,
	foreign key(user_id) references users(id)
);
 */
public class Order {
	private String id;
	private Double money;
	private Date ordertime;
	private String receiverinfo;
	private int paystate;
	private int user_id;
	private String nickname;
	
	/**
	 * @return the orderItems
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	private List<OrderItem> orderItems;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the money
	 */
	public Double getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(Double money) {
		this.money = money;
	}
	/**
	 * @return the ordertime
	 */
	public Date getOrdertime() {
		return ordertime;
	}
	/**
	 * @param ordertime the ordertime to set
	 */
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	/**
	 * @return the receiverinfo
	 */
	public String getReceiverinfo() {
		return receiverinfo;
	}
	/**
	 * @param receiverinfo the receiverinfo to set
	 */
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	/**
	 * @return the paystate
	 */
	public int getPaystate() {
		return paystate;
	}
	/**
	 * @param paystate the paystate to set
	 */
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
