package amazon;

import java.util.Scanner;

public class CountofStringsThatCanBeFormedUsingabcUnderGivenConstraints {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(sc.nextLine());
		while(t != 0) {
			t--;
			sb.append(solve(Integer.parseInt(sc.nextLine())) + "\n");
		}
//		System.out.println(sb.toString());

	}

	public static int solve(int n) {
		long x = System.currentTimeMillis();
		int[][][] dp = new int[n + 1][2][3];
		int solution = solveRecDP(n, 1, 2, dp);
		long y = System.currentTimeMillis();
		long a = y - x;
		
		x = System.currentTimeMillis();
		solution = solveRec(n, 1, 2);
		y = System.currentTimeMillis();
		long b = y - x;
		System.out.println(a + ", " + b);
		return solution;
	}

	public static int solveRec(int n, int bCount, int cCount) {

		if(bCount < 0 || cCount < 0) {
			return 0;
		}
		if(n == 0) {
			return 1;
		}
		if(bCount == 0 && cCount == 0) {
			return 1;
		}
		
		int res = solveRec(n - 1, bCount, cCount) + solveRec(n - 1, bCount - 1, cCount) + solveRec(n - 1, bCount, cCount - 1);
		return res;
	}

	public static int solveRecDP(int n, int bCount, int cCount, int[][][] dp) {

		if(bCount < 0 || cCount < 0) {
			return 0;
		}
		if(n == 0) {
			return 1;
		}
		if(bCount == 0 && cCount == 0) {
			return 1;
		}
		if(dp[n][bCount][cCount] > 0) {
			return dp[n][bCount][cCount];
		}
		int res = solveRecDP(n - 1, bCount, cCount, dp) + solveRecDP(n - 1, bCount - 1, cCount, dp) + solveRecDP(n - 1, bCount, cCount - 1, dp);
		dp[n][bCount][cCount] = res;
		return res;
	}

}
