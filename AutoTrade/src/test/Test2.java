package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

public class Test2 
{
   public static interface User32 extends StdCallLibrary
   {
        final User32 instance = (User32) Native.loadLibrary ("user32", User32.class);
        HWND FindWindowExA(HWND hwndParent, HWND childAfter, String className, String windowName);
        HWND FindWindowA(String className, String windowName);
   }

   public static void main(String[] args) throws Exception
   {
	   	StringBuilder sb = new StringBuilder();
		FileInputStream fis = new FileInputStream("E:\\config.txt");
		InputStreamReader reader = new InputStreamReader(fis,"UTF-8"); //最后的"GBK"根据文件属性而定，如果不行，改成"UTF-8"试试
        BufferedReader br = new BufferedReader(reader);
        String line;
        
        while ((line = br.readLine()) != null) 
            sb.append(line);
        
        br.close();
        reader.close();

        String s = sb.toString();
        List<Integer> indexList = new ArrayList<Integer>();
        List<String> stringList = new ArrayList<String>();
        
        for(int i = 0; i < s.length(); i++) {
        	if(s.charAt(i) == '"') {
        		indexList.add(i);
        	}
        }
        
        
        for(int i = 0; i < indexList.size() - 1; i++) {
        	String temp = s.substring(indexList.get(i) + 1, indexList.get(i + 1));
        	if(!temp.equals(",")) {
        		stringList.add(temp);
            	System.out.println(s.substring(indexList.get(i) + 1, indexList.get(i + 1)));
        	}
        	
        }
        
   }
}