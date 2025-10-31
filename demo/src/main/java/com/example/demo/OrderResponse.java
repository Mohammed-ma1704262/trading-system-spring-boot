package com.example.demo;

public class OrderResponse {
	private String userID;
	private String stockSymbol;
	private String orderType;
	private int quantity;
	private double price;

	public OrderResponse(String userId, String stockSymbol, String orderType, int quantity, double price) {
		super();
		this.userID = userId;
		this.stockSymbol = stockSymbol;
		this.orderType = orderType;
		this.quantity = quantity;
		this.price = price;
	}

	public String getUserId() {
		return userID;
	}

	public void setUserId(String userId) {
		this.userID = userId;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}