package debug;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class StockInfo{

	List<Item> list = new ArrayList<Item>();
	int size = 0;
	public StockInfo() {
		String filePath = "stock.txt";
    	try {
    		StringBuilder sb = new StringBuilder();
    		FileInputStream fis = new FileInputStream(filePath);
    		int i = -1;
    		while((i = fis.read()) != -1) sb.append((char)i);
    		String[] strs = sb.toString().split("\\s+");
    		for(int i1 = 0; i1 < strs.length / 4; i1++) {
    			Item item = new Item();
    			item.name = strs[i1 * 4];
    			item.method = strs[i1 * 4 + 1];
    			item.num = Integer.parseInt(strs[i1 * 4 + 2]);
    			item.date = strs[i1 * 4 + 3];
    			item.status = false;
    			list.add(item);
    		}
    		fis.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	size = list.size();
	}
	public StockInfo(JTextArea textArea) {

		String filePath = "stock.txt";
    	try {
    		StringBuilder sb = new StringBuilder();
    		FileInputStream fis = new FileInputStream(filePath);
    		int i = -1;
    		while((i = fis.read()) != -1) sb.append((char)i);
    		String[] strs = sb.toString().split("\\s+");
    		for(int i1 = 0; i1 < strs.length / 4; i1++) {
    			Item item = new Item();
    			item.name = strs[i1 * 4];
    			item.method = strs[i1 * 4 + 1];
    			item.num = Integer.parseInt(strs[i1 * 4 + 2]);
    			item.date = strs[i1 * 4 + 3];
    			item.status = false;
    			list.add(item);
    		}
    		fis.close();
    		
    	}catch(Exception e) {
    		textArea.append("  读取stock.txt文件异常!!!\r\n");
    		e.printStackTrace();
    	}
    	size = list.size();
	}
}