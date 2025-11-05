package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class Portofolio {
	private String userID;
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

	public Portofolio(String userID, Stock stockOwned, int quntitiyOwned) {
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

	public String getUserID() {
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

	public Map<Stock, Integer> getStockFromOtherOwnedStocks(String symbole) {

		Stock tempStock = null;

		Integer quantity = 0;
		for (Map.Entry mapElement : this.otherOwnedStocks.entrySet()) {
			Stock key = (Stock) mapElement.getKey();

			if (key.getStockSymbol().equals(symbole)) {
				tempStock = key;
				quantity = (Integer) mapElement.getValue();
				break;
			}

		}

		Map<Stock, Integer> tempMap = new HashMap<Stock, Integer>();

		tempMap.put(tempStock, quantity);

		return tempMap;
	}

	public boolean updateIfAlreadyOwnedStock(Stock stock, int quantity) {
		boolean exists = false;

		for (Map.Entry mapElement : this.otherOwnedStocks.entrySet()) {
			Stock key = (Stock) mapElement.getKey();

			if (key.getStockSymbol().equals(stock.getStockSymbol())) {
				int quntityAlreadyOwned = (int) mapElement.getValue();
				mapElement.setValue(quantity + quntityAlreadyOwned);
				exists = true;
				break;
			}

		}
		return exists;

	}

	public void addOtherStocks(Stock stock, int quantity) {
		if (stock.getStockSymbol() != this.stockOwned.getStockSymbol()) {
			if (!updateIfAlreadyOwnedStock(stock, quantity))
				this.otherOwnedStocks.put(stock, quantity);

		}

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
