package tradingSys;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

	private int orderID;
	private int userID;

	private String orderType;
	private int orderQuantity;

	private String orderStatus;
	private String timeStamp;

	private String stockSymbol;
	private int pricePerShare;

	public Order(int userID, String stockSymbol, String orderType, int orderQuantity, int pricePerShare,
			String orderStatus) {
		super();
		this.orderID = (int) (Math.random() * 10001);
		this.userID = userID;
		this.stockSymbol = stockSymbol;
		this.orderType = orderType;
		this.orderQuantity = orderQuantity;
		this.pricePerShare = pricePerShare;
		this.orderStatus = orderStatus;

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.timeStamp = myDateObj.format(myFormatObj);
	}

	public Order(int userID, String stockSymbol, String orderType, int orderQuantity, int pricePerShare) {
		super();
		this.orderID = (int) (Math.random() * 10001);
		this.userID = userID;
		this.stockSymbol = stockSymbol;
		this.orderType = orderType;
		this.orderQuantity = orderQuantity;
		this.pricePerShare = pricePerShare;
		this.orderStatus = "PENDING";

		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.timeStamp = myDateObj.format(myFormatObj);
	}

	public int getOrderID() {
		return orderID;
	}

	public int getUserID() {
		return userID;
	}

//	public void setUserID(int userID) {
//		this.userID = userID;
//	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		if (orderType.toUpperCase() == "BUY" || orderType.toUpperCase() == "SELL") {

			this.orderType = orderType.toUpperCase();
		}

		else {
			System.err.println("Error , you can only place BUY or SELL check the spelling");
		}
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		if (orderQuantity >= this.orderQuantity)
			this.orderQuantity = this.orderQuantity - orderQuantity;
		else
			System.err.println("Smth went wrong...");
	}

	public int getPricePerShare() {
		return pricePerShare;
	}

	public void setPricePerShare(int pricePerShare) {
		this.pricePerShare = pricePerShare;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		if (orderStatus.toUpperCase() == "PENDING" || orderStatus.toUpperCase() == "EXECUTED") {

			this.orderStatus = orderStatus.toUpperCase();
		}
		if (orderStatus.toUpperCase() == "CANCELLED") {
			if (orderStatus.toUpperCase() == "PENDING")
				this.orderStatus = orderStatus.toUpperCase();
			else {
				System.err.println("Error , You can only cancel orders that are of status pending");
			}
		}

		else {
			System.err.println("Error , only place PENDING, EXECUTED, Or CANCELLED  check the spelling");
		}

	}

	public String getTimeStamp() {
//		LocalDateTime myDateObj = LocalDateTime.now();
//		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//		String timeStamp = myDateObj.format(myFormatObj);
		return timeStamp;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", userID=" + userID + ", orderType=" + orderType + ", orderQuantity="
				+ orderQuantity + ", orderStatus=" + orderStatus + ", timeStamp=" + timeStamp + ", stockSymbol="
				+ stockSymbol + ", pricePerShare=" + pricePerShare + "]";
	}

}
