package autoTrade;

import java.awt.AWTException;
import java.awt.Label;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import com.sun.glass.events.KeyEvent;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Go implements Runnable{

	int length;
	JTextArea textArea1;
	JTextArea textArea2;
	Label[] label;
	static Robot robot;
	static List<Integer> list = new ArrayList<Integer>();
	static List<String> tradeNameList = new ArrayList<String>();
	
	
	Go(JTextArea textArea1,JTextArea textArea2,Label[] label){
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		this.textArea1 = textArea1;
		this.textArea2 = textArea2;
		this.label = label;
		length = 0;
	}
	
	public void getConfig() {
		try {
			
			StringBuilder sb = new StringBuilder();
			FileInputStream fis = new FileInputStream("config.txt");
			InputStreamReader reader = new InputStreamReader(fis,"UTF-8"); //最后的"GBK"根据文件属性而定，如果不行，改成"UTF-8"试试
	        BufferedReader br = new BufferedReader(reader);
	        String line;
	        
	        while ((line = br.readLine()) != null) 
	            sb.append(line);
	        
	        String s = sb.toString();
	        List<Integer> indexList = new ArrayList<Integer>();
//	        List<String> stringList = new ArrayList<String>();
	        
	        for(int i = 0; i < s.length(); i++)
	        	if(s.charAt(i) == '"')
	        		indexList.add(i);
	        
	        for(int i = 0; i < indexList.size() - 1; i++) {
	        	String temp = s.substring(indexList.get(i) + 1, indexList.get(i + 1));
	        	if(!temp.equals(",")) 
	        		tradeNameList.add(temp);
	        }

			Matcher m = Pattern.compile("=\\d+").matcher(s);
			
			while(m.find()) list.add(Integer.parseInt(m.group().substring(1)));
			textArea2.append("  读取config.txt文件成功!!!\r\n");
			
			sb = null;
			indexList = null;
			fis.close();
			reader.close();
			br.close();

		} catch (Exception e) {
			textArea2.append("  读取config.txt文件异常\r\n");
			e.printStackTrace();
		}
	}

	public static void buy(String code,int stockNum) {

			for(String s : tradeNameList) {
				HWND hwnd = User32.INSTANCE.FindWindow(null, s);
				if(hwnd != null) {
					shiftProcess(s);
					break;
				}
			}
			

			robot.delay(list.get(0));
			
			
			robot.keyPress(KeyEvent.VK_F2);          //切换到卖出界面 , 缓冲一下
			robot.keyRelease(KeyEvent.VK_F2);
			robot.delay(list.get(1));
			
			robot.keyPress(KeyEvent.VK_F1);          //切换到买入界面
			robot.keyRelease(KeyEvent.VK_F1);
			robot.delay(list.get(2));
			
			inputStock(code);               // 输入股票代码
			robot.delay(list.get(4));
			
			robot.keyPress(KeyEvent.VK_TAB);    // 切换到买入价格
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(5));
			
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
			robot.delay(list.get(6));
			
			
//			StockData a = new StockData(code);           // 自动输入涨停价
//			System.out.println(Float.toString(a.limitUpPrice));
			inputStock(Float.toString(StockData.limitUpPrice));
			robot.delay(list.get(7));
			
			robot.keyPress(KeyEvent.VK_TAB);    // 切换到买入数量
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(8));

			inputStock(Integer.toString(stockNum));            //  获取买入数量
			robot.delay(list.get(9));
			
			robot.keyPress(KeyEvent.VK_TAB);    
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(10));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(11));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(12));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(13));
			
			shiftProcess("AutoTrade");
			robot = null;

	}
	
	public static void sold(String code,int stockNum){
		try {
			Robot robot = new Robot();
			
			for(String s : tradeNameList) {
				HWND hwnd = User32.INSTANCE.FindWindow(null, s);
				if(hwnd != null) {
					shiftProcess(s);
					break;
				}
			}
			

			robot.delay(list.get(0));
			
			robot.keyPress(KeyEvent.VK_F1);          //切换到买入界面 , 缓冲一下
			robot.keyRelease(KeyEvent.VK_F1);
			robot.delay(list.get(1));
			
			robot.keyPress(KeyEvent.VK_F2);          //切换到卖出界面
			robot.keyRelease(KeyEvent.VK_F2);
			robot.delay(list.get(2));
			
			inputStock(code);               // 输入股票代码
			robot.delay(list.get(4));
			
			robot.keyPress(KeyEvent.VK_TAB);    // 切换到卖出价格
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(5));
			
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
			robot.delay(list.get(6));
			
			
//			StockData a = new StockData(code);           // 自动输入跌停价
			
//			System.out.println(a.lastdayClosePrice);
	//		System.out.println(a.lastdayClosePrice * 0.9);
	//		System.out.println(Go.round((float) (a.lastdayClosePrice * 0.9)));
			
			String price = Float.toString((float) (StockData.lastdayClosePrice * 0.9 + 0.01));
			price = price.substring(0,price.indexOf('.') + 3);
			
//			System.out.println(price);
			inputStock(price);
			robot.delay(list.get(7));
			
			robot.keyPress(KeyEvent.VK_TAB);    // 切换到卖出数量
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(8));

			inputStock(Integer.toString(stockNum));            //  输入卖出数量
			robot.delay(list.get(9));
			
			robot.keyPress(KeyEvent.VK_TAB);    
			robot.keyRelease(KeyEvent.VK_TAB);   
			robot.delay(list.get(10));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(11));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(12));
			robot.keyPress(KeyEvent.VK_ENTER);    
			robot.keyRelease(KeyEvent.VK_ENTER); 
			robot.delay(list.get(13));
			shiftProcess("AutoTrade");
			
			robot = null;
	//		a = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void shiftProcess(String title){
		
		HWND hwnd = User32.INSTANCE.FindWindow(null, title);
		User32.INSTANCE.ShowWindow(hwnd, 9);
		User32.INSTANCE.SetForegroundWindow(hwnd);
		
	/*
		
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
		
		*/
		
	}
	
	public static void inputStock(String code) {
		Robot robot;
		try {
			robot = new Robot();
			for(int i = 0; i < code.length(); i++) {
				robot.keyPress(code.charAt(i));
				robot.keyRelease(code.charAt(i));
				robot.delay(list.get(3));
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
		if(textArea == textArea1) {
			length += s.length();
			textArea.setCaretPosition(length);
		}
				
		if(length > 1000000) {
			length = 0;
			textArea1.setText("");
		}	
	}
    
    
	@Override
	public void run() {
		StockInfo stockInfo = new StockInfo(textArea2);
		
		getConfig();
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm");//定义时间的格式
		SimpleDateFormat f2 = new SimpleDateFormat("MM-dd-HH:mm");//定义时间的格式
		textArea1.setEditable(false);
		textArea1.setLineWrap(true);
		textArea2.setEditable(false);
		textArea2.setLineWrap(true);
		while(stockInfo.size > 0){
			Date date = new Date();
			if(date.getHours() * 60 + date.getMinutes() < 555){
				print(textArea1,"       时间还没到9:15 , 将在9:15之后开始\t" + " -- " + f1.format(date) + "\r\n");
				robot.delay(list.get(14));
				date = null;
				System.gc();
				continue;
			}
			
			for(int i = 0; i < stockInfo.list.size(); i++) {
				Item item = stockInfo.list.get(i);
				
				robot.delay(list.get(14));
				if(!item.status) {
					StockData.anylysis(item.name);
					print(textArea1,"    正在查询 : " + StockData.stockName + " (" + item.name + ")");
					print(textArea1, "\t -- " + f1.format(date) + "\r\n");

					switch(item.method) {
						case "s1" :
							if(TradeMethod.soldWay1()) {
								sold(item.name,item.num);
								print(textArea2,"  卖出 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "s2" :
							if(TradeMethod.soldWay2()) {
								sold(item.name,item.num);
								print(textArea2,"  卖出 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "s3" :
							if(TradeMethod.soldWay3()) {
								sold(item.name,item.num);
								print(textArea2,"  卖出 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "s4" :
							if(TradeMethod.soldWay4()) {
								sold(item.name,item.num);
								print(textArea2,"  卖出 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "s5" :
							if(TradeMethod.soldWay5()) {
								sold(item.name,item.num);
								print(textArea2,"  卖出 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "b1" :
							if(TradeMethod.buyWay1()) {
								buy(item.name,item.num);
								print(textArea2,"  买入 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "b2" :
							if(TradeMethod.buyWay2()) {
								buy(item.name,item.num);
								print(textArea2,"  买入 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "b3" :
							if(TradeMethod.buyWay3()) {
								buy(item.name,item.num);
								print(textArea2,"  买入 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "b4" :
							if(TradeMethod.buyWay4()) {
								buy(item.name,item.num);
								print(textArea2,"  买入 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						case "b5" :
							if(TradeMethod.buyWay5()) {
								buy(item.name,item.num);
								print(textArea2,"  买入 : " + StockData.stockName + " (" + f2.format(date) + ")\r\n");
								label[i * 5 + 3].setText("已成交");
								item.status = true;
								stockInfo.size--;
							}
							break;
						default:
							break;
					}
				}
			}
			date = null;
			System.gc();
		}
		print(textArea2,"  程序结束" + "\r\n");
		System.out.println(System.currentTimeMillis());
	}
}
