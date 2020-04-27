package codeforces.round86;

import java.util.Scanner;

public class RoadToZero {

	public static int mod = 1000000007;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t != 0) {
			long x = sc.nextLong();
			long y = sc.nextLong();
			long a = sc.nextLong();
			long b = sc.nextLong();
			System.out.println(minimumDollarsToSpend(x, y, a, b));
			t--;
		}

	}
	
	
	public static long minimumDollarsToSpend(long x, long y, long a, long b) {
		long ans = 0;
		
		long smaller = Math.min(x, y);
		long larger = Math.max(x, y);
		
		long minAStepsreqd = (larger - smaller);
		
		ans = Math.min(smaller * 2 * a, smaller * b);
		
		ans += (minAStepsreqd) * a;
		
		return ans;
	}

}
