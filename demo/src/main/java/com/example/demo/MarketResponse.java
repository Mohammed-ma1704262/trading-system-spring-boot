package com.example.demo;

public class MarketResponse {
	private String stockSymbol;
	private double bestBid; // Highest buy price
	private double bestAsk; // Lowest sell price
	private double lastTradePrice;

	public MarketResponse(String stockSymbol, double bestBid, double bestAsk, double lastTradePrice) {
		super();
		this.stockSymbol = stockSymbol;
		this.bestBid = bestBid;
		this.bestAsk = bestAsk;
		this.lastTradePrice = lastTradePrice;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public double getBestBid() {
		return bestBid;
	}

	public void setBestBid(double bestBid) {
		this.bestBid = bestBid;
	}

	public double getBestAsk() {
		return bestAsk;
	}

	public void setBestAsk(double bestAsk) {
		this.bestAsk = bestAsk;
	}

	public double getLastTradePrice() {
		return lastTradePrice;
	}

	public void setLastTradePrice(double lastTradePrice) {
		this.lastTradePrice = lastTradePrice;
	}

}