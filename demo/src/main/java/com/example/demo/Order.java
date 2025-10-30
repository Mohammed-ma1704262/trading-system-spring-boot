//package com.example.demo;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class Order {
//
//	private int orderID;
//	private String orderType;
//
//	private String orderStatus;
//	private String timeStamp;
//
//	public Order(String orderType, String timeStamp) {
//		super();
//		this.orderID = (int) (Math.random() * 10001);
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
//		this.orderStatus = "PENDING";
//
//		this.timeStamp = timeStamp;
//	}
//
//	public int getOrderID() {
//		return orderID;
//	}
//
//	public String getOrderType() {
//		return orderType;
//	}
//
//	public void setOrderType(String orderType) {
//		if (orderType.toUpperCase() == "BUY" || orderType.toUpperCase() == "SELL") {
//
//			this.orderType = orderType.toUpperCase();
//		}
//
//		else {
//			System.err.println("Error , you can only place BUY or SELL check the spelling");
//		}
//	}
//
//	public String getOrderStatus() {
//		return orderStatus;
//	}
//
//	public void setOrderStatus(String orderStatusToChange) {
//		if (orderStatusToChange.toUpperCase() == "PENDING" || orderStatusToChange.toUpperCase() == "EXECUTED") {
//
//			this.orderStatus = orderStatusToChange.toUpperCase();
//		}
//		if (orderStatusToChange.toUpperCase() == "CANCELLED") {
//			if (this.orderStatus.toUpperCase() == "PENDING")
//				this.orderStatus = orderStatusToChange.toUpperCase();
//			else {
//				System.err.println("Error , You can only cancel orders that are of status pending");
//			}
//		}
//
//		else {
//			System.err.println("Error , only place PENDING, EXECUTED, Or CANCELLED  check the spelling");
//		}
//
//	}
////	public void setOrderStatus(String orderStatus) {
////		this.orderStatus = orderStatus;
////	}
//
//	public String getTimeStamp() {
//		return timeStamp;
//	}
//
//	public void setTimeStamp(String timeStamp) {
//		this.timeStamp = timeStamp;
//	}
//
//	@Override
//	public String toString() {
//		return "Order [orderID=" + orderID + ", orderType=" + orderType + ", orderStatus=" + orderStatus
//				+ ", timeStamp=" + timeStamp + "]";
//	}
//
//}
