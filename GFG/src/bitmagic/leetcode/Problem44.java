package bitmagic.leetcode;

public class Problem44 {

	public static void main(String[] args) {
		for(int i = 0;i < 34; i++) {
			System.out.println(i + " : " + noOfBitsReqd(i));
		}
		
	}
	
	public static int noOfBitsReqd(int n) {
		return 1 + (int)Math.floor(Math.log(n)/Math.log(2));
	}

}
