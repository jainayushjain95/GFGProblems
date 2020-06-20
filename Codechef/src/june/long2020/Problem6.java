package june.long2020;

import java.util.LinkedList;
import java.util.Scanner;

import june.long2020.GUESSGQ.Ranges;

public class Problem6 {
	public static Scanner sc = new Scanner(System.in);
	
	public static class Block {
		public int startRow;
		public int startColumn;
		public int endRow;
		public int endColumn;
		public int countOfOnes;
		
		public Block(int startRow, int startColumn, int endRow, int endColumn, int countOfOnes) {
			this.startRow = startRow;
			this.startColumn = startColumn;
			this.endRow = endRow;
			this.endColumn = endColumn;
			this.countOfOnes = countOfOnes;
		}	
	}
	
	public static void main(String[] args) {
		int t = sc.nextInt();
		while(t != 0) {
			int N = sc.nextInt();
			int P = sc.nextInt();
			int[][] A = new int[N + 1][N + 1];
			int total = getGraderResponseForCount(1, 1, N, N);
			if(total != 0) {
				solveOpt4(A, total, N);	
			}
			printOutput(A);
			getGraderResponseForOutputCheck();
			t--;
		}
	}
	
	public static void solveOpt4(int[][] A, int total, int N) {
		LinkedList<Block> queue = new LinkedList<Block>();
		queue.add(new Block(1, 1, N, N, total));
		int currBlockSize = N - 1;
		while(currBlockSize != 0) {
			int noOfBlockSizes = (N + 1 - currBlockSize) * (N + 1 - currBlockSize);
			int i = 1, j = 1;
			while(noOfBlockSizes != 0) {
				int count = getGraderResponseForCount(i, j, currBlockSize, currBlockSize);
				if(count != 0) {
					queue.add(new Block(i, j, currBlockSize, currBlockSize, count));
				}
				noOfBlockSizes--;
				i++;
				j++;
			}
			currBlockSize--;
		}
		while(!queue.isEmpty()) {
			Block b = queue.poll();
			solveABlock(b.startRow, b.startColumn, N, A, b.countOfOnes, b.startColumn, b.endColumn);
		}
	}
	
	
	public static void solveABlock(int beginRow, int endRow, int N, int[][] A, int total, int beginColumn, int endColumn) {
		if(beginRow > endRow) {
			return;
		}
		if(beginRow == endRow) {
			int countInCurrentRow = total;
			if(countInCurrentRow != 0) {
				solveForRow(beginRow, countInCurrentRow, A, N);
			}
			return;
		}
		int midRow = endRow - 1;
		int countTillMidRow = getGraderResponseForCount(beginRow, beginColumn, midRow, endColumn);
		int countAfterMidRow = total - countTillMidRow;
		
		if(countTillMidRow != 0) {
			solveABlock(beginRow, midRow, N, A, countTillMidRow, beginColumn, endColumn);
		}
		if(countAfterMidRow != 0) {
			solveABlock(midRow + 1, endRow, N, A, countAfterMidRow, beginColumn, endColumn);
		}
	}
	
	
	
	
	
	
	
	public static int getInsert(int P) {
		int x = (int)(1 + Math.random() * 100);
		if(x <= P) {
			return 1;
		}
		return 0;
	}
	
	
	
	public static void solveOptTwo(int beginRow, int endRow, int N, int[][] A, int total) {
		if(beginRow > endRow) {
			return;
		}
		if(beginRow == endRow) {
			int countInCurrentRow = total;
			if(countInCurrentRow != 0) {
				solveForRow(beginRow, countInCurrentRow, A, N);
			}
			return;
		}
		int midRow = endRow - 1;
		int countTillMidRow = getGraderResponseForCount(beginRow, 1, midRow, N);
		int countAfterMidRow = total - countTillMidRow;
		
		if(countTillMidRow != 0) {
			solveOptTwo(beginRow, midRow, N, A, countTillMidRow);
		}
		if(countAfterMidRow != 0) {
			solveOptTwo(midRow + 1, endRow, N, A, countAfterMidRow);
		}
	}
	
	public static void solveForRow(int rowNumber, int onesInRow, int[][] A, int N) {
		int left = 2, right = N - 1;
		int i = rowNumber;
		while(left < right && onesInRow > 0) {
			int currOnesInRow = getGraderResponseForCount(i, left, i, right);
			if(currOnesInRow + 2 ==  onesInRow) {
				A[i][left - 1] = 1;
				A[i][right + 1] = 1;
				onesInRow = onesInRow - 2;
			} else if(currOnesInRow + 1 == onesInRow){
				int leftBinary = getGraderResponseForCount(i, left - 1, i, left - 1);
				if(leftBinary == 1) {
					A[i][left - 1] = 1;
				} else {
					A[i][right + 1] = 1;
				}
				onesInRow--;
			}
			left++;
			right--;
		}
		if(onesInRow != 0) {
			if(onesInRow == 2) {
				A[i][N/2] = 1;
				A[i][1 + N/2] = 1;
				onesInRow = onesInRow - 2;
			} else {
				int leftBinary = getGraderResponseForCount(i, N/2, i, N/2);
				if(leftBinary == 1) {
					A[i][N/2] = 1;
				} else {
					A[i][1 + N/2] = 1;
				}
				onesInRow--;
			}
		}
	}
	
	
	public static int getMaxNoOfOnePossible(int N, int P) {
		return (N * N * P) / 100;
	}
	
	public static int getGraderResponseForCount(int r1, int c1, int r2, int c2) {
		System.out.println(1 + " " + r1 + " " + c1 + " " + r2 + " " + c2);
		int response = sc.nextInt();
		if(response == -1) {
			System.exit(0);
		}
		return response;
	}
	
	public static void printOutput(int[][] A) {
		System.out.println(2);
		int N = A.length;
		for(int i = 1;i < N; i++) {
			for(int j = 1;j < N; j++) {
				System.out.print(A[i][j] + " ");	
			}
			System.out.println();
		}
	}
	
	public static int getGraderResponseForOutputCheck() {
		int response = sc.nextInt();
		if(response == -1) {
			System.exit(0);
		}
		return response;
	}
}

