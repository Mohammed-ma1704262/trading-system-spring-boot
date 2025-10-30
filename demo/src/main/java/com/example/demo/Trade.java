package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Trade {

	private int tradeID;
	private int orderBuyID;
	private int orderSellID;

	private String stockSymbole;
	private int Qunantity;
	private double executionPrice;

	private String timeStamp;

	public Trade(int orderBuyID, int orderSellID, String stockSymbole, int qunantity, double executionPrice) {
		super();
		this.tradeID = (int) (Math.random() * 10001);

		this.orderBuyID = orderBuyID;
		this.orderSellID = orderSellID;
		this.stockSymbole = stockSymbole;
		Qunantity = qunantity;
		this.executionPrice = executionPrice;

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.timeStamp = myDateObj.format(myFormatObj);
	}

	public int getOrderBuyID() {
		return orderBuyID;
	}

	public void setOrderBuyID(int orderBuyID) {
		this.orderBuyID = orderBuyID;
	}

	public int getOrderSellID() {
		return orderSellID;
	}

	public void setOrderSellID(int orderSellID) {
		this.orderSellID = orderSellID;
	}

	public String getStockSymbole() {
		return stockSymbole;
	}

	public void setStockSymbole(String stockSymbole) {
		this.stockSymbole = stockSymbole;
	}

	public int getQunantity() {
		return Qunantity;
	}

	public void setQunantity(int qunantity) {
		Qunantity = qunantity;
	}

	public double getExecutionPrice() {
		return executionPrice;
	}

	public void setExecutionPrice(double executionPrice) {
		this.executionPrice = executionPrice;
	}

	public int getTradeID() {
		return tradeID;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	@Override
	public String toString() {
		return "Trade [tradeID=" + tradeID + ", orderBuyID=" + orderBuyID + ", orderSellID=" + orderSellID
				+ ", stockSymbole=" + stockSymbole + ", Qunantity=" + Qunantity + ", executionPrice=" + executionPrice
				+ ", timeStamp=" + timeStamp + "]";
	}

}
