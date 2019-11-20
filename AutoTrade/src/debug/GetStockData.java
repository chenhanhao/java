package debug;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetStockData {
	
	String stackCode;   //  股票代码
	String stockName;   //  股票名称
	String data;           //  获得的接口数据
	String date;          //  日期

	int time;             //  时间
	
	float todayOpenPrice;  //今日开盘价
	float lastdayClosePrice;   //昨日收盘价
	float nowPrice;          //  现价
	float todayHighestPrice;  //今日最高价
	float todayLowestPrice;  //今日最低价
	float limitUpPrice;         // 涨停价
	
	float dealSumPrice;   //今日总成交额
	float dealSum;          //  今日成交股票数量
	
	float buyOneAmount;       //  买一数量
	float buyTwoAmount;    //  买二数量
	float buyThreeAmount;  //买三数量
	float buyFourAmount;  // 买四数量
	float buyFiveAmount;  //买五数量
	
	float buyOnePrice;  //买一价
	float buyTwoPrice;  //买二价
	float buyThreePrice;  //买三价
	float buyFourPrice;  //买四价
	float buyFivePrice;  //买五价
	
	float soldOneAmount;   //卖一数量
	float soldTwoAmount;  //卖二数量
	float soldThreeAmount;  //卖三数量
	float soldFourAmount;  //卖四数量
	float soldFiveAmount;  //卖五数量
	
	float soldOnePrice;  //卖一价
	float soldTwoPrice;  //卖二价
	float soldThreePrice;  //卖三价
	float soldFourPrice;  //卖四价
	float soldFivePrice;  //卖五价
	
	float changeRate;  //涨幅
	
	public GetStockData(String stockCode) {
		data = null;
		this.stackCode = stockCode;
		try {
			URL url = new URL("http://hq.sinajs.cn/list=" + judgeKind(stockCode));
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
			data = br.readLine();   //  获取接口数据的字符串           
			anylysis();      //  解析数据
			
			url = null;
			br = null;
		} catch (Exception e) {
			System.out.println("解析股票信息失败 : " + stockCode);
			e.printStackTrace();
		}
	}
	
	public String judgeKind(String stockCode) {
		if(stockCode.charAt(0) == '6')
			return "sh" + stockCode;
		return "sz" + stockCode;
	}
	
	
	public void anylysis() {
		if(data == null) {
			System.out.println("获取数据失败" + stackCode);
			return;
		}
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
		time = timeToInt(datas[31]);
		changeRate = Go.round((nowPrice / lastdayClosePrice - 1) * 100);
		limitUpPrice = Go.round((float) (lastdayClosePrice * 1.1));
	}
	
	
	public int timeToInt(String s) {
		int hour = Integer.parseInt(s.substring(0,2));
		int minute = Integer.parseInt(s.substring(3,5));
		int second = Integer.parseInt(s.substring(6,8));
		return hour * 3600 + minute * 60 + second;
	}
}
