package test;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;

public class Test3 {
	
	List<Integer> list = new ArrayList<Integer>();
	
	synchronized public void helper() {
		list.add(1);
	}
	
	
	
	public static void main(String[] args) throws Exception {
		HWND hwnd = User32.INSTANCE.FindWindow(null, "AutoTrade");
		if(hwnd == null)
			System.out.println("null");
		User32.INSTANCE.ShowWindow(hwnd, 9);
		User32.INSTANCE.SetForegroundWindow(hwnd);
		
		Robot robot = new Robot();
//		robot.delay(5000);
	
		
		
	}
}
