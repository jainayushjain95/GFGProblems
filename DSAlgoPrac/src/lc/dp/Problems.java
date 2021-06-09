package lc.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


class Pair {
	int i;
	int j;
	
	public Pair(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
}

public class Problems {

	
	public static void main(String[] args) {
//		int[][] data = {{2},{3,4}, {6,5,7}, {4,1,8,3}};
//		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
//		for(int i = 0;i < data.length; i++) {
//			List<Integer> list = new ArrayList<Integer>();
//			for(int j = 0;j < data[i].length; j++) {
//				list.add(data[i][j]);
//			}
//			triangle.add(list);
//		}
//		System.out.println((new Problems()).minimumTotal(triangle));
		
		int[] coins = {2};
		System.out.println((new Problems().coinChange(coins, 3)));
	}
	
	public int coinChange(int[] coins, int amount) {
		int coinsCount = coinChangeDP(coins, amount);
		return coinsCount == Integer.MAX_VALUE ? -1 : coinsCount;
    }
	
	public int coinChangeDP(int[] coins, int amount) {
		int[] dpArray = new int[amount + 1];
		for(int i = 1;i < dpArray.length; i++) {
			dpArray[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 1;i < dpArray.length; i++) {
			int currCoinsCount = dpArray[i];
			for(int coin : coins) {
				if(coin <= i) {
					int newCoinsCount = (dpArray[i - coin] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : 1 + dpArray[i - coin];
					if(newCoinsCount < currCoinsCount) {
						currCoinsCount = newCoinsCount;
					}
				}
			}
			dpArray[i] = currCoinsCount;
		}
		
		return dpArray[dpArray.length - 1]; 
	}
	
	public int coinChangeRecursive(int[] memo, int[] coins, int amount) {
		if(amount == 0) {
			return 0;
		}
		
		if(amount < 0) {
			return -1;
		}
		
		if(memo[amount] > 0) {
			return memo[amount];
		}
		int minCoins = Integer.MAX_VALUE;
		
		for(int i = 0;i < coins.length; i++) {
			int coinsCount = coinChangeRecursive(memo, coins, amount - coins[i]);
			if(coinsCount >= 0 && minCoins > coinsCount) {
				minCoins = 1 + coinsCount;
			}
			
		}
		memo[amount] = minCoins;
		return minCoins;
    }
	
	public boolean isInterleave(String s1, String s2, String s3) {
		if((s1.length() + s2.length()) != s3.length()) {
			return false;
		}
		
		return isInterleaveSolveDP(s1, s2, s3);
    }
	
	public boolean isInterleaveSolveDP(String s1, String s2, String s3) {
		boolean[][] dpArray = new boolean[s1.length() + 1][s2.length() + 1];
		
		for(int i = 0;i < dpArray.length; i++) {
			for(int j = 0;j < dpArray[i].length; j++) {
				if(i == 0 && j == 0) {
					dpArray[i][j] = true;
				} else if(i == 0) {
					dpArray[i][j] = dpArray[i][j - 1] && s3.charAt(j - 1) == s2.charAt(j - 1);
				} else if(j == 0) {
					dpArray[i][j] = dpArray[i - 1][j] && s3.charAt(i - 1) == s1.charAt(i - 1);
				} else {
					dpArray[i][j] = (dpArray[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1))
							 || (dpArray[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
				}
			}
		}
		return dpArray[dpArray.length - 1][dpArray[0].length - 1];
	}
	
	public String getKey(int i, int j, int k) {
		StringBuilder key = new StringBuilder(i);
		key.append("#");
		key.append(j);
		key.append("#");
		key.append(k);
		return key.toString();
	}
	
	public boolean isInterleaveSolve(Map<String, Boolean> memo, String s1, String s2, String s3, int i, int j, int k) {
		if(k == s3.length()) {
			return s1.length() == i && s2.length() == j;
		}
		
		String key = getKey(i, j, k);
		if(memo.containsKey(key)) {
			return memo.get(key);
		}
		
		boolean isInterleavingLeft = (i < s1.length()) && (s1.charAt(i) == s3.charAt(k)) && isInterleaveSolve(memo, s1, s2, s3, i + 1, j, k + 1);
		boolean isInterleavingRight = (j < s2.length()) && (s2.charAt(j) == s3.charAt(k)) && isInterleaveSolve(memo, s1, s2, s3, i, j + 1, k + 1);	
		
		boolean sol = isInterleavingLeft || isInterleavingRight;
		
		memo.put(key, sol);
		
		return sol;
	}
	
	public int minCost(int[][] costs) {
		int[][] memo = new int[costs.length][3];
		minCostDP(costs);
		return Math.min(Math.min(minCostRec(memo, costs, 0, 0), minCostRec(memo, costs, 0, 1)), minCostRec(memo, costs, 0, 2));
    }
	
	public int minCostDP(int[][] costs) {
		int minCost = 0;
		int[][] dpArray = new int[costs.length][3];
		
		dpArray[costs.length - 1] = costs[costs.length - 1];
		
		for(int i = costs.length - 2; i >= 0; i--) {
			dpArray[i][0] = costs[i][0] + Math.min(dpArray[i + 1][1], dpArray[i + 1][2]);
			dpArray[i][1] = costs[i][1] + Math.min(dpArray[i + 1][0], dpArray[i + 1][2]);
			dpArray[i][2] = costs[i][2] + Math.min(dpArray[i + 1][0], dpArray[i + 1][1]);
		}
		
		minCost = Math.min(dpArray[0][0], Math.min(dpArray[0][1], dpArray[0][2]));
		
		return minCost;
	}
	
	public int minCostRec(int[][] memo, int[][] costs, int houseIndex, int color) {
		if(houseIndex > costs.length - 1) {
			return 0;
		}
		if(memo[houseIndex][color] != 0) {
			return memo[houseIndex][color];
		}
		int totalCost = costs[houseIndex][color];
		if(color == 0) {
			totalCost += Math.min(minCostRec(memo, costs, 1 + houseIndex, 1), minCostRec(memo, costs, 1 + houseIndex, 2));
		} else if(color == 1) {
			totalCost += Math.min(minCostRec(memo, costs, 1 + houseIndex, 2), minCostRec(memo, costs, 1 + houseIndex, 0));
		} else {
			totalCost += Math.min(minCostRec(memo, costs, 1 + houseIndex, 0), minCostRec(memo, costs, 1 + houseIndex, 1));			
		}

		memo[houseIndex][color] = totalCost;
		return totalCost;
	}
	
	
	public int numDecodings(String s) {
		int numDecodings = 0;
		int[] memo = new int[s.length()];
		Arrays.fill(memo, -1);
		numDecodings = numDecodingsSolve(memo, s, 0);
		return numDecodings;
    }
	
	public int numDecodingsSolve(int[] memo, String s, int beginIndex) {

		if(beginIndex < s.length() && s.charAt(beginIndex) == '0') {
			return 0;
		}
		
		if(beginIndex >= s.length() - 1) {
			return 1;
		}
		
		if(memo[beginIndex] > -1) {
			return memo[beginIndex];
		}		
		
		int singleCharCount = numDecodingsSolve(memo, s, beginIndex + 1);
		
		int doubleCharCount = 0;
		
		if(beginIndex < s.length() - 1 && Integer.parseInt(s.substring(beginIndex, beginIndex + 2)) <= 26) {
			doubleCharCount = numDecodingsSolve(memo, s, beginIndex + 2);
		}
		
		memo[beginIndex] = singleCharCount + doubleCharCount;
		return memo[beginIndex];
	}
	
	
	public int combinationSum4(int[] nums, int target) {
		int ans = combinationSum4DPTD(nums, target);
		return ans;
    }
	
	public int combinationSum4DPTD(int[] nums, int target) {
		int[] dpArray = new int[target + 1];
			
		dpArray[0] = 1;
		for(int i = 1;i < dpArray.length; i++) {
			for(int num : nums) {
				if(i - num >= 0) {
					dpArray[i] += dpArray[i - num]; 
				}
			}
		}
		
		return dpArray[target];
    }
	
	public int combinationSum4Rec(int[] nums, int target) {
		if(target == 0) {
			return 1;
		}
		if(target < 0) {
			return 0;
		}
		
		int solve = 0;
		for(int i = 0; i < nums.length; i++) {
			solve += combinationSum4Rec(nums, target - nums[i]);
		}
		return solve;
    }
	
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> cs3 = new ArrayList<List<Integer>>(); 
		combinationSumRecursive(0, new LinkedList<Integer>(), cs3, candidates, target);
		return cs3;
    }

	public void combinationSumRecursive(int index, List<Integer> path, List<List<Integer>> cs3, int[] candidates, int target) {
		if(target == 0) {
			cs3.add(new LinkedList<Integer>(path));
			return;
		}
		
		if(target < 0) {
			return;
		}
		
		if(index >= candidates.length) {
			return;
		}
		
		for(int i = index;i < candidates.length; i++) {
			path.add(candidates[i]);
			combinationSumRecursive(i, path, cs3, candidates, target - candidates[i]);
			path.remove(path.size() - 1);
		}
	}
	
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> cs3 = new ArrayList<List<Integer>>();
		combinationSum3BackTrackSolve(cs3, k, n, new LinkedList<Integer>(), 1);
		return cs3;
    }
	
	public void combinationSum3BackTrackSolve(List<List<Integer>> cs3, int k, int n, List<Integer> path, int start) {
		if(path.size() == k && n == 0) {
			cs3.add(new LinkedList<Integer>(path));
			return;
		}
		if(n < 0 || path.size() == k) {
			return;
		}
		for(int i = start; i <= 9; i++) {
			path.add(i);
			combinationSum3BackTrackSolve(cs3, k, n - i, path, i + 1);
			path.remove(path.size() - 1);
		}
	}
	
	public int minimumTotal(List<List<Integer>> triangle) {
		return minimumTotalSolveDP(triangle);
    }
	
	public int minimumTotalSolveDP(List<List<Integer>> triangle) {
		
		for(int i = triangle.size() - 2; i >= 0; i--) {
			List<Integer> base = triangle.get(i);
			List<Integer> base2 = triangle.get(i + 1);
			for(int j = 0;j < base.size(); j++) {
				base.set(j, base.get(j) + Math.min(base2.get(j), base2.get(j + 1)));
			}
		}
		
		return triangle.get(0).get(0);
	}
	
	public int minimumTotalSolveRecursive(List<List<Integer>> triangle, int index, int rowIndex) {
		System.out.println(index + ", " + rowIndex);
		if(rowIndex >= triangle.size()) {
			return 0;
		}
		int poss1 = triangle.get(rowIndex).get(index) + minimumTotalSolveRecursive(triangle, index, rowIndex + 1);
		int poss2 = triangle.get(rowIndex).get(index + 1) + minimumTotalSolveRecursive(triangle, index + 1, rowIndex + 1);
		
        return Math.min(poss1, poss2);
    }
	
	public int maxProfit(int[] prices) {
		int maxProfit = 0;
		int min = prices[0];

		for(int i = 1;i < prices.length; i++) {
			int currProfit = prices[i] - min;
			if(currProfit > maxProfit) {
				maxProfit = currProfit;
			}
			if(min > prices[i]) {
				min = prices[i];
			}
		}

		return maxProfit;
	}

	public int maxProfit2(int[] prices) {
		int maxProfit = 0;

		for(int i = 1;i < prices.length; i++) {
			if(prices[i] > prices[i - 1]) {
				maxProfit += prices[i] - prices[i - 1];
			}
		}

		return maxProfit;
	}

	public int uniquePaths(int m, int n) {
		return uniquePathsDP(m, n);
	}

	public int uniquePathsDP(int m, int n) {
		int[][] dpARray = new int[m][n];
		for(int i = 0; i < n; i++) {
			dpARray[m - 1][i] = 1;
		}

		for(int i = 0; i < m; i++) {
			dpARray[i][n - 1] = 1;
		}

		for(int i = m - 2; i >= 0; i--) {
			for(int j = n - 2; j >= 0; j--) {
				dpARray[i][j] = dpARray[i + 1][j] + dpARray[i][j + 1];
			}
		}
		return dpARray[0][0];
	}

	public int uniquePathsRecusrive(int m, int n, int rowIndex, int columnIndex) {

		if(rowIndex == m - 1 || columnIndex == n - 1) {
			return 1;
		}

		return uniquePathsRecusrive(m, n, rowIndex + 1, columnIndex) + uniquePathsRecusrive(m, n, rowIndex, columnIndex + 1);
	}

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		return uniquePathsWithObstaclesDP(obstacleGrid);  
	}
	
	public int uniquePathsWithObstaclesDP(int[][] obstacleGrid) {
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		if(obstacleGrid[m - 1][n - 1] == 1) {
			return 0;
		}
		
		int[][] dpArray = new int[m][n];
		boolean obstacleFound = false;
		for(int i = n - 1; i >= 0; i--) {
			if(!obstacleFound) {
				dpArray[m - 1][i] = (obstacleGrid[m - 1][i] == 1) ? 0 : 1;
				obstacleFound = dpArray[m - 1][i] == 0;
			}
		}
		obstacleFound = false;
		for(int i = m - 1; i >= 0; i--) {
			if(!obstacleFound) {
				dpArray[i][n - 1] = (obstacleGrid[i][n - 1] == 1) ? 0 : 1;
				obstacleFound = dpArray[i][n - 1] == 0;
			}
		}
		
		for(int i = m - 2; i >= 0; i--) {
			for(int j = n - 2; j >= 0; j--) {
				if(obstacleGrid[i][j] != 1) {
					dpArray[i][j] = dpArray[i][j + 1] + dpArray[i + 1][j];
				}
			}
		}
		
		return dpArray[0][0];
	}
	
	public int uniquePathsWithObstaclesRecursive(int[][] obstacleGrid, int rowIndex, int columnIndex) {
		if(rowIndex >= obstacleGrid.length || columnIndex >= obstacleGrid[rowIndex].length) {
			return 0;
		}
		if(obstacleGrid[rowIndex][columnIndex] == 1) {
			return 0;
		}
		if(rowIndex == obstacleGrid.length - 1 && columnIndex == obstacleGrid[rowIndex].length - 1) {
			return 1;
		}
		
		return uniquePathsWithObstaclesRecursive(obstacleGrid, rowIndex + 1, columnIndex) + uniquePathsWithObstaclesRecursive(obstacleGrid, rowIndex, columnIndex + 1);  
	}

}
