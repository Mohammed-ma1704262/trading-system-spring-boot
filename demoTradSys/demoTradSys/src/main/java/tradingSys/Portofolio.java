package tradingSys;

public class Portofolio {
	private int userID;
	private double balance;
	private String stockSymbole;
	private int quntitiyOwned;
	private double avgPurshasedPrice;
	private double currentValue;

	public Portofolio(int userID, String stockSymbole, int quntitiyOwned, double avgPurshasedPrice) {
		super();
		this.balance = 1000000.0;
		this.userID = userID;
		this.stockSymbole = stockSymbole;
		this.quntitiyOwned = quntitiyOwned;
		this.avgPurshasedPrice = avgPurshasedPrice;
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

	public int getUserID() {
		return userID;
	}

	public String getStockSymbole() {
		return stockSymbole;
	}

	public double getAvgPurshasedPrice() {
		return avgPurshasedPrice;
	}

	public double getCurrentValue() {
		return currentValue;
	}

	@Override
	public String toString() {
		return "Portofolio [userID=" + userID + ", balance=" + balance + ", stockSymbole=" + stockSymbole
				+ ", quntitiyOwned=" + quntitiyOwned + ", avgPurshasedPrice=" + avgPurshasedPrice + ", currentValue="
				+ currentValue + "]";
	}

}
