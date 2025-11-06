# Trading System Spring Boot
The Group Securities Recruitment Assignment

# Trading System API

A RESTful API for a simple stock trading system built with Spring Boot. This system allows users to place buy/sell orders, and execute trades automatically.

## Features

- **Order Management**: Place, view, and cancel buy/sell orders
- **Automatic Trade Execution**: Matches buy and sell orders based on price conditions
- **Portfolio Management**: Track user holdings, cash balance, and stock positions
- **Market Data**: Get real-time market information (best bid, best ask, last trade price)
- **Trade History**: View executed trades for users and specific stocks



## API Endpoints

### Order Management
- `POST /api/orders` - Create a new buy/sell order
- `GET /api/orders/{orderId}` - Get order details by ID
- `GET /api/orders/user/{userId}` - Get all orders for a specific user
- `DELETE /api/orders/{orderId}` - Cancel a pending order

### Trade Management
- `GET /api/trades` - Get all executed trades
- `GET /api/trades/user/{userId}` - Get all trades for a specific user
- `GET /api/trades/stock/{symbol}` - Get all trades for a specific stock

### Portfolio Management
- `GET /api/portfolio/{userId}` - Get user's complete portfolio
- `GET /api/portfolio/{userId}/stock/{symbol}` - Get user's position for a specific stock

### Market Data
- `GET /api/market/{symbol}` - Get current market data for a stock


## Key Features Implementation

### Order Matching Logic
- Buy orders execute when there's a sell order at or below the buy price
- Sell orders execute when there's a buy order at or above the sell price
- Automatic partial order fills supported
- Orders start as PENDING and change to EXECUTED when matched and the order is completed 

### Portfolio Management
- Each user starts with QAR 100,000 cash balance
- Tracks average purchase price and current value of holdings
- Prevents users from selling stocks they don't own
- Prevents users from buying with insufficient funds


## Defualt Varaibles

### Stocks
- **QIBK** - Initial price: QAR 1,500
- **QNBK** - Initial price: QAR 1,000  
- **MARK** - Initial price: QAR 2,000

## Users
- **12345** - Initial Stock: QIBK Owns 1000
- **67890** - Initial Stock: QNBK Owns 1000
- **10112** - Initial Stock: MARK Owns 1000


## Sample Test Scenarios

### 1. Basic Trade Flow
- User A buys 10 QIBK shares at QAR 150
- User B sells 10 QIBK shares at QAR 150
- Orders automatically match and execute
- Both portfolios are update accordingly

### 2. Insufficient Funds
- User with QAR 1,000 tries to buy 100 shares at QAR 50 each
- System rejects with "Insufficient funds" error

### 3. Selling Non-owned Stock
- User tries to sell stock they don't own
- System rejects with appropriate error message

##  Assumptions & Limitations

### Assumptions
- Users start with pre-defined portfolios and stocks
- Order IDs and Trade IDs are generated using random numbers
- Market data is calculated from active orders and recent trades


## Business Rules

1. **Order Validation**:
   - Users can only sell stocks they own in sufficient quantity
   - Users cannot buy if they have insufficient funds
   - Orders can only be cancelled if they are PENDING

2. **Trade Execution**:
   - Orders match when buy price ≥ sell price
   - Execution uses seller's price
   - Partial order fills are supported

3. **Portfolio Management**:
   - Average purchase price is calculated as total cost / total quantity
   - Current value uses the latest market price


