package autoTrade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class StockData {
	
	static String stackCode;   //  股票代码
	static String stockName;   //  股票名称
	static String data;           //  获得的接口数据
	static String date;          //  日期

	static int time;             //  时间
	
	static float todayOpenPrice;  //今日开盘价
	static float lastdayClosePrice;   //昨日收盘价
	static float nowPrice;          //  现价
	static float todayHighestPrice;  //今日最高价
	static float todayLowestPrice;  //今日最低价
	static float limitUpPrice;         // 涨停价
	
	static float dealSumPrice;   //今日总成交额
	static float dealSum;          //  今日成交股票数量
	
	static float buyOneAmount;       //  买一数量
	static float buyTwoAmount;    //  买二数量
	static float buyThreeAmount;  //买三数量
	static float buyFourAmount;  // 买四数量
	static float buyFiveAmount;  //买五数量
	
	static float buyOnePrice;  //买一价
	static float buyTwoPrice;  //买二价
	static float buyThreePrice;  //买三价
	static float buyFourPrice;  //买四价
	static float buyFivePrice;  //买五价
	
	static float soldOneAmount;   //卖一数量
	static float soldTwoAmount;  //卖二数量
	static float soldThreeAmount;  //卖三数量
	static float soldFourAmount;  //卖四数量
	static float soldFiveAmount;  //卖五数量
	
	static float soldOnePrice;  //卖一价
	static float soldTwoPrice;  //卖二价
	static float soldThreePrice;  //卖三价
	static float soldFourPrice;  //卖四价
	static float soldFivePrice;  //卖五价
	
	static float changeRate;  //涨幅

	public static String judgeKind(String stockCode) {
		if(stockCode.charAt(0) == '6')
			return "sh" + stockCode;
		return "sz" + stockCode;
	}
	
	
	public static void anylysis(String stockCode) {
		StockData.stackCode = stockCode;
		try {
			URL url = new URL("http://hq.sinajs.cn/list=" + judgeKind(stockCode));
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
			data = br.readLine();   //  获取接口数据的字符串           
			
			String[] datas = data.split(",");
			
			stockName = datas[0].substring(datas[0].indexOf('"') + 1);	
			todayOpenPrice = Float.parseFloat(datas[1]);	
			lastdayClosePrice = Float.parseFloat(datas[2]);		
			nowPrice = Float.parseFloat(datas[3]);	
			todayHighestPrice = Float.parseFloat(datas[4]);
			todayLowestPrice = Float.parseFloat(datas[5]);
			buyOnePrice = Float.parseFloat(datas[6]);
			soldOnePrice = Float.parseFloat(datas[7]);
			dealSum = Float.parseFloat(datas[8]);
			dealSumPrice = Float.parseFloat(datas[9]);
			buyOneAmount = Float.parseFloat(datas[10]);
			buyOnePrice = Float.parseFloat(datas[11]);
			buyTwoAmount = Float.parseFloat(datas[12]);
			buyTwoPrice = Float.parseFloat(datas[13]);
			buyThreeAmount = Float.parseFloat(datas[14]);
			buyThreePrice = Float.parseFloat(datas[15]);
			buyFourAmount = Float.parseFloat(datas[16]);
			buyFourPrice = Float.parseFloat(datas[17]);
			buyFiveAmount = Float.parseFloat(datas[18]);
			buyFivePrice = Float.parseFloat(datas[19]);
			soldOneAmount = Float.parseFloat(datas[20]);
			soldOnePrice = Float.parseFloat(datas[21]);
			soldTwoAmount = Float.parseFloat(datas[22]);
			soldTwoPrice = Float.parseFloat(datas[23]);
			soldThreeAmount = Float.parseFloat(datas[24]);
			soldThreePrice = Float.parseFloat(datas[25]);
			soldFourAmount = Float.parseFloat(datas[26]);
			soldFourPrice = Float.parseFloat(datas[27]);
			soldFiveAmount = Float.parseFloat(datas[28]);
			soldFivePrice = Float.parseFloat(datas[29]);
			date = datas[30];
			
			Date date = new Date();
			time = date.getHours() * 3600 + date.getMinutes() * 60 + date.getSeconds();
			changeRate = Go.round((nowPrice / lastdayClosePrice - 1) * 100);
			limitUpPrice = Go.round((float) (lastdayClosePrice * 1.1));
			
			br.close();
			url = null;
			br = null;
			datas = null;
			date = null;
		} catch (Exception e) {
			System.out.println("解析股票信息失败 : " + stockCode);
			e.printStackTrace();
		}
	}
}
