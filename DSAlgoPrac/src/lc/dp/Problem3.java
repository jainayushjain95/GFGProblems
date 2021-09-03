package lc.dp;

import java.util.Arrays;

public class Problem3 {

	int[][] dp1;
	Boolean[][] dp2;
	int[] dp3;

	public static void main(String[] args) {
		int[][] grid = {
				{8,2,8,15,5},
				{8,17,1,15,3},
				{8,8,5,5,16},
				{2,2,18,2,9}
			};
		int sol = (new Problem3()).numDecodings("27");
		System.out.println(sol);
	}
	
	public int numDecodings(String s) {
		dp3 = new int[s.length() + 1];
		return numDecodingsSolve2(s);
    }
	
	public int numDecodingsSolve2(String s) {
		int a = 1, b = s.charAt(0) == '0' ? 0 : 1, c = 0; 
		for(int i = 2;i <= s.length(); i++) {
			if(s.charAt(i - 1) != '0') {
				dp3[i] = dp3[i - 1];
			}
			
			int digitsPair = Integer.parseInt(s.substring(i - 2, i));
			if(digitsPair >= 10 && digitsPair <= 26) {
				dp3[i] += dp3[i - 2];
			}
		}
		
		return dp3[s.length()];
	}
	
	public int numDecodingsSolve(String s, int index) {
		if(index == s.length()) {
			return 1;
		}
		
		if(index > s.length()) {
			return 0;
		}
		
		if(dp3[index] > -1) {
			return dp3[index];
		}
		
		if(s.charAt(index) == '0') {
			return 0;
		}
		
		int ways = numDecodingsSolve(s, index + 1);
		
		if(index < s.length() - 1 && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
			ways += numDecodingsSolve(s, index + 2);
		}
		
		dp3[index] = ways;
		
		return ways;
    }
	
	public int minCostII(int[][] costs) {
		return minCostIISolve2(costs);
    }
	
	public int minCostIISolve2(int[][] costs) {
        int minCost = Integer.MAX_VALUE, n = costs.length, k = costs[0].length;
        int[] previousRow = costs[n - 1];
        
        
        for(int i = n - 2;i >= 0; i--) {
        	int[] currentRow = new int[k];
        	int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        	int minIndex = 0, secondMinIndex = 0;
        	
        	for(int j = 0;j < k; j++) {
        		if(previousRow[j] < min) {
        			secondMin = min;
        			min = previousRow[j];
        			secondMinIndex = minIndex;
        			minIndex = j;
        		} else if(previousRow[j] < secondMin) {
        			secondMin = previousRow[j];
        			secondMinIndex = j;
        		}
        	}
        	
        	for(int j = 0;j < k; j++) {
        		if(j == minIndex) {
        			currentRow[j] = costs[i][j] + secondMin;
        		} else {
        			currentRow[j] = costs[i][j] + min;
        		}
        	}
        	
        	previousRow = currentRow;
        }
        
        for(int x : previousRow) {
        	minCost = Math.min(minCost, x);
        }
        
        
        return minCost;
	}
	
	public int minCostIISolve(int[][] costs, int houseIndex, int colorIndex) {
		if(houseIndex >= costs.length) {
			return 0;
		}
		if(dp1[houseIndex][colorIndex] > -1) {
			return dp1[houseIndex][colorIndex];
		}
		int minCost = 10000;
		for(int i = 0;i < costs[0].length; i++) {
        	if(colorIndex != i) {
        		minCost = Math.min(minCost, costs[houseIndex][i] + minCostIISolve(costs, houseIndex + 1, i));	
        	}
        }
		dp1[houseIndex][colorIndex] = minCost;
        return minCost;
    }
	
	public boolean canPartition(int[] nums) {
		int sum = getSum(nums);
		dp2 = new Boolean[1 + nums.length][1 + sum/2];
		return sum % 2 == 0 && subsetSumExists2(nums, sum/2, nums.length);
    }
	
	public boolean subsetSumExists2(int[] nums, int targetSum, int n) {
		for(int i = 0;i <= nums.length; i++) {
			dp2[i][0] = true;
		}
	
		for(int i = 1;i <= targetSum; i++) {
			dp2[0][i] = false;
		}
		
		for(int i = 1;i <= nums.length; i++) {
			for(int j = 1;j <= targetSum; j++) {
				if(nums[i - 1] > j) {
					dp2[i][j] = dp2[i - 1][j];
				} else {
					dp2[i][j] = dp2[i - 1][j] || dp2[i - 1][j - nums[i - 1]];
				}
			}
		}
		
		return dp2[nums.length][targetSum];
	}
	
	public boolean subsetSumExists1(int[] nums, int targetSum, int n) {
		if(targetSum == 0) {
			return true;
		}
		if(n == 0) {
			return false;
		}
		if(dp2[n][targetSum] != null) {
			return dp2[n][targetSum];
		}
		if(nums[n - 1] > targetSum) {
			dp2[n][targetSum] = subsetSumExists1(nums, targetSum, n - 1);
		} else {
			dp2[n][targetSum] = subsetSumExists1(nums, targetSum, n - 1) || subsetSumExists1(nums, targetSum - nums[n - 1], n - 1);
		}
		return dp2[n][targetSum];
	}
	
	public int getSum(int[] nums) {
		int sum = 0;
		for(int x : nums) {
			sum += x;
		}
		return sum;
	}

	public int minPathSum(int[][] grid) {
		dp1 = new int[grid.length][grid[0].length];
		return minPathSumSolve(grid);
	}
	
	public int minPathSumSolve(int[][] grid) {
		dp1 = new int[grid.length][grid[0].length];
		dp1[grid.length - 1][grid[0].length - 1] = grid[grid.length - 1][grid[0].length - 1];
		
		for(int i = grid.length - 1;i >= 0; i--) {
			for(int j = grid[0].length - 1;j >= 0; j--) {
				if(i == grid.length - 1 && j == grid[0].length - 1) {
					dp1[i][j] = grid[i][j];
				} else if(j == grid[0].length - 1) {
					dp1[i][j] = grid[i][j] + dp1[i + 1][j];
				} else if(i == grid.length - 1) {
					dp1[i][j] = grid[i][j] + dp1[i][j + 1];
				} else {
					dp1[i][j] = grid[i][j] + Math.min(dp1[i][j + 1], dp1[i + 1][j]);	
				}
			}
		}
		
		return dp1[0][0];
	}
	
	public int minPathSum(int[][] grid, int rowIndex, int colIndex) {
		if(rowIndex == grid.length - 1 && colIndex == grid[0].length - 1) {
			return grid[rowIndex][colIndex];
		}
		if(dp1[rowIndex][colIndex] > 0) {
			return dp1[rowIndex][colIndex];
		}
		int minCost = Integer.MAX_VALUE;
		if(colIndex < grid[0].length - 1) {
			minCost = grid[rowIndex][colIndex] + minPathSum(grid, rowIndex, colIndex + 1);
		}
		if(rowIndex < grid.length - 1) {
			minCost = Math.min(minCost, grid[rowIndex][colIndex] + minPathSum(grid, rowIndex + 1, colIndex));
		}
		dp1[rowIndex][colIndex] = minCost;
		return minCost;
	}

	public int lengthOfLIS(int[] nums) {
		int lisLength = 1;
		int[] lisArray = new int[nums.length];
		lisArray[0] = 1;

		for(int beginIndex = 1; beginIndex < nums.length; beginIndex++) {
			int currMax = 1;
			for(int index = beginIndex - 1; index >= 0; index--) {
				if(nums[index] < nums[beginIndex]) {
					currMax = Math.max(currMax, 1 + lisArray[index]);
				}
			}
			lisArray[beginIndex] = currMax;
			if(currMax > lisLength) {
				lisLength = currMax;
			}
		}

		return lisLength;
	}


	public int minInsertions(String s) {
		dp1 = new int[s.length()][s.length()];
		return s.length() - getLPS(s, 0, s.length() - 1);
	}

	public int getLPS(String s, int beginIndex, int endIndex) {
		return getLPSBottomUp(s);
	}

	public int getLPSBottomUp(String s) {
		for(int i = s.length() - 1; i >= 0; i--) {
			dp1[i][i] = 1;
			char c = s.charAt(i);
			for(int j = i + 1; j < s.length(); j++) {
				if(c == s.charAt(j)) {
					dp1[i][j] = 2 + dp1[i + 1][j - 1];
				} else {
					dp1[i][j] = Math.max(dp1[i][j], dp1[i][j - 1]);
				}
			}
		}
		return dp1[0][s.length() - 1];
	}

	public int getLPSRec(String s, int beginIndex, int endIndex) {
		if(beginIndex > endIndex) {
			return 0;
		}

		if(dp1[beginIndex][endIndex] > -1) {
			return dp1[beginIndex][endIndex];
		}

		if(beginIndex == endIndex) {
			dp1[beginIndex][endIndex] = 1;
			return 1;
		}

		if(s.charAt(beginIndex) == s.charAt(endIndex)) {
			dp1[beginIndex][endIndex] = 2 + getLPSRec(s, beginIndex + 1, endIndex - 1);
		} else {
			dp1[beginIndex][endIndex] = Math.max(getLPSRec(s, beginIndex + 1, endIndex), getLPSRec(s, beginIndex, endIndex - 1));	
		}
		return dp1[beginIndex][endIndex];
	}

}
