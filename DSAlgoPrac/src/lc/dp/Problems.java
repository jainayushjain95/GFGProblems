package lc.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		
		int[] candidates = {1, 2, 3};
		System.out.println((new Problems().combinationSum4DPTD(candidates, 4)));
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
