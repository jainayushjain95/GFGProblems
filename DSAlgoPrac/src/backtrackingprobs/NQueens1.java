package backtrackingprobs;

import java.util.ArrayList;
import java.util.List;

public class NQueens1 {

	public static void main(String[] args) {
		(new NQueens1()).solveNQueens(5);
	}

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> solution = new ArrayList<List<String>>();
		int columnIndex = 0;
		while(columnIndex < n) {
			int[][] board = new int[n][n];
			boolean solve = solveNQueens(board, n, 0, columnIndex);
			if(solve) {
				List<String> data = new ArrayList<String>();
				solution.add(data);
				boolean flag = false;
				for(int i = 0;i < n; i++) {
					StringBuilder stringBuilder = new StringBuilder();
					for(int j = 0;j < n; j++) {
						char append = (board[i][j] == 0) ? '.' : 'Q';
						if(!flag && append == 'Q') {
							columnIndex = j + 1;
							flag = true;
						}
						stringBuilder.append(append);
					}
					data.add(stringBuilder.toString());
				}
			} else {
				columnIndex++;
			}
		}
		return solution;
	}
	
	

	public boolean solveNQueens(int[][] board, int n, int rowIndex, int columnIndex) {
		if(n == 0) {
			return true;
		}
		
		if(rowIndex == board.length) {
			return n == 0;
		}
		
		if(columnIndex == board.length) {
			columnIndex = 0;
			rowIndex++;
			if(rowIndex == board.length) {
				return n == 0;
			}
		}
		
		boolean isValid = isValidPlacement(board, rowIndex, columnIndex);
		if(isValid) {
			board[rowIndex][columnIndex] = 1;
			if(solveNQueens(board, n - 1, rowIndex, columnIndex + 1)) {
				return true;
			}
		}
		board[rowIndex][columnIndex] = 0;
		return solveNQueens(board, n, rowIndex, columnIndex + 1);
	}

	public boolean isValidPlacement(int[][] board, int rowIndex, int columnIndex) {
		return checkVertical(board, rowIndex, columnIndex)
				&& checkHorizontal(board, rowIndex, columnIndex)
				&& checkLeftToRightDiagonal(board, rowIndex, columnIndex)
				&& checkTopToBottomDiagonal(board, rowIndex, columnIndex);
	}

	public boolean checkVertical(int[][] board, int rowIndex, int columnIndex) {
		boolean isValid = true;
		for(int i = 0;i < board.length; i++) {
			if(board[i][columnIndex] == 1 && i != rowIndex) {
				isValid = false;
				break;
			}
		}
		return isValid;
	}

	public boolean checkHorizontal(int[][] board, int rowIndex, int columnIndex) {
		boolean isValid = true;
		for(int i = 0;i < board.length; i++) {
			if(board[rowIndex][i] == 1 && i != columnIndex) {
				isValid = false;
				break;
			}
		}
		return isValid;
	}

	public boolean checkLeftToRightDiagonal(int[][] board, int rowIndex, int columnIndex) {
		boolean isValid = true;
		int i = rowIndex + 1, j = columnIndex - 1;

		while(i < board.length && j >= 0 && isValid) {
			if(board[i][j] == 1) {
				isValid = false;
			}
			i++;
			j--;
		}

		i = rowIndex - 1;
		j = columnIndex + 1;

		while(i >= 0 && j < board.length && isValid) {
			if(board[i][j] == 1) {
				isValid = false;
			}
			i--;
			j++;
		}

		return isValid;
	}

	public boolean checkTopToBottomDiagonal(int[][] board, int rowIndex, int columnIndex) {
		boolean isValid = true;
		int i = rowIndex + 1, j = columnIndex + 1;

		while(i < board.length && j < board.length && isValid) {
			if(board[i][j] == 1) {
				isValid = false;
			}
			i++;
			j++;
		}

		i = rowIndex - 1;
		j = columnIndex - 1;

		while(i >= 0 && j >= 0 && isValid) {
			if(board[i][j] == 1) {
				isValid = false;
			}
			i--;
			j--;
		}

		return isValid;
	}
}
