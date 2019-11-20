package test;


import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.MSG;
import com.sun.jna.Native; 
import com.sun.jna.Platform; 
import com.sun.jna.win32.StdCallLibrary; 
/*
 *
 **
Created by lenovo on 2017/4/27.
使用winID来获得窗口的类型和标题，然后发送消息或者其他操作
*
*/
public class TestForHandle {
public static void main(String[] args) {
 
	HWND hwnd = User32.INSTANCE.FindWindow(null,"网上股票交易系统5.0");
//	HWND hwnd2 = User32.INSTANCE.FindWindow(null,"");
	if (hwnd == null) {
	    System.out.println("not running");
	}
	else{
		System.out.println("is running");
		
		
	
//		HWND FindWindowExA(HWND hwndParent, HWND childAfter, String className, String windowName);
  //      HWND FindWindowA(String className, String windowName);

		
//		User32.INSTANCE.;
		
//		System.out.println(new WinDef.DWORD(0));
//		System.out.println(User32.INSTANCE.GetMessage(new MSG(), hwnd, 0,0));
	    User32.INSTANCE.ShowWindow(hwnd, 9 );        // SW_RESTORE
	    User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front

	 //   User32.INSTANCE.senSendMessage(hq, WM_KEYDOWN, 'L', 0);
	    sendChar('1');
	    
	   
	 /*
	    //User32.INSTANCE.GetForegroundWindow() //获取现在前台窗口
	    WinDef.RECT qqwin_rect = new  WinDef.RECT();
	    User32.INSTANCE.GetWindowRect(hwnd, qqwin_rect);
	    int qqwin_width = qqwin_rect.right-qqwin_rect.left;
	    int qqwin_height = qqwin_rect.bottom-qqwin_rect.top;
	 
	    User32.INSTANCE.MoveWindow(hwnd, 700, 100, qqwin_width, qqwin_height, true);
	    for(int i = 700; i > 100; i -=10) {
	        User32.INSTANCE.MoveWindow(hwnd, i, 100, qqwin_width, qqwin_height, true);   // bring to front
	        try {
	            Thread.sleep(80);
	        }catch(Exception e){}
	    }
	 */   
	    //User32.INSTANCE.PostMessage(hwnd, WinUser.WM_CLOSE, null, null);  // can be WM_QUIT in some occasio
	}
	
	}
private static WinUser.INPUT input = new WinUser.INPUT();
private static void sendChar(char ch) {
	input.type = new WinDef.DWORD(WinUser.INPUT.INPUT_KEYBOARD);
	input.input.setType("ki");
	input.input.ki.wScan = new WinDef.WORD(0);
	input.input.ki.time = new WinDef.DWORD(0);
	input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR(0);
	// Press
	input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
	input.input.ki.dwFlags = new WinDef.DWORD(0); // keydown
	User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
	// Release
	input.input.ki.wVk = new WinDef.WORD(Character.toUpperCase(ch)); // 0x41
	input.input.ki.dwFlags = new WinDef.DWORD(2); // keyup
	User32.INSTANCE.SendInput(new WinDef.DWORD(1), (WinUser.INPUT[]) input.toArray(1), input.size());
}



}