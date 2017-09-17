package com.chinasofti.ordersys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfo {

	private int orderId;
	
	private String orderBeginDate;
	
	private String orderEndDate;

	private User waiter;
	
	private int orderState;
	
	private int tableId;
	
	private int sumPrice;
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderBeginDate() {
		return orderBeginDate;
	}

	public void setOrderBeginDate(String orderBeginDate) {
		this.orderBeginDate = orderBeginDate;
	}

	public String getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public User getWaiter() {
		return waiter;
	}

	public void setWaiter(User waiter) {
		this.waiter = waiter;
	}

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public OrderInfo() {
		super();
		waiter = new User();
	}

	public int getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", orderBeginDate=" + orderBeginDate + ", orderEndDate=" + orderEndDate + ", waiter=" + waiter + ", orderState=" + orderState + ", tableId=" + tableId + ", sumPrice=" + sumPrice + "]";
	}

	
}
