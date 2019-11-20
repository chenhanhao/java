package debug;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GetStockData {
	
	String stackCode;   //  ��Ʊ����
	String stockName;   //  ��Ʊ����
	String data;           //  ��õĽӿ�����
	String date;          //  ����

	int time;             //  ʱ��
	
	float todayOpenPrice;  //���տ��̼�
	float lastdayClosePrice;   //�������̼�
	float nowPrice;          //  �ּ�
	float todayHighestPrice;  //������߼�
	float todayLowestPrice;  //������ͼ�
	float limitUpPrice;         // ��ͣ��
	
	float dealSumPrice;   //�����ܳɽ���
	float dealSum;          //  ���ճɽ���Ʊ����
	
	float buyOneAmount;       //  ��һ����
	float buyTwoAmount;    //  �������
	float buyThreeAmount;  //��������
	float buyFourAmount;  // ��������
	float buyFiveAmount;  //��������
	
	float buyOnePrice;  //��һ��
	float buyTwoPrice;  //�����
	float buyThreePrice;  //������
	float buyFourPrice;  //���ļ�
	float buyFivePrice;  //�����
	
	float soldOneAmount;   //��һ����
	float soldTwoAmount;  //��������
	float soldThreeAmount;  //��������
	float soldFourAmount;  //��������
	float soldFiveAmount;  //��������
	
	float soldOnePrice;  //��һ��
	float soldTwoPrice;  //������
	float soldThreePrice;  //������
	float soldFourPrice;  //���ļ�
	float soldFivePrice;  //�����
	
	float changeRate;  //�Ƿ�
	
	public GetStockData(String stockCode) {
		data = null;
		this.stackCode = stockCode;
		try {
			URL url = new URL("http://hq.sinajs.cn/list=" + judgeKind(stockCode));
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gbk"));
			data = br.readLine();   //  ��ȡ�ӿ����ݵ��ַ���           
			anylysis();      //  ��������
			
			url = null;
			br = null;
		} catch (Exception e) {
			System.out.println("������Ʊ��Ϣʧ�� : " + stockCode);
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
			System.out.println("��ȡ����ʧ��" + stackCode);
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
