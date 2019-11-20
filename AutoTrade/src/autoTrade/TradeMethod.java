package autoTrade;

public class TradeMethod {
	public static boolean soldWay1() {
		if(StockData.time < 34200)
			return false;
		
		if(StockData.todayHighestPrice < StockData.lastdayClosePrice) {
			if(StockData.nowPrice < StockData.lastdayClosePrice * 0.95) {
				if(StockData.time < 35100) {
					if(StockData.nowPrice < StockData.lastdayClosePrice * 0.93) {
						return true;
					}
				}
				else
					return true;
			}
		}
		else {
			if(StockData.nowPrice < StockData.todayHighestPrice * 0.95) {
				return true;
			}
		}
		return false;
		
	}
	
	public static boolean soldWay2() {
		if(StockData.time < 33930)
			return false;
		
		if(StockData.nowPrice >= StockData.limitUpPrice)
			return true;
		else
			return false;	
	}
	
	public static boolean soldWay3() {
		float maxChange = (StockData.todayHighestPrice / StockData.lastdayClosePrice - 1) * 100;
		float minChange = (StockData.todayLowestPrice / StockData.lastdayClosePrice - 1) * 100;
		
		if(StockData.changeRate < -2.8) {
			if(minChange < -3.2) {
				if(StockData.changeRate < -3.2) {
					if(StockData.time > 37800)
						return true;
				}
			}
			else {
				if(StockData.changeRate < -2.8) {
					if(StockData.time > 37800)
						return true;
				}
			}
		}
		
		if(maxChange > 9.5)
			return true;
		else {
			if(maxChange > 5) {
				if(maxChange - StockData.changeRate > 1)
					return true;	
			}
			else {
				if(maxChange > 2) {
					if(maxChange * 0.5 > StockData.changeRate)
						return true;
				}
			}
		}
		
		if(StockData.time > 53580)
			return true;
		
		return false;
	}
	
	public static boolean soldWay4() {
		
		return false;
	}
	public static boolean soldWay5() {
		
		return false;
	}
	
	
	public static boolean buyWay1() {
		if(StockData.time <= 33900) return false;     //  从9:25开始

		if(StockData.todayHighestPrice / StockData.lastdayClosePrice >= 1.09)
			return true;
		return false;
	}
	
	public static boolean buyWay2() {
//		System.out.println("stock.time " + stock.time);
//		return true;
		
		if(StockData.time <= 34200 && StockData.time >= 33930 ) {        //  从9:25:30 - 9:30:00
			if(StockData.changeRate >= 2)
				return true;
		}
		return false;
		
		
	}
	
	public static boolean buyWay3() {
		
		return false;
	}
	
	public static boolean buyWay4() {
		
		return false;
	}
	public static boolean buyWay5() {
		
		return false;
	}
	
}
