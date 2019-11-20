package autoTrade;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

public class StockInfo{

	List<Item> list = new ArrayList<Item>();
	int size = 0;

	public StockInfo(JTextArea textArea) {

		String filePath = "stock.txt";
    	try {
    		Date date = new Date();
    		StringBuilder sb = new StringBuilder();
    		FileInputStream fis = new FileInputStream(filePath);
    		int i = -1;
    		
    		while((i = fis.read()) != -1) sb.append((char)i);
    		
    		String[] strs = sb.toString().split("\\s+");
    		
    		for(int j = 0; j < strs.length / 4; j++) {
    			Item item = new Item();
    			item.name = strs[j * 4];
    			item.method = strs[j * 4 + 1];
    			item.num = Integer.parseInt(strs[j * 4 + 2]);
    			item.date = strs[j * 4 + 3];
    			
    			int month = Integer.parseInt(item.date.substring(0,item.date.indexOf('.')));	
				int day = Integer.parseInt(item.date.substring(item.date.indexOf('.') + 1));
				if(month == date.getMonth() + 1 && day == date.getDate())
					item.status = false;
				else
					item.status = true;
    			list.add(item);
    		}

    		
    		fis.close();
    		sb = null;
    		fis = null;
    	}catch(Exception e) {
    		textArea.append("  读取stock.txt文件异常!!!\r\n");
    		e.printStackTrace();
    	}
    	size = list.size();
	}
}