package lc.dp;

public class Problems {

	public static void main(String[] args) {
		int[][] array = {
				{0, 0, 0}, {0, 1, 0}, {0, 0, 0}
		};
		System.out.println((new Problems()).uniquePathsWithObstacles(array));
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
