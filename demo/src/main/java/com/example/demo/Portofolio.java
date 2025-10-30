package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class Portofolio {
	private int userID;
	private double balance;
	private Stock stockOwned;
//	private String stockSymbole;
//	private double stockPricePerShare;
	private int quntitiyOwned;
//	private double avgPurshasedPrice;
//	private double currentValue;

	// (stock , quantity)
	Map<Stock, Integer> otherOwnedStocks = new HashMap<Stock, Integer>();

//	public Portofolio(int userID, double balance, String stockSymbole, int quntitiyOwned, double avgPurshasedPrice) {
//		super();
//		this.balance = balance;
//		this.userID = userID;
//		this.stockSymbole = stockSymbole;
//		this.quntitiyOwned = quntitiyOwned;
//		this.avgPurshasedPrice = avgPurshasedPrice;
//	}

	public Portofolio(int userID, Stock stockOwned, int quntitiyOwned) {
		super();
		this.balance = 100000.0;
		this.userID = userID;
		this.stockOwned = stockOwned;
//		this.stockSymbole = stockSymbole;
//		this.stockPricePerShare = stockPricePerShare;
		this.quntitiyOwned = quntitiyOwned;
//		this.avgPurshasedPrice = avgPurshasedPrice;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getQuntitiyOwned() {
		return quntitiyOwned;
	}

	public void setQuntitiyOwned(int quntitiyOwned) {
		this.quntitiyOwned = quntitiyOwned;
	}

	public Stock getStockOwned() {
		return stockOwned;
	}

	public void setStockOwned(Stock stockOwned) {
		this.stockOwned = stockOwned;
	}

//	public String getStockSymbol() {
//		return stock.getStockSymbol();
//	}
//
//	public void setStockSymbol(String stockSymbol) {
//		this.stock.setStockSymbol(stockSymbol);
//	}
//
//	public double getPricePerShare() {
//		return stock.getPricePerShare();
//	}
//
//	public void setPricePerShare(int pricePerShare) {
//		this.stock.setPricePerShare(pricePerShare);
//	}

//	public void setStockPricePerShare(double stockPricePerShare) {
//		this.stockPricePerShare = stockPricePerShare;
//	}

	public int getUserID() {
		return userID;
	}

//	public String getStockSymbole() {
//		return stockSymbole;
//	}

	/// hmmmm things Don't make sense here
	public double getAvgPurshasedPrice() {
		return stockOwned.getPricePerShare() / quntitiyOwned;
	}

	public double getCurrentValue() {
		return quntitiyOwned * stockOwned.getPricePerShare();
	}

	public Map<Stock, Integer> getOtherOwnedStocks() {
		return otherOwnedStocks;
	}

	public void addOtherStocks(Stock stock, int quantity) {
		if (stock.getStockSymbol() != this.stockOwned.getStockSymbol())
			this.otherOwnedStocks.put(stock, quantity);
		else {
			System.err.println("You can only add other stocks , not the same stock you already own");
		}
	}

	@Override
	public String toString() {
		return "Portofolio [userID=" + userID + ", balance=" + balance + ", stockOwned=" + stockOwned
				+ ", quntitiyOwned=" + quntitiyOwned + ", otherOwnedStocks=" + otherOwnedStocks + "]";
	}

}
