package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderMang {

	private int userID;

	private int orderID;
	private String orderType;
	private int orderQuantity;

	private String orderStatus;
	private String timeStamp;

//	private Stock stock;
	private String stockSymbol;
	private double pricePerShare;

//	public OrderMang(int userID, String stockSymbol, String orderType, int orderQuantity, int pricePerShare,
//			String orderStatus) {
//		super();
//		this.orderID = (int) (Math.random() * 10001);
//		this.userID = userID;
//		this.stockSymbol = stockSymbol;
//
//		if (orderType.toUpperCase() == "BUY" || orderType.toUpperCase() == "SELL") {
//
//			this.orderType = orderType.toUpperCase();
//		}
//
//		else if (orderType.toUpperCase() != "BUY" || orderType.toUpperCase() != "SELL") {
//			System.err.println("Error , you can only have the type to be eaither BUY or SELL check the spelling");
//		}
//
//		this.orderQuantity = orderQuantity;
//		this.pricePerShare = pricePerShare;
//		this.orderStatus = orderStatus;
//
//		LocalDateTime myDateObj = LocalDateTime.now();
//		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//		this.timeStamp = myDateObj.format(myFormatObj);
//	}

	public OrderMang(int userID, String stockSymbol, double pricePerShare, String orderType, int orderQuantity) {
		super();
		this.orderID = (int) (Math.random() * 10001);
		this.userID = userID;
//		this.stock = stock;
		this.pricePerShare = pricePerShare;
		this.stockSymbol = stockSymbol;

		if (orderType.toUpperCase() == "BUY" || orderType.toUpperCase() == "SELL") {

			this.orderType = orderType.toUpperCase();
		}

		else if (orderType.toUpperCase() != "BUY" || orderType.toUpperCase() != "SELL") {
			System.err.println("Error , you can only have the type to be eaither BUY or SELL check the spelling");
		}

		this.orderQuantity = orderQuantity;

		this.orderStatus = "PENDING";

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.timeStamp = myDateObj.format(myFormatObj);
	}

//	public Stock getStock() {
//		return stock;
//	}
//
//	public void setStock(Stock stock) {
//		this.stock = stock;
//	}

//	public void setUserID(int userID) {
//		this.userID = userID;
//	}

	public int getUserID() {
		return userID;
	}

	public int getOrderID() {
		return orderID;
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
		if (orderType.toUpperCase() == "BUY" || orderType.toUpperCase() == "SELL") {

			this.orderType = orderType.toUpperCase();
		}

		else {
			System.err.println("Error , you can only place BUY or SELL check the spelling");
		}
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
//		if (orderQuantity >= this.orderQuantity)
//			this.orderQuantity = this.orderQuantity - orderQuantity;
//		else
//			System.err.println("Smth went wrong...");

		this.orderQuantity = orderQuantity;
	}

	public double getPricePerShare() {
		return pricePerShare;
	}

	public void setPricePerShare(int pricePerShare) {
		this.pricePerShare = pricePerShare;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatusToChange) {
		if (orderStatusToChange.toUpperCase() == "PENDING" || orderStatusToChange.toUpperCase() == "EXECUTED") {

			this.orderStatus = orderStatusToChange.toUpperCase();
		}
		if (orderStatusToChange.toUpperCase() == "CANCELLED") {
			if (this.orderStatus.toUpperCase() == "PENDING")
				this.orderStatus = orderStatusToChange.toUpperCase();
			else {
				System.err.println("Error , You can only cancel orders that are of status pending");
			}
		}

		else {
			System.err.println("Error , only place PENDING, EXECUTED, Or CANCELLED  check the spelling");
		}

	}

	public String getTimeStamp() {
//		LocalDateTime myDateObj = LocalDateTime.now();
//		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//		String timeStamp = myDateObj.format(myFormatObj);
		return timeStamp;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", userID=" + userID + ", orderType=" + orderType + ", orderQuantity="
				+ orderQuantity + ", orderStatus=" + orderStatus + ", timeStamp=" + timeStamp + ", stockSymbol="
				+ stockSymbol + ", pricePerShare=" + pricePerShare + "]";
	}

}
