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

	ArrayList<Trade> tradeList = new ArrayList<Trade>();

	@GetMapping("/api/health-check")
	public String getHealthCheck() {
		return "Health is Normal!";
	}

	// returns All Current Users Portofolios
	@GetMapping("/api/users")
	public Portofolio[] getAllUsers() {
		return usersPortfolios;
	}

	public Portofolio getUserById(int UserID) {
		for (int i = 0; i < usersPortfolios.length; i++) {
			if (usersPortfolios[i].getUserID() == UserID) {
				return usersPortfolios[i];
			}
		}
		return null;
	}

	// getting a specific user via his ID
	@GetMapping("/api/user/{UserID}")
	public Portofolio getSepcUser(@PathVariable int UserID) {
		return getUserById(UserID);
	}

	// required API Endpoints

	//// //// //// //// //// //// //// //// //// ////

	//// Order End Point
	// Create a new order (buy or sell)

	// Response :: Created order with ID and status

	// the order type will be sent via the request params
	@PostMapping("/api/orders")
	public OrderMang createNewOrder(@RequestParam int userID, @RequestParam String orderType,
			@RequestParam String stockSymbol, @RequestParam double pricePerShare, @RequestParam double priceProposed,
			@RequestParam int orderQuantity) {

		OrderMang newOrder = null;

		// 1. we want to make sure that the user doesn't own the stocks he wants to buy

		// 2. in case of selling , we want to make sure that the user still has enough
		// to sell

		for (int i = 0; i < usersPortfolios.length; i++) {
			if (orderType.toUpperCase() == "BUY") {
				if (usersPortfolios[i].getUserID() == userID) {
					if (usersPortfolios[i].getStockOwned().getStockSymbol() == stockSymbol)
						return null;
					else
						break;
				}

			} else if (orderType.toUpperCase() == "SELL") {
				if (usersPortfolios[i].getUserID() == userID
						&& usersPortfolios[i].getStockOwned().getStockSymbol() == stockSymbol) {
					if (usersPortfolios[i].getQuntitiyOwned() >= orderQuantity) {
						usersPortfolios[i].setQuntitiyOwned(usersPortfolios[i].getQuntitiyOwned() - orderQuantity);
						break;

					} else
						return null;
				}
			}

			newOrder = new OrderMang(userID, stockSymbol, pricePerShare, orderType, orderQuantity);

			orderList.add(newOrder);
		}

		// 3. after order has finished we want to match buying or selling

		// the order type symbole quantitiy etc are all coming from the params

		// so we need to match via the symbole

		if (orderList.size() > 1) {
			for (int i = 0; i < orderList.size(); i++) {

				// must be the same symboles
				if (orderList.get(i).getStockSymbol() == stockSymbol && orderType == "BUY"
						&& orderList.get(i).getOrderType() == "SELL") {

					// for the order to sell we will decrease the quntity of the stocks , and
					// increase the balance of that user

					// for the user who is buying , we will decrease the balance
					// and add the stock to the list of owned stocks

					// we need to change the orderStatus to Executed

					// and add it to the executed list

					if (orderList.get(i).getOrderStatus() == "PENDING") {

						// decrease qunatity
						Portofolio userSelling = getUserById(orderList.get(i).getUserID());

						Portofolio userBuying = getUserById(userID);

						int howManyToSell = orderList.get(i).getOrderQuantity();

						int newQuantity = userSelling.getQuntitiyOwned() - howManyToSell;

						if (newQuantity < 0)
							return null;

						// sell order can be executed if there's a buy order at or above the sell price
						if (priceProposed >= orderList.get(i).getPricePerShare() * orderQuantity) {

							orderList.get(i).setOrderQuantity(newQuantity);

							// increase the balance of that user

							userSelling.setBalance(userSelling.getBalance() + priceProposed);

							// for the user who is buying , we will decrease the balance

							userBuying.setBalance(userBuying.getBalance() - priceProposed);

							Stock tempStock = new Stock(orderList.get(i).getStockSymbol(),
									orderList.get(i).getPricePerShare());

							// and add the stock to the list of owned stocks
							userBuying.addOtherStocks(tempStock, orderQuantity);

							orderList.get(i).setOrderStatus("EXECUTED");

							// orderBuyId , OrderSellId , StockSymbole of what was traded ,Quntitiy that was
							// exchanged , executionPrice that was agreed on quantity*stockpricePerShare
							// (int orderBuyID, int orderSellID, String stockSymbole, int qunantity, double
							// executionPrice)
							Trade tradeTemp = new Trade(newOrder.getOrderID(), orderList.get(i).getOrderID(),
									stockSymbol, orderQuantity, priceProposed);

							tradeList.add(tradeTemp);

						}

					} else if (orderList.get(i).getStockSymbol() == stockSymbol && orderType == "SELL"
							&& orderList.get(i).getOrderType() == "BUY") {

						if (orderList.get(i).getOrderStatus() == "PENDING") {

							// decrease qunatity
							Portofolio userSelling = getUserById(userID);

							Portofolio userBuying = getUserById(orderList.get(i).getUserID());

							int howManyToSell = orderList.get(i).getOrderQuantity();

							int newQuantity = userSelling.getQuntitiyOwned() - howManyToSell;

							if (newQuantity < 0)
								return null;

							// Buy order can be executed if there's a buy order at or less than the sell
							// price
							if (priceProposed <= orderList.get(i).getPricePerShare() * orderQuantity) {

								orderList.get(i).setOrderQuantity(newQuantity);

								// increase the balance of that user

								userSelling.setBalance(userSelling.getBalance() + priceProposed);

								// for the user who is buying , we will decrease the balance

								userBuying.setBalance(userBuying.getBalance() - priceProposed);

								Stock tempStock = new Stock(orderList.get(i).getStockSymbol(),
										orderList.get(i).getPricePerShare());

								// and add the stock to the list of owned stocks
								userBuying.addOtherStocks(tempStock, orderQuantity);

								orderList.get(i).setOrderStatus("EXECUTED");

								// orderBuyId , OrderSellId , StockSymbole of what was traded ,Quntitiy that was
								// exchanged , executionPrice that was agreed on quantity*stockpricePerShare
								Trade tradeTemp = new Trade(orderList.get(i).getOrderID(), newOrder.getOrderID(),
										stockSymbol, orderQuantity, priceProposed);

								tradeList.add(tradeTemp);

							}
						}
					}

				} else {
					// it was not found
					return null;
				}
			}
		}
		// 4. if executed we need to add it to the executed list

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
