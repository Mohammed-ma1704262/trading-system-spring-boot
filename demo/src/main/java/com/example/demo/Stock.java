package com.example.demo;

public class Stock {

	private String stockSymbol;
	private double pricePerShare;

//	public Stock() {
//	}

	public Stock(String stockSymbol, double pricePerShare) {
		super();
		this.stockSymbol = stockSymbol;
		this.pricePerShare = pricePerShare;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public double getPricePerShare() {
		return pricePerShare;
	}

	public void setPricePerShare(int pricePerShare) {
		this.pricePerShare = pricePerShare;
	}

	@Override
	public String toString() {
		return "Stock [stockSymbol=" + stockSymbol + ", pricePerShare=" + pricePerShare + "]";
	}

}
