package test;

public class MyThread extends Thread{

	int count;
	int[] nums;
	int start,end;
	
	MyThread(int[] nums,int start,int end){
		count = 0;
		this.nums = nums;
		this.start = start;
		this.end = end;
	}
	
	
	@Override
	public void run() {
		for(int i = start; i <= end; i++) {
			for(int j = 0; j < 200; j++) {
				for(int k = 0; k < 500; k++)
					nums[i]++;
			}
				
		}
//		System.out.println(count);
		
	}
	
}
