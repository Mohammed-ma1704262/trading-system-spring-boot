package tradingSys;

public class Stock {

	private String stockSymbol;
	private int pricePerShare;

	public Stock() {
	}

	public Stock(String stockSymbol, int pricePerShare) {
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

	public int getPricePerShare() {
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
