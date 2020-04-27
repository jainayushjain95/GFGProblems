package backtracking.problems;

public class BKT1 {

	public static void main(String[] args) {
		int[][] board = {
		  {0, 3, 0, 0, 7, 0, 0, 0, 0},
		  {6, 0, 0, 1, 9, 5, 0, 0, 0},
		  {0, 9, 8, 0, 0, 0, 0, 6, 0},
		  {8, 0, 0, 0, 6, 0, 0, 0, 3},
		  {4, 0, 0, 8, 0, 3, 0, 0, 1},
		  {7, 0, 0, 0, 2, 0, 0, 0, 6},
		  {0, 6, 0, 0, 0, 0, 2, 8, 0},
		  {0, 0, 0, 4, 1, 9, 0, 0, 5},
		  {0, 0, 0, 0, 8, 0, 0, 7, 0},
		};
		boolean isSolved = canSudokuBeSolvedFromThisCell(board, 0, 0);
		for(int i = 0; i < 9; i++) {
			for(int j = 0;j < 9; j++) {
				System.out.print(board[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println(isSolved);
	}
	
	public static boolean canSudokuBeSolvedFromThisCell(int[][] board, int row, int column) {
		if(column == board.length) {
			column = 0;
			row++;
			if(row == board.length) {
				return true;
			}
		}
		if(board[row][column] != 0) {
			return canSudokuBeSolvedFromThisCell(board, row, column + 1);
		}
		for(int i = 1;i <= 9; i++) {
			if(isPossible(board, row, column, i)) {
				board[row][column] = i;
				if(canSudokuBeSolvedFromThisCell(board, row, column + 1)) {
					return true;
				}
				board[row][column] = 0;
			}
		}
		return false;
	}
	
	/*
	 * would check for row wise unique, column wise unique and grid wise unique
	 */
	public static boolean isPossible(int[][] board, int row, int column, int element) {
		boolean rowCheck = rowCheck(board, row, 0, element);
		boolean columnCheck = columnCheck(board, 0, column, element);
		boolean gridCheck = gridCheck(board, row, column, element);
		return rowCheck && columnCheck && gridCheck;
	}
	
	public static boolean columnCheck(int[][] board, int row, int column, int element) {
		if(row == board.length) {
			return true;
		}
		if(board[row][column] == element) {
			return false;
		}
		return columnCheck(board, row + 1, column, element);
	}
	
	public static boolean rowCheck(int[][] board, int row, int column, int element) {
		if(column == board.length) {
			return true;
		}
		if(board[row][column] == element) {
			return false;
		}
		return rowCheck(board, row, column + 1, element);
	}
	
	public static boolean gridCheck(int[][] board, int row, int column, int element) {
		int gridSize = (int) Math.sqrt(board.length);
		int rowIndex = gridSize * (row/gridSize);
		int columnIndex = gridSize * (column/gridSize);
		
		for(int i = 0;i < gridSize; i++) {
			for(int j = 0;j < gridSize; j++) {
				if(board[i + rowIndex][j + columnIndex] == element) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
