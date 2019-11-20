package debug;

import java.awt.AWTException;
import java.awt.Label;
import java.awt.Robot;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextArea;
import com.sun.glass.events.KeyEvent;

public class Go implements Runnable{

	JTextArea textArea1;
	JTextArea textArea2;
	Label[] label;
	static List<Integer> list = new ArrayList<Integer>();

	Go(){
		
	}
	
	Go(JTextArea textArea1,JTextArea textArea2,Label[] label){
		
		this.textArea1 = textArea1;
		this.textArea2 = textArea2;
		this.label = label;
	}
	
	public void getConfig() {
		try {
			
			StringBuilder sb = new StringBuilder();
			FileInputStream fis = new FileInputStream("config.txt");
			int i = -1;
			
			while((i = fis.read()) != -1) sb.append((char)i);
			
			fis.close();

			Matcher m = Pattern.compile("=\\d+").matcher(sb.toString());
			
			while(m.find()) list.add(Integer.parseInt(m.group().substring(1)));
			textArea2.append("  读取config.txt文件成功\r\n");
			sb = null;
		} catch (Exception e) {
			textArea2.append("  读取config.txt文件异常\r\n");
			e.printStackTrace();
		}
	}
	public static boolean soldWay1(GetStockData stock) {
		if(stock.time < 34200)
			return false;
		
		if(stock.todayHighestPrice < stock.lastdayClosePrice) {
			if(stock.nowPrice < stock.lastdayClosePrice * 0.95) {
				if(stock.time < 35100) {
					if(stock.nowPrice < stock.lastdayClosePrice * 0.93) {
						return true;
					}
				}
				else
					return true;
			}
		}
		else {
			if(stock.nowPrice < stock.todayHighestPrice * 0.95) {
				return true;
			}
		}
		return false;
		
	}
	
	public static boolean soldWay2(GetStockData stock) {
		if(stock.time < 33930)
			return false;
		
		if(stock.nowPrice >= stock.limitUpPrice)
			return true;
		else
			return false;
			
	}
	
	public static boolean soldWay3(GetStockData stock) {
		
		return false;
	}
	
	public static boolean soldWay4(GetStockData stock) {
		
		return false;
	}
	public static boolean soldWay5(GetStockData stock) {
		
		return false;
	}
	
	
	public static boolean buyWay1(GetStockData stock) {
		if(stock.time <= 33900) return false;     //  从9:25开始

		if(stock.todayHighestPrice / stock.lastdayClosePrice >= 1.09)
			return true;
		return false;
	}
	
	public static boolean buyWay2(GetStockData stock) {
//		System.out.println("stock.time " + stock.time);
//		return true;
		
		if(stock.time <= 34200 && stock.time >= 33930 ) {        //  从9:25:30 - 9:30:00
			if(stock.changeRate >= 2)
				return true;
		}
		return false;
		
		
	}
	
	public static boolean buyWay3(GetStockData stock) {
		
		return false;
	}
	
	public static boolean buyWay4(GetStockData stock) {
		
		return false;
	}
	public static boolean buyWay5(GetStockData stock) {
		
		return false;
	}
	
	public static void buy(String code,int stockNum) {
		try {
			Robot robot = new Robot();
			shiftProcess("网上股票交易系统5.0");

			robot.delay(list.get(1));
			
			
			robot.keyPress(KeyEvent.VK_F2);          //切换到卖出界面 , 缓冲一下
			robot.keyRelease(KeyEvent.VK_F2);
			robot.delay(list.get(2));
			
			robot.keyPress(KeyEvent.VK_F1);          //切换到买入界面
			robot.keyRelease(KeyEvent.VK_F1);
			robot.delay(list.get(3));
			
			inputStock(code);               // 输入股票代码
			robot.delay(list.get(5));
			
			robot.keyPress(KeyEvent.VK_TAB);    // 切换到买入价格
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(6));
			
			robot.keyPress(KeyEvent.VK_BACKSPACE);        // 删掉预设价格
			robot.keyRelease(KeyEvent.VK_BACKSPACE);  
			robot.keyPress(KeyEvent.VK_BACKSPACE); 
			robot.keyRelease(KeyEvent.VK_BACKSPACE);   
			robot.keyPress(KeyEvent.VK_BACKSPACE); 
			robot.keyRelease(KeyEvent.VK_BACKSPACE);   
			robot.keyPress(KeyEvent.VK_BACKSPACE); 
			robot.keyRelease(KeyEvent.VK_BACKSPACE);   
			robot.keyPress(KeyEvent.VK_BACKSPACE); 
			robot.keyRelease(KeyEvent.VK_BACKSPACE);   
			robot.delay(list.get(7));
			
			
			GetStockData a = new GetStockData(code);           // 自动输入涨停价
			System.out.println(Float.toString(a.limitUpPrice));
			inputStock(Float.toString(a.limitUpPrice));
			robot.delay(list.get(8));
			
			robot.keyPress(KeyEvent.VK_TAB);    // 切换到买入数量
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(9));

			inputStock(Integer.toString(stockNum));            //  获取买入数量
			robot.delay(list.get(10));
			
			robot.keyPress(KeyEvent.VK_TAB);    
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(11));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(12));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(13));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(14));
			
			shiftProcess("");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void sold(String code,int stockNum){
		try {
			Robot robot = new Robot();
			shiftProcess("网上股票交易系统5.0");

			robot.delay(list.get(1));
			
			
			robot.keyPress(KeyEvent.VK_F1);          //切换到买入界面 , 缓冲一下
			robot.keyRelease(KeyEvent.VK_F1);
			robot.delay(list.get(2));
			
			robot.keyPress(KeyEvent.VK_F2);          //切换到卖出界面
			robot.keyRelease(KeyEvent.VK_F2);
			robot.delay(list.get(3));
			
			inputStock(code);               // 输入股票代码
			robot.delay(list.get(5));
			
			robot.keyPress(KeyEvent.VK_TAB);    // 切换到卖出价格
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(6));
			
			robot.keyPress(KeyEvent.VK_BACKSPACE);        // 删掉预设价格
			robot.keyRelease(KeyEvent.VK_BACKSPACE);  
			robot.keyPress(KeyEvent.VK_BACKSPACE); 
			robot.keyRelease(KeyEvent.VK_BACKSPACE);   
			robot.keyPress(KeyEvent.VK_BACKSPACE); 
			robot.keyRelease(KeyEvent.VK_BACKSPACE);   
			robot.keyPress(KeyEvent.VK_BACKSPACE); 
			robot.keyRelease(KeyEvent.VK_BACKSPACE);   
			robot.keyPress(KeyEvent.VK_BACKSPACE); 
			robot.keyRelease(KeyEvent.VK_BACKSPACE);   
			robot.delay(list.get(7));
			
			
			GetStockData a = new GetStockData(code);           // 自动输入跌停价
			
			String price = Double.toString(round((float)(a.lastdayClosePrice * 0.9)));
			price = price.substring(0,price.indexOf('.') + 3);
			
			inputStock(price);
			robot.delay(list.get(8));
			
			robot.keyPress(KeyEvent.VK_TAB);    // 切换到卖出数量
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(9));

			inputStock(Integer.toString(stockNum));            //  输入卖出数量
			robot.delay(list.get(10));
			
			robot.keyPress(KeyEvent.VK_TAB);    
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(11));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(12));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(13));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(14));
			shiftProcess("");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void shiftProcess(String title){
	/*	
		HWND hwnd = User32.INSTANCE.FindWindow(null, title);
		User32.INSTANCE.ShowWindow(hwnd, 9);
		User32.INSTANCE.SetForegroundWindow(hwnd);
	*/	
	
		
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(list.get(0));
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot = null;
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void inputStock(String code) {
		Robot robot;
		try {
			robot = new Robot();
			for(int i = 0; i < code.length(); i++) {
				robot.keyPress(code.charAt(i));
				robot.keyRelease(code.charAt(i));
				robot.delay(list.get(4));
			}
			robot = null;
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
    public static float round(float num) {
    	float temp = num * 100;
    	if(temp - Math.floor(temp) >= 0.5)
			return (float) ((Math.floor(temp) + 1) / 100);
		return (float) (Math.floor(temp) / 100);
	}

	public void print(JTextArea textArea,String s) {
		textArea.append(s);
		textArea.setCaretPosition(textArea.getText().length());
	}
    
    
	@Override
	public void run() {
		Date date2 = new Date();
		int time = date2.getHours() * 60 + date2.getMinutes();
		StockInfo stockInfo = new StockInfo(textArea2);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		
		getConfig();
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm");//定义时间的格式
		SimpleDateFormat f2 = new SimpleDateFormat("MM-dd-HH:mm");//定义时间的格式
		textArea1.setEditable(false);
		textArea1.setLineWrap(true);
		textArea2.setEditable(false);
		textArea2.setLineWrap(true);
		while(stockInfo.size > 0) {
			Date date = new Date();
			if((date.getHours() * 60 + date.getMinutes() - time) != 0 && (date.getHours() * 60 + date.getMinutes() - time) % list.get(16) == 0) //  间隔一段时间清空信息栏
				textArea1.setText("");
			if(date.getHours() * 60 + date.getMinutes() < 555){
				print(textArea1,"       时间还没到9:15 , 将在9:15之后开始\t" + " --    " + f1.format(date) + "\r\n");
				continue;
			}
			
			for(int i = 0; i < stockInfo.list.size(); i++) {
				
				Item item = stockInfo.list.get(i);
//				System.out.println(item.name + " status " + item.status);
				int month = Integer.parseInt(item.date.substring(0,item.date.indexOf('.')));	
				int day = Integer.parseInt(item.date.substring(item.date.indexOf('.') + 1));
				
				if(!item.status && month == date.getMonth() + 1 && day == date.getDate()) {
					robot.delay(list.get(15));
					GetStockData stock = new GetStockData(item.name);
					print(textArea1,"    正在查询 : " + stock.stockName + " (" + item.name + ")");
					print(textArea1, "\t -- " + f1.format(date) + "\r\n");

					if(item.method.equals("s1")) {
						if(!soldWay1(stock))
							continue;
						sold(item.name,item.num);
						print(textArea2,"  卖出 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("s2")) {
						if(!soldWay2(stock))
							continue;
						sold(item.name,item.num);
						print(textArea2,"  卖出 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("s3")) {
						if(!soldWay3(stock))
							continue;
						sold(item.name,item.num);
						print(textArea2,"  卖出 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("s4")) {
						if(!soldWay4(stock))
							continue;
						sold(item.name,item.num);
						print(textArea2,"  卖出 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("s5")) {
						if(!soldWay5(stock))
							continue;
						sold(item.name,item.num);
						print(textArea2,"  卖出 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("b1")) {
						if(!buyWay1(stock))
							continue;
						buy(item.name,item.num);
						print(textArea2,"  买入 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("b2")) {
						if(!buyWay2(stock))
							continue;
						buy(item.name,item.num);
						print(textArea2,"  买入 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("b3")) {
						if(!buyWay3(stock))
							continue;
						buy(item.name,item.num);
						print(textArea2,"  买入 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("b4")) {
						if(!buyWay4(stock))
							continue;
						buy(item.name,item.num);
						print(textArea2,"  买入 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
					else if(item.method.equals("b5")) {
						if(!buyWay5(stock))
							continue;
						buy(item.name,item.num);
						print(textArea2,"  买入 : " + stock.stockName + " (" + f2.format(date) + ")\r\n");
						label[i * 5 + 3].setText("已成交");
						item.status = true;
						stockInfo.size--;
					}
				}
			}
		}
		print(textArea2,"  程序结束" + "\r\n");
	}
}
