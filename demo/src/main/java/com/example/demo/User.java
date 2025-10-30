package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class User {

	private int userID;
	private double balance = 10000.0;

	// ok each user will have different stocks , and the Integer will specify how
	// many of that stock they have
	// example <QIIB , 100 >
	Map<Stock, Integer> StocksHashMap = new HashMap<Stock, Integer>();

	public User(int userID) {
		super();
		this.userID = userID;
	}

	public User(int userID, Map<Stock, Integer> StocksHashMap) {
		super();
		this.userID = userID;

		// calculate the total to get it from balance , and add each stock of the map to
		// the object stock map
		this.StocksHashMap.putAll(StocksHashMap);
		double temp = 0.0;
		for (Map.Entry mapElement : StocksHashMap.entrySet()) {
			Stock key = (Stock) mapElement.getKey();

			int value = (int) mapElement.getValue();

			temp = temp + (key.getPricePerShare() * value);

			System.out.println(key + " : " + temp);
		}

		this.balance = balance - temp;

		System.out.println("New Balance" + " : " + balance);
	}

	public int getUserID() {
		return userID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Map<Stock, Integer> getStocksHashMap() {
		return StocksHashMap;
	}

	public void setStocksHashMap(Map<Stock, Integer> stocksHashMap) {
		StocksHashMap.putAll(stocksHashMap);
		double temp = 0.0;
		for (Map.Entry mapElement : StocksHashMap.entrySet()) {
			Stock key = (Stock) mapElement.getKey();

			int value = (int) mapElement.getValue();

			temp = temp + (key.getPricePerShare() * value);

			System.out.println(key + " : " + temp);
		}

		this.balance = this.balance - temp;

		System.out.println("New Balance" + " : " + balance);

	}

	public void addStocksToUser(Stock stockToAdd, Integer howMany) {

		double temp = 0.0;
		for (Map.Entry mapElement : StocksHashMap.entrySet()) {
			Stock key = (Stock) mapElement.getKey();

			if (stockToAdd.equals(key)) {
				int value = (int) mapElement.getValue();
				mapElement.setValue(value + howMany);

				temp = temp + (key.getPricePerShare() * howMany);

				this.balance = this.balance - temp;
				return;
			}

		}
		this.StocksHashMap.put(stockToAdd, howMany);
		this.balance = this.balance - (stockToAdd.getPricePerShare() * howMany);

	}

}
