package lc.google;

import java.util.Arrays;

public class UniqueBinarySearchTrees {

	int[] dp;
	
	public static void main(String[] args) {
		System.out.println(new UniqueBinarySearchTrees().numTrees(4));
	}
	
	public int numTrees(int n) {
		dp = new int[n + 1];
		return numTreesSolveBottomUP(n);
    }
	
	public int numTreesSolveBottomUP(int n) {
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i = 2; i <= n; i++) {
			for(int j = 1;j <= i; j++) {
				dp[i] += dp[j - 1] * dp[i - j];
			}
		}
		
		return dp[n];
	}
	
	public int numTreesSolve(int n) {
		if(n <= 1) {
			dp[n] = 1;
			return 1;
		}
		
		if(dp[n] > 0) {
			return dp[n];
		}
		
		int ans = 0;
		for(int i = 1;i <= n; i++) {
			ans += numTreesSolve(i - 1) * numTreesSolve(n - i);
		}
		dp[n] = ans;
		return ans;
    }

}

/*
 * Recursive Time COmplexity
 *	F(0) == F(1) == 1
 *	F(3) = [F(0) + F(2)] + [F(1) + F(1)] + [F(2) + F(0)]
 *		 = 2 * [F(0) + F(1) + F(2)]
 *
 *  F(n) = 2 * [F(0) + F(1) + F(2) + ..... + F(n - 1)] -- 1
 *  
 * => F(n + 1) = 2 * [F(0) + F(1) + F(2) + ..... + F(n - 1) + F(n)]  -- 2
 * 
 * 2 - 1 
 * 	==> F(n + 1) - F(n) = 2F(n)
 * 	=> F(n + 1) = 3 * F(n)
 * 
 *  ===> F(n) = 3 * F(n - 1)
 *  
 *  =======> T(n) = T(n - 1) + T(n - 1) + T(n - 1) + c
 *  
 *  T(n) = 3^n
 *  
 *  	 
*/