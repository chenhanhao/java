package debug;

import java.awt.AWTException;
import java.awt.Label;
import java.awt.Robot;
import java.util.List;

public class Run implements Runnable{
	StockInfo stockInfo = new StockInfo();
	Label[] labels;
	
	public Run(Label[] labels) {
		this.labels = labels;
	}
	@Override
	public void run() {

			List<Item> list = stockInfo.list;
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					while(stockInfo.size > 0) {
						for(int i = 0; i < stockInfo.size; i++) {
							
							robot.delay(3000);
							Item item = list.get(i);
							GetStockData stock = new GetStockData(item.name);
							labels[i * 8 + 0].setText(stock.stockName);
							labels[i * 8 + 1].setText(Float.toString(stock.nowPrice));
							labels[i * 8 + 2].setText(Float.toString(stock.changeRate));
							labels[i * 8 + 3].setText(Float.toString(stock.time));
							labels[i * 8 + 4].setText(Float.toString(stock.limitUpPrice));
							
							String price = Float.toString(Go.round((float) (stock.lastdayClosePrice * 0.9)));
							price = price.substring(0,price.indexOf('.') + 3);
							
							labels[i * 8 + 5].setText(price);
							labels[i * 8 + 6].setText(Float.toString(stock.lastdayClosePrice));
							labels[i * 8 + 7].setText(Float.toString(stock.todayOpenPrice));
						}
					}
					
		}
		
		
		
	}
