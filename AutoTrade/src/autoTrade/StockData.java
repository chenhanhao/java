package autoTrade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class StockData {
	
	static String stackCode;   //  ��Ʊ����
	static String stockName;   //  ��Ʊ����
	static String data;           //  ��õĽӿ�����
	static String date;          //  ����

	static int time;             //  ʱ��
	
	static float todayOpenPrice;  //���տ��̼�
	static float lastdayClosePrice;   //�������̼�
	static float nowPrice;          //  �ּ�
	static float todayHighestPrice;  //������߼�
	static float todayLowestPrice;  //������ͼ�
	static float limitUpPrice;         // ��ͣ��
	
	static float dealSumPrice;   //�����ܳɽ���
	static float dealSum;          //  ���ճɽ���Ʊ����
	
	static float buyOneAmount;       //  ��һ����
	static float buyTwoAmount;    //  �������
	static float buyThreeAmount;  //��������
	static float buyFourAmount;  // ��������
	static float buyFiveAmount;  //��������
	
	static float buyOnePrice;  //��һ��
	static float buyTwoPrice;  //�����
	static float buyThreePrice;  //������
	static float buyFourPrice;  //���ļ�
	static float buyFivePrice;  //�����
	
	static float soldOneAmount;   //��һ����
	static float soldTwoAmount;  //��������
	static float soldThreeAmount;  //��������
	static float soldFourAmount;  //��������
	static float soldFiveAmount;  //��������
	
	static float soldOnePrice;  //��һ��
	static float soldTwoPrice;  //������
	static float soldThreePrice;  //������
	static float soldFourPrice;  //���ļ�
	static float soldFivePrice;  //�����
	
	static float changeRate;  //�Ƿ�

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
			data = br.readLine();   //  ��ȡ�ӿ����ݵ��ַ���           
			
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
			System.out.println("������Ʊ��Ϣʧ�� : " + stockCode);
			e.printStackTrace();
		}
	}
}
