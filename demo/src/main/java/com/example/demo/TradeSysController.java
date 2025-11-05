package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	Portofolio user1 = new Portofolio("12345", stockQIBK, 1000);
	Portofolio user2 = new Portofolio("67890", stockQNBK, 1000);
	Portofolio user3 = new Portofolio("10112", stockMARK, 1000);

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

	public Portofolio getUserById(String UserID) {
		for (int i = 0; i < usersPortfolios.length; i++) {
			if (usersPortfolios[i].getUserID().equals(UserID)) {
				return usersPortfolios[i];
			}
		}
		return null;
	}

	// getting a specific user via his ID
	@GetMapping("/api/user/{UserID}")
	public Portofolio getSepcUser(@PathVariable String UserID) {
		return getUserById(UserID);
	}

	@GetMapping("/api/orders/all-orders")
	public ArrayList<OrderMang> getAllOrders() {
		return orderList;
	}

//	public void executeTrade(OrderMang orderToBuy, OrderMang orderToSell) {
//
//		if (!orderToBuy.getOrderStatus().equals("PENDING") || !orderToSell.getOrderStatus().equals("PENDING")) {
//
//			// in order to execute a trade both have to be pending types
//			return;
//
//		}
//		Portofolio userBuying = getUserById(orderToBuy.getUserID());
//		Portofolio userSelling = getUserById(orderToSell.getUserID());
//
//		/// order buying may cause an issue , as if we are buying lets say 10000 shares
//		/// but we have more than one share for selling , each on different prices ,
//		/// each is 250 shares , we need to ensure that the
//		/// algorithm will go over each selling share if the buying and selling
//		/// conditions meet
//
//		// how much the user buying is willing to offer ??
//		double userBuyingPid = orderToBuy.getOrderQuantity() * orderToBuy.getPricePerShare();
//
//		// how much the user selling is willing to take ??
//		double userSellingPid = orderToSell.getOrderQuantity() * orderToSell.getPricePerShare();
//
//		int howManyBuyerNeeds = orderToBuy.getOrderQuantity();
//
//		int newQuantity = userSelling.getQuntitiyOwned() - howManyBuyerNeeds;
//
//		if (newQuantity < 0) {
////			// this means 
////			break;
//			// a User was found , but quntity will be way less than what it can be sold ,
//			// thus we can't sell
//
//			/// so we can set the Quntity to stay to what the user has , or if the user
//			/// doesn't have any equals to zero , we cancel the sell order and buy order and
//			/// inform the user
//
////			if (userSelling.getQuntitiyOwned() == 0) {
////				System.err.println("You buy , your current invantory is equal to zero ");
////			return;
////			}
//
//			return;
//
//		}
//
//		// A buy order can be executed if there's a sell order at or below the buy price
//		/// buyorder>=sellorder
//		// A sell order can be executed if there's a buy order at or above the sellprice
//		/// buyorder>=sellorder
//
//		if (orderToBuy.getPricePerShare() >= orderToSell.getPricePerShare()) {
//
//			double totalAmount = orderToBuy.getPricePerShare() * howManyBuyerNeeds;
//
//			orderToSell.setOrderQuantity(orderToSell.getOrderQuantity() - howManyBuyerNeeds);
//			if (orderToSell.getOrderQuantity() == 0) {
//				orderToSell.setOrderStatus("EXECUTED");
//			}
//
//			// increase the balance of that user
//
//			userSelling.setBalance(userSelling.getBalance() + totalAmount);
//			userSelling.setQuntitiyOwned(userSelling.getQuntitiyOwned() - howManyBuyerNeeds);
//
//			// for the user who is buying , we will decrease the balance
//
//			userBuying.setBalance(userBuying.getBalance() - (totalAmount));
//
//			Stock tempStock = new Stock(orderToSell.getStockSymbol(), orderToSell.getPricePerShare());
//
//			// and add the stock to the list of owned stocks
//			userBuying.addOtherStocks(tempStock, howManyBuyerNeeds);
//
////			if (orderToBuy.getOrderQuantity() == howManyBuyerNeeds) {
////				orderToBuy.setOrderStatus("EXECUTED");
////			}
//
//			orderToBuy.setOrderStatus("EXECUTED");
//
//			// orderBuyId , OrderSellId , StockSymbole of what was traded ,Quntitiy that was
//			// exchanged , executionPrice that was agreed on quantity*stockpricePerShare
//			// (int orderBuyID, int orderSellID, String stockSymbole, int qunantity, double
//			// executionPrice)
//			Trade tradeTemp = new Trade(orderToBuy.getOrderID(), orderToSell.getOrderID(), orderToBuy.getStockSymbol(),
//					howManyBuyerNeeds, totalAmount);
//
//			tradeList.add(tradeTemp);
//
//		}
//
//	}
//
//	

	public void executeTrade(OrderMang buyOrder, OrderMang sellOrder) {

		// Determine how many shares can be traded
		int tradeQuantity = Math.min(buyOrder.getOrderQuantity(), sellOrder.getOrderQuantity());
		// A buy order can be executed if there's a sell order at or below the buy price
		// A sell order can be executed if there's a buy order at or above the sell
		// price
		double executionPrice = sellOrder.getPricePerShare(); // Use seller's price as it's less
		double totalAmount = executionPrice * tradeQuantity;

		// Get users
		Portofolio buyer = getUserById(buyOrder.getUserID());
		Portofolio seller = getUserById(sellOrder.getUserID());

		Stock tempStock = new Stock(sellOrder.getStockSymbol(), sellOrder.getPricePerShare());

		// Update portfolios
		buyer.setBalance(buyer.getBalance() - totalAmount);
		// we need to check first if it exisits between the other stocks
		buyer.addOtherStocks(tempStock, tradeQuantity);

		// update the stock you own
		seller.setQuntitiyOwned(seller.getQuntitiyOwned() - tradeQuantity);
		seller.setBalance(seller.getBalance() + totalAmount);

		// Update orders

		if (buyOrder.getOrderQuantity() == tradeQuantity) {
			buyOrder.setOrderStatus("EXECUTED");
		} else {
			buyOrder.setOrderQuantity(buyOrder.getOrderQuantity() - tradeQuantity);
			// Order remains PENDING for the remaining quantity
		}

		if (sellOrder.getOrderQuantity() == tradeQuantity) {
			sellOrder.setOrderStatus("EXECUTED");
		} else {
			sellOrder.setOrderQuantity(sellOrder.getOrderQuantity() - tradeQuantity);
			// Order remains PENDING for the remaining quantity
		}

		// Create trade record
		Trade trade = new Trade(buyOrder.getOrderID(), sellOrder.getOrderID(), buyOrder.getStockSymbol(), tradeQuantity,
				executionPrice);
		tradeList.add(trade);

	}

	public void orderMatcher(OrderMang newOrderTemp, OrderMang orderInList) {

//		double priceProposed = newOrderTemp.getPricePerShare() * newOrderTemp.getOrderQuantity();

		// we want to know which order will be to buy and which is to sell

		OrderMang orderToSell = null;

		OrderMang orderToBuy = null;

		if (newOrderTemp.getOrderType().equals("BUY")) {
			orderToBuy = newOrderTemp;

			orderToSell = orderInList;
		}

		else {

			orderToBuy = orderInList;

			orderToSell = newOrderTemp;

		}
		/// order buying may cause an issue , as if we are buying lets say 10000 shares
		/// but we have more than one share for selling , each on different prices ,
		/// each is 250 shares , we need to ensure that the
		/// algorithm will go over each selling share if the buying and selling
		/// conditions meet

		/// we should go on a loop until buyer has exhusted all of his buying orders
		///
		if (orderToBuy.getPricePerShare() >= orderToSell.getPricePerShare()) {
			executeTrade(orderToBuy, orderToSell);

		}

	}

	public boolean areAMatch(OrderMang newOrder, OrderMang orderInList) {
		// Basic checks
		if (newOrder == orderInList || !newOrder.getOrderStatus().equals("PENDING")
				|| !orderInList.getOrderStatus().equals("PENDING")
				|| newOrder.getUserID().equals(orderInList.getUserID())
				|| !newOrder.getStockSymbol().equals(orderInList.getStockSymbol())
				|| newOrder.getOrderType().equals(orderInList.getOrderType()))
			return true;

		return false;
	}

	// required API Endpoints

	//// //// //// //// //// //// //// //// //// ////

	//// Order End Point
	// Create a new order (buy or sell)

	// Response :: Created order with ID and status

	// the order type will be sent via the request params

	// Create a request response

	@PostMapping("/api/orders")
	public OrderResponse createNewOrder(@RequestParam String userID, @RequestParam String orderType,
			@RequestParam String stockSymbol, @RequestParam double pricePerShare, @RequestParam int orderQuantity) {

		// Validation

		if (orderQuantity <= 0 || pricePerShare <= 0) {
			System.err.println("Invalid quantity or price");
			return null;
		}

		double priceProposed = pricePerShare * orderQuantity;

		OrderMang newOrder = null;

		// (String userId, String stockSymbol, String orderType, int quantity, double
		// price)
		OrderResponse response = new OrderResponse(userID, stockSymbol, orderType, orderQuantity, priceProposed);

		// 1. we want to make sure that the user doesn't own the stocks he wants to buy

		// 2. in case of selling , we want to make sure that the user still has enough
		// to sell

//		return OrderResponse;

		// checking logical errors so we are expecting to return nothing

		Portofolio targetedUser = getUserById(userID);

		if (targetedUser == null) {
			System.err.println("user Was Not Found");
			return null;
		}

		if (orderType.toUpperCase().equals("BUY")) {
			if (targetedUser.getUserID().equals(userID)) {
				if (targetedUser.getStockOwned().getStockSymbol().equals(stockSymbol)) {
					System.err.println("You can't buy a stock you own");

					return null;
				} else if (priceProposed > targetedUser.getBalance()) {
					System.err.println("Insufficient funds");

					return null;
				} else {
					newOrder = new OrderMang(userID, stockSymbol, pricePerShare, orderType, orderQuantity);

					orderList.add(newOrder);

				}

			}

		} else if (orderType.toUpperCase().equals("SELL")) {
			if (targetedUser.getUserID().equals(userID)
					&& targetedUser.getStockOwned().getStockSymbol().equals(stockSymbol)) {
				if (targetedUser.getQuntitiyOwned() >= orderQuantity) {
					// it's adviced to not detuct from the quntity unless a transcation happens
					// targetedUser.setQuntitiyOwned(targetedUser.getQuntitiyOwned() -
					// orderQuantity);

					newOrder = new OrderMang(userID, stockSymbol, pricePerShare, orderType, orderQuantity);

					orderList.add(newOrder);

				}
			} else {
				if (!targetedUser.getStockOwned().getStockSymbol().equals(stockSymbol))
					System.err.println("You can't sell  A Stock you don't own ");
				if (targetedUser.getQuntitiyOwned() < orderQuantity)
					System.err.println("You can't sell this order Quantity is more than what you currently have ");
				return null;

			}
		}

		// 3. after order has finished we want to match buying or selling

		// the order type symbole quantitiy etc are all coming from the params

		// so we need to match via the symbole

		if (orderList.size() > 1)

		{

			for (int i = 0; i < orderList.size(); i++) {

				// must be the same symboles

				OrderMang orderInList = orderList.get(i);

				boolean canTheyMatch = areAMatch(newOrder, orderInList);

				if (canTheyMatch) {
					// if the same user OR
					// the same ordertype sell sell , or buy buy OR
					// the the stock symboles are not the same
					// if one of them is executed
					// then go to the next Item
					continue;
				}

				// Execute the trade
				orderMatcher(newOrder, orderInList);

				// If new order is fully executed, stop looking for matches
				if (newOrder.getOrderStatus().equals("EXECUTED")) {
					break;
				}

			}
		}

		// 4. if executed we need to add it to the executed list

		return response;

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
	public ArrayList<OrderMang> getAllOrdersForAUser(@PathVariable String userId) {

		ArrayList<OrderMang> orderListTemp = new ArrayList<OrderMang>();

		for (OrderMang order : orderList) {

			if (order.getUserID().equals(userId))
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
	public ArrayList<Trade> getAllExecutedTrades() {
		return tradeList;
	}

	// Get all trades for a specific user
	@GetMapping("/api/trades/user/{userId}")
	public ArrayList<OrderMang> getAllExecutedTradesForASpecificUser(@PathVariable String userId) {
		ArrayList<OrderMang> orderListTemp = new ArrayList<OrderMang>();

		for (OrderMang order : orderList) {

			if (order.getUserID().equals(userId)) {
				if (order.getOrderStatus().equals("EXECUTED")) {
					orderListTemp.add(order);
				}
			}
		}

		return orderListTemp;
	}

	// Get all trades for a specific stock
	@GetMapping("/api/trades/stock/{symbol}")
	public ArrayList<OrderMang> getAllTradesForASpecificStock(@PathVariable String symbol) {

		ArrayList<OrderMang> orderListTemp = new ArrayList<OrderMang>();

		for (OrderMang order : orderList) {

			if (order.getStockSymbol().equals(symbol)) {
				if (order.getOrderStatus().equals("EXECUTED")) {
					orderListTemp.add(order);
				}
			}
		}

		return orderListTemp;
	}

	//// //// //// //// //// //// //// //// //// ////

	//// Portfolio End points

	// Get user's complete portfolio (holdings and cash balance)
	@GetMapping("/api/portfolio/{userId}")
	public Portofolio getUserCompletePortofolio(@PathVariable String userId) {

		return getUserById(userId);

	}

	// Get user's position for a specific stock
	@GetMapping("/api/portfolio/{userId}/stock/{symbol}")
	public Map<Stock, Integer> getUserCompletePortofolio(@PathVariable String userId, @PathVariable String symbol) {

		Map<Stock, Integer> tempMap = new HashMap<Stock, Integer>();

		Portofolio targetUser = getUserById(userId);

		if (targetUser.getStockOwned().getStockSymbol().equals(symbol)) {
			tempMap.put(targetUser.getStockOwned(), targetUser.getQuntitiyOwned());
			return tempMap;
		} else {
			return targetUser.getStockFromOtherOwnedStocks(symbol);
		}

	}

	//// //// //// //// //// //// //// //// //// ////

	//// Market Data End points

	// Get current market data for a stock (best bid, best ask, last trade price)
	@GetMapping("/api/market/{symbol}")
	public void getCurrentMarketDataForAStock(@PathVariable String symbol) {
	}

}
