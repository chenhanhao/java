package test;

import java.io.FileInputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
	public static void main(String[] args) {
		boolean flag = true;
		Date date = new Date();
		
		System.out.println(date.getHours() );
		System.out.println(date.getMinutes());
		
		while(flag) {
			Date date2 = new Date();
			System.out.println(date2.getSeconds());
		}
		System.out.println(date.getSeconds());
		
//		System.out.println(date.getDate());
		System.out.println(date.getMonth());
		
		String s = "fhj=1500 fhdshjf=4855";

		Matcher m2 = Pattern.compile("\\d{2,}").matcher(s);
		
//		while(m2.find()) {
//			System.out.println(m2.group());
//		}
		
		
		
		
	try {
				

		StringBuilder sb = new StringBuilder();
		FileInputStream fis = new FileInputStream("E:\\config.txt");
		int i = -1;
		
		while((i = fis.read()) != -1)
			sb.append((char)i);
		
		fis.close();

		Matcher m = Pattern.compile("=\\d+").matcher(sb.toString());
//		System.out.println(m.);
		while(m.find()) {
//			System.out.println(Integer.parseInt(m.group().substring(1)));
		}
				
		
		
				
	//			for(int num : list)
	//				System.out.println(num);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
	String filePath = "E:\\stock.txt";
	try {
		StringBuilder sb = new StringBuilder();
		FileInputStream fis = new FileInputStream(filePath);
		int i = -1;
		while((i = fis.read()) != -1) {
			char ch = (char)i;
			sb.append(ch);
			
		}
	//	size = name.size();
		String[] strs = sb.toString().split("\\s+");
		System.out.println(strs.length);
		for(String s2 : strs)
			System.out.println(s2);
		fis.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
}
