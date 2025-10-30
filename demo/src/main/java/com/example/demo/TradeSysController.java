package com.example.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TradeSysController {

	// Types of stocks Available QIBK, QNBK, MARK
	Stock stockQIBK = new Stock("QIBK", 1500);
	Stock stockQNBK = new Stock("QNBK", 1000);
	Stock stockMARK = new Stock("MARK", 2000);

	// created some profiles for some users
	Portofolio user1 = new Portofolio(12345, stockQIBK, 100);
	Portofolio user2 = new Portofolio(67890, stockQNBK, 100);
	Portofolio user3 = new Portofolio(10112, stockMARK, 100);

	Portofolio[] usersPortfolios = { user1, user2, user3 };

	ArrayList<OrderMang> orderList = new ArrayList<OrderMang>();

	@GetMapping("/api/health-check")
	public String getHealthCheck() {
		return "Health is Normal!";
	}

	// returns All Current Users Portofolios
	@GetMapping("/api/users")
	public Portofolio[] getAllUsers() {
		return usersPortfolios;
	}

	// getting a specific user via his ID
	@GetMapping("/api/user/{UserID}")
	public Portofolio getSepcUser(@PathVariable int UserID) {
		for (int i = 0; i < usersPortfolios.length; i++) {
			if (usersPortfolios[i].getUserID() == UserID) {
				return usersPortfolios[i];
			}
		}
		return null;
	}

	// required API Endpoints

	//// //// //// //// //// //// //// //// //// ////

	//// Order End Point
	// Create a new order (buy or sell)

	// Response :: Created order with ID and status
	@PostMapping("/api/orders")
	public OrderMang createNewOrder(@RequestParam int userID, @RequestParam String orderType,
			@RequestParam String stockSymbol, @RequestParam double pricePerShare, @RequestParam int orderQuantity) {

		// 1. we want to make sure that the user doesn't own the stocks he wants to buy
		
		
		if (orderType.toUpperCase()=="BUY")
		{
			
		}
		
		OrderMang newOrder = new OrderMang(userID, stockSymbol, pricePerShare, orderType, orderQuantity);

		orderList.add(newOrder);
		
		// 2. in case of selling , we want to make sure that the user still has enough to sell
		
		
		
		
		// 3. after selling we want to match buying or selling
		
		
		
		//4. if executed we need to add it to the executed list

		return newOrder;

	}

	// Get order details by ID
	@GetMapping("/api/orders/{orderId}")
	public OrderMang getOrderById(@PathVariable int orderId) {
		for (OrderMang order : orderList) {

			if (order.getOrderID() == orderId)
				return order;

		}

		System.err.println("ERROR! Order was not found");
		return null;
	}

	// Get all orders for a specific user
	@GetMapping("/api/orders/users/{userId}")
	public ArrayList<OrderMang> getAllOrdersForAUser(@PathVariable int userId) {

		ArrayList<OrderMang> orderListTemp = new ArrayList<OrderMang>();

		for (OrderMang order : orderList) {

			if (order.getUserID() == userId)
				orderListTemp.add(order);
		}

		// we will return list so it can also be empty
		return orderListTemp;
	}

	// Cancel a pending order
	@DeleteMapping("/api/orders/{orderId}")
	public void cancelAPendingOrder(@PathVariable int orderId) {
	}

	//// //// //// //// //// //// //// //// //// ////

	//// Trade End Point

	// Get all executed trades
	@GetMapping("/api/trades")
	public void getAllExecutedTrades() {
	}

	// Get all trades for a specific user
	@GetMapping("/api/trades/user/{userId}")
	public void getAllExecutedTradesForASpecificUser(@PathVariable int userId) {
	}

	// Get all trades for a specific stock
	@GetMapping("/api/trades/stock/{symbol}")
	public void getAllTradesForASpecificStock(@PathVariable String symbol) {
	}

	//// //// //// //// //// //// //// //// //// ////

	//// Portfolio End points

	// Get user's complete portfolio (holdings and cash balance)
	@GetMapping("/api/portfolio/{userId}")
	public void getUserCompletePortofolio(@PathVariable int userId) {
	}

	// Get user's position for a specific stock
	@GetMapping("/api/portfolio/{userId}/stock/{symbol}")
	public void getUserCompletePortofolio(@PathVariable int userId, @PathVariable String symbol) {
	}

	//// //// //// //// //// //// //// //// //// ////

	//// Market Data End points

	// Get current market data for a stock (best bid, best ask, last trade price)
	@GetMapping("/api/market/{symbol}")
	public void getCurrentMarketDataForAStock(@PathVariable String symbol) {
	}

}
