package dynamic.programming.problems;

public class DP3 {

	public static void main(String[] args) {
		int[] coins = {1, 2, 5};
		int n = coins.length;
		int sum = 5;
		System.out.println(getCountCombinationsDP(coins, n, sum));
	}
	
	public static int getCountCombinations(int[] coins, int n, int sum) {
		if(sum == 0) {
			return 1;
		}
		
		if(sum < 0) {
			return 0;
		}
		
		if(n <= 0) {
			return 0;
		}
		
		return getCountCombinations(coins, n - 1, sum) + getCountCombinations(coins, n, sum - coins[n - 1]);
	}
	
	public static int getCountCombinationsDP(int[] coins, int n, int sum) {
		int[][] dpArray = new int[1 + sum][1 + n];
		for(int i = 1;i <= n; i++) {
			dpArray[0][i] = 1;
		}
		
		for(int i = 1; i <= sum; i++) {
			for(int j = 1; j <= n; j++) {
				dpArray[i][j] = dpArray[i - 1][j];
				if(i >= j) {
					dpArray[i][j] += dpArray[i - j][j];
				}
			}
		}
		
		return dpArray[sum][n];
	}

}
