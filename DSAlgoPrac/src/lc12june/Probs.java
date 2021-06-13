package lc12june;

public class Probs {

	public static void main(String[] args) {
		
		int[][] chalk = {{5,1,3,1},{9,3,3,1},{1,3,3,8}};
		
		System.out.println(new Probs().largestMagicSquare(chalk));

	}
	
	public int largestMagicSquare(int[][] grid) {
		int length = 1, currSize = 2, maxSize = Math.min(grid.length, grid[0].length);
		
		
		while(currSize <= maxSize) {
			for(int i = 0;i <= grid.length - currSize; i++) {
				for(int j = 0;j <= grid[i].length - currSize; j++) {
					long rowSum = checkRowSums(grid, i, j, currSize);
					if(rowSum != -1) {
						long colSum = checkColSums(grid, i, j, currSize);
						if(colSum != -1 && rowSum == colSum) {
							long dia1Sum = checkDia1Sums(grid, i, j, currSize);
							if(dia1Sum == colSum) {
								long dia2Sum = checkDia2Sums(grid, i, j, currSize);
								if(dia2Sum == dia1Sum) {
									length = currSize;
								}
							}
						}
					}
				}
			}
			currSize++;
		}
		
		return length;
    }

	public long checkDia1Sums(int[][] grid, int rowIndex, int columnIndex, int size) {
		long sum = 0;
		int col = 0;
		for(int row = 0; row < size; row++) {
			sum = sum + grid[rowIndex + row][columnIndex + col];
			col++;
		}
		
		return sum;
	}
	
	public long checkDia2Sums(int[][] grid, int rowIndex, int columnIndex, int size) {
		long sum = 0;
		int col = 0, row = 0;
		for(col = columnIndex + size - 1; col >= columnIndex; col--) {
			sum = sum + grid[rowIndex + row][col];
			row++;
		}
		
		return sum;
	}
	
	public long checkRowSums(int[][] grid, int rowIndex, int columnIndex, int size) {
		long sum = 0;
		
		for(int row = 0; row < size; row++) {
			if(sum == -1) {
				break;
			}
			long currSum = 0;
			for(int i = 0; i < size; i++) {
				currSum = currSum + grid[rowIndex + row][columnIndex + i];
			}
			if(row == 0) {
				sum = currSum;
			} else if(currSum != sum) {
				sum = -1;
				break;
			}
		}
		
		return sum;
	}
	
	public long checkColSums(int[][] grid, int rowIndex, int columnIndex, int size) {
		long sum = 0;
		
		for(int col = 0; col < size; col++) {
			if(sum == -1) {
				break;
			}
			long currSum = 0;
			for(int i = 0; i < size; i++) {
				currSum = currSum + grid[rowIndex + i][columnIndex + col];
			}
			if(col == 0) {
				sum = currSum;
			} else if(currSum != sum) {
				sum = -1;
				break;
			}
		}
		
		return sum;
	}
	
	public int chalkReplacer(int[] chalk, int k) {
		int index = 0;
		long sum = 0;
		long K = k;
		
		for(int x : chalk) {
			sum += x;
		}
		
		K = K % sum;
		
		while(index < chalk.length && K >= chalk[index]) {
			K = K - chalk[index];
			index++;
		}
		
		return index;
    }
	
	public boolean isCovered(int[][] ranges, int left, int right) {
		boolean isCovered = true;
		
		while(isCovered && left <= right) {
			boolean isLeftCovered = false;
			for(int i = 0;i < ranges.length; i++) {
				if(ranges[i][0] <= left && ranges[i][1] >= left) {
					isLeftCovered = true;
					break;
				}
			}
			if(!isLeftCovered) {
				isCovered = false;
				break;
			}
			left++;
		}
		
		return isCovered;
    }
	
}
