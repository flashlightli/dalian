package com.chinasofti.ordersys.entity;

public class OrderDish {

	private int orderId;
	
	private int tableId;
	
	private Dish dish;
	
	private int num;

	public OrderDish() {
		dish = new Dish();
	}
	
	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	@Override
	public String toString() {
		return "OrderDish [orderId=" + orderId + ", tableId=" + tableId + ", dish=" + dish + ", num=" + num + "]";
	}



	
}
