package june.long2020;

import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		StringBuilder str = new StringBuilder();
	
		
		while(t != 0) {
			str.append(solve(sc.nextLong()) + "\n");
			t--;
		}
		
		System.out.println(str.toString());
	}
//
//	public static String brute(long TS) {
//		StringBuilder str = new StringBuilder();
//		long noOfWays = 0;
//		for(long i = 2;i <= TS; i = i + 2) {
//			long x = i, y = TS;
//			while(x % 2 == 0 && y % 2 == 0) {
//				x = x / 2;
//				y = y / 2;
//			}
//			if(x % 2 == 0 && y % 2 != 0) {
//				noOfWays++;
//			}
//		}
//		str.append(noOfWays);
//		return str.toString();
//	}
	
	public static String solve(long TS) {
		StringBuilder str = new StringBuilder();
		long noOfWays = 0;
		if(TS % 2 != 0) {
			noOfWays = TS / 2;
		} else if(isPowerOfTwo(TS)) {
			noOfWays = 0;
		} else if(TS % 4 != 0){
			noOfWays = TS / 4;
		} else {
			long TS1 = TS;
			long start = 2;
			while(TS1 % 2 == 0) {
				start = start * 2;
				TS1 = TS1 / 2;
			}
			noOfWays = TS / start;
		}

		str.append(noOfWays);
		return str.toString();
	}
	
	public static boolean isPowerOfTwo(long x) {
		return (x & (x - 1)) == 0;
	}

}
