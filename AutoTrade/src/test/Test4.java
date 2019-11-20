package test;

public class Test4 {

	
	public static float round(float num) {
    	float temp = num * 100;
    	if(temp - Math.floor(temp) >= 0.5)
			return (float) ((Math.floor(temp) + 1) / 100);
		return (float) (Math.floor(temp) / 100);
	}
	
	public static void main(String[] args) {
		System.out.println(12.65*0.9);
		
		String s;
		String s1 = new String("hello");
		String s2 = new String("world");
		
		StringBuilder sb = new StringBuilder();
		sb.append("hello world");
		s = sb.toString();
		
		
		
		switch(s) {
		case "hello":
			System.out.println("hello");
			break;
		case "hello world":
			System.out.println("hello world");
			break;
			
		}
		
	}
}
