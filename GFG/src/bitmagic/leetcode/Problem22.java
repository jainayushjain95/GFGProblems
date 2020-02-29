package bitmagic.leetcode;

public class Problem22 {

	public static void main(String[] args) {
		System.out.println(noOfSteps(14));
	}

	public static int noOfSteps(int n) {
		int count = 0;
		while(n > 0) {
			if(n % 2 == 0) {
				n = divideByTwo(n);
			} else {
				n = n - 1;
			}
			count++;
		}
		return count;    
	}
	
	public static int divideByTwo(int n) {
		return n >> 1;
	}

}
