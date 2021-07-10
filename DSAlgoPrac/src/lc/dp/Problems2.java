package lc.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problems2 {
	static int k = 0;
	public static void main(String[] args) {
		int[][] matrix = {
				{0,1,1,1},
				{1, 1, 1, 1},
				{0, 1, 1, 1}
//				{1, 1, 0},
//				{1, 1, 0},
		};
		System.out.println(new Problems2().countSquares(matrix));
	}

	public int countSquares(int[][] matrix) {
		int count = 0;
		int rowsCount = matrix.length, colsCount = matrix[0].length;
		int[][] sumMatrix = new int[rowsCount][colsCount];
		
		for(int i = 0;i < rowsCount; i++) {
			for(int j = 0;j < colsCount; j++) {
				if(matrix[i][j] != 0) {
					if(i == 0 || j == 0) {
						sumMatrix[i][j] = 1;
					} else {
						sumMatrix[i][j] = 1 + Math.min(sumMatrix[i - 1][j], Math.min(sumMatrix[i][j - 1], sumMatrix[i - 1][j - 1]));
					}
					count += sumMatrix[i][j];
				}
			}
		}
		
		return count;
    }
	

		
	public int minDistance(String word1, String word2) {
		int[][] memo = new int[word1.length()][word2.length()];

		return minDistanceSolve(memo, word1, word2, 0, 0);
	}

	public int countVowelStrings(int n) {
		int[][] memo = new int[n + 1][6];
		for(int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1); 
		}
		return countVowelStringsSolve(memo, n, 1);
	}

	public int countVowelStringsSolve(int[][] memo, int n, int pos) {
		if(n == 0) {
			k++;
			return 1;	
		}
//		if(memo[n][pos] > -1) {
//			return memo[n][pos];
//		}
		int sum = 0;
		for(int i = pos; i <= 5; i++) {
			sum += countVowelStringsSolve(memo, n - 1, i);
		}
		memo[n][pos] = sum;
		return sum;
	}

	public int minDistanceSolve(int[][] memo, String word1, String word2, int beginIndex1, int beginIndex2) {
		if(beginIndex1 >= word1.length() && beginIndex2 >= word2.length()) {
			return 0;
		}

		if(beginIndex1 >= word1.length()) {
			return word2.length() - beginIndex2;
		}

		if(beginIndex2 >= word2.length()) {
			return word1.length() - beginIndex1;
		}

		if(memo[beginIndex1][beginIndex2] > 0) {
			return memo[beginIndex1][beginIndex2];
		}

		int minDis = 0;

		if(word1.charAt(beginIndex1) == word2.charAt(beginIndex2)) {
			minDis = minDistanceSolve(memo, word1, word2, beginIndex1 + 1, beginIndex2 + 1);
		} else {
			minDis = 1 + Math.min(
					minDistanceSolve(memo, word1, word2, beginIndex1 + 1, beginIndex2),
					minDistanceSolve(memo, word1, word2, beginIndex1, beginIndex2 + 1)
					);
		}

		memo[beginIndex1][beginIndex2] = minDis;

		return minDis;
	}

	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> lists = new LinkedList<List<Integer>>();
		solve(n, k, 0, lists, new LinkedList<Integer>());
		return lists;
	}

	public void solve(int n, int k, int currentPosition, List<List<Integer>> lists, List<Integer> list) {
		if(list.size() == k) {
			lists.add(new LinkedList<Integer>(list));
			return;
		}
		if(currentPosition > n) {
			return;
		}
		for(int i = currentPosition + 1; i <= n; i++) {
			list.add(i);
			solve(n, k, i, lists, list);
			list.remove(list.size() - 1);
		}
	}

	public int getMaximumGenerated(int n) {
		int max = 0;
		if(n == 0) {
			return max;
		} else if(n == 1) {
			return 1 + max;
		} else {
			max = 1;
			int[] array = new int[n + 1];
			array[1] = 1;

			for(int i = 2; i <= n; i++) {
				int base = i >> 1;
			if(i % 2 == 0) {
				array[i] = array[base];
			} else {
				array[i] = array[base] + array[i - base];
			}
			max = Math.max(max, array[i]);
			}

		}
		return max;
	}

	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> pascal = new ArrayList<List<Integer>>();
		List<Integer> row = new ArrayList<Integer>();
		row.add(1);
		pascal.add(row);
		int rowIndex = 1;

		while(rowIndex < numRows) {
			List<Integer> newRow = new ArrayList<Integer>();
			newRow.add(1);
			for(int i = 0;i < pascal.get(rowIndex- 1).size() - 1; i++) {
				newRow.add(pascal.get(rowIndex- 1).get(i) + pascal.get(rowIndex- 1).get(i + 1));
			}
			newRow.add(1);
			pascal.add(newRow);
			rowIndex++;
		}

		return pascal;
	}

	public int rotatedDigits(int n) {
		int count = 0;
		int[] dpArray = new int[n + 1];

		for(int i = 0; i <= n; i++) {
			if(i < 10) {
				if(i == 0 || i == 1  || i == 8) {
					dpArray[i] = 1;
				} else if(i == 5 || i == 2 || i == 6 || i == 9) {
					count++;
					dpArray[i] = 2;
				}
			} else {
				int mod = i % 10, div = i / 10;
				if(dpArray[mod] == 1 && dpArray[div] == 1) {
					dpArray[i] = 1;
				} else if(dpArray[mod] >= 1 && dpArray[div] >= 1) {
					dpArray[i] = 2;
					count++;
				}
			}
		}

		return count;
	}

	public boolean valid(int n) {
		boolean isValid = false;

		while(n > 0) {
			int mod = n  % 10;
			if(mod == 3 || mod == 4 || mod == 7) {
				isValid = false;
				break;
			} else if(mod == 2 || mod == 5 || mod == 6 || mod == 9) {
				isValid = true;
			}
			n = n / 10;
		}

		return isValid;
	}

	public int climbStairs(int[] memo, int n) {
		if(n < 0) {
			return 0;
		}

		if(n <= 1) {
			return 1;
		}

		if(memo[n] >= 0) {
			return memo[n];
		}

		int solve = climbStairs(memo, n - 1) + climbStairs(memo, n - 2);
		memo[n] = solve;
		return solve;
	}

}
