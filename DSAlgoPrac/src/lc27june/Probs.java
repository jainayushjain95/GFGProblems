package lc27june;

public class Probs {

	public static void main(String[] args) {
		int[] nums = {4,2,5,9,7,4,8};
		int[][] grid = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12},
				{13, 14, 15, 16}
		};
		System.out.println(new Probs().wonderfulSubstrings("aabb"));
	}
	
	public long wonderfulSubstrings(String word) {
		long count = 0l;
		int j = 0;
		int[] alpha = new int[10];
		int countOfOdds = 0;
		
		for(int i = 0;i < word.length();) {
			if(j >= word.length()) {
				alpha[word.charAt(i) - 97]--;
				if(alpha[word.charAt(i) - 97] % 2 == 0) {
					countOfOdds--;
				}  else {
					countOfOdds++;
				}
				if(countOfOdds <= 1) {
					count++;
				} else {
					alpha[word.charAt(i) - 97]--;
				}
				i++;
			} else {
				alpha[word.charAt(j) - 97]++;
				if(alpha[word.charAt(j) - 97] % 2 == 0) {
					countOfOdds--;
				}  else {
					countOfOdds++;
				}
				if(countOfOdds <= 1) {
					j++;
					count++;
				} else {
					alpha[word.charAt(i) - 97]--;
					i++;
					j = i;
				}	
			}
			
		}
		return count;
    }
	
	public static boolean isWOnderful(int[] alpha) {
		int count = 0;
		boolean flag = true;
		for(int i = 0;i < alpha.length; i++) {
			if(alpha[i] % 2 != 0) {
				count++;
				if(count > 1) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	public int[][] rotateGrid(int[][] grid, int k) {
		int rowsCount = grid.length, columnsCount = grid[0].length;
		int layersCount = Math.min(rowsCount >> 1, columnsCount >> 1);
		int startRowIndex = 0, startColumnsIndex = 0;
		int endRowIndex = rowsCount - 1, endColumnsIndex = columnsCount - 1;
		
		for(int layerIndex = 0;layerIndex < layersCount; layerIndex++) {
			int rotationsCount = k % (((rowsCount - 1) << 1) + ((columnsCount - 1) << 1));
			while(rotationsCount > 0) {
				int temp = grid[startRowIndex][startColumnsIndex];
				for(int i = startRowIndex + 1;i <= endRowIndex; i++) {
					int temp2 = grid[i][startColumnsIndex];
					grid[i][startColumnsIndex] = temp;
					temp = temp2;
				}
				for(int i = startColumnsIndex + 1;i <= endColumnsIndex; i++) {
					int temp2 = grid[endRowIndex][i];
					grid[endRowIndex][i] = temp;
					temp = temp2;
				}
				
				for(int i = endRowIndex - 1;i >= startRowIndex; i--) {
					int temp2 = grid[i][endColumnsIndex];
					grid[i][endColumnsIndex] = temp;
					temp = temp2;
				}
				for(int i = endColumnsIndex - 1;i >= startColumnsIndex; i--) {
					int temp2 = grid[startRowIndex][i];
					grid[startRowIndex][i] = temp;
					temp = temp2;
				}
				rotationsCount--;
			}
			rowsCount = rowsCount - 2;
			columnsCount = columnsCount - 2;
			startRowIndex++;
			startColumnsIndex++;
			endRowIndex--;
			endColumnsIndex--;
		}
		
		return grid;
    }
	
	public int maxProductDifference(int[] nums) {
		int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
		
		for(int x : nums) {
			if(x < min) {
				secondMin = min;
				min = x;
			} else if(x >= min && x <= secondMin) {
				secondMin = x;
			}
			
			if(x >= secondMax && x <= max) {
				secondMax = x;
			} else if(x > max){
				secondMax = max;
				max = x;
			}
		}
		
		
		return (max * secondMax) - (min * secondMin);
    }

}
