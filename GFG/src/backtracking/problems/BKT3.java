package backtracking.problems;

public class BKT3 {

	public static void main(String[] args) {
		int N = 10;
		int[][] board = new int[N][N];
		boolean isPossible = isNQueenProblemSolvable(N, board, 0, 0);
		
		System.out.println(isPossible);
		
		for(int i = 0;i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(board[i][j] + ", ");
			}
			System.out.println();
		}
	}

	public static boolean isNQueenProblemSolvable(int N, int[][] board, int row, int column) {
		if(N == 0) {
			return true;
		}
		
		if(row == board.length) {
			return N == 0;
		}
		
		if(column == board.length) {
			column = 0;
			row++;
			if(row == board.length) {
				return N == 0;
			}
		}
		
		if(board[row][column] != 0) {
			return isNQueenProblemSolvable(N, board, row, column + 1);
		}
		
		if(isPossibleToPlaceQueenHere(board, row, column)) {
			board[row][column] = 1;
			if(isNQueenProblemSolvable(N - 1, board, row + 1, 0)) {
				return true;
			}
		}
		board[row][column] = 0;
		return isNQueenProblemSolvable(N, board, row, column + 1);
	}
	
	
	public static boolean isPossibleToPlaceQueenHere(int[][] board, int row, int column) {
		boolean a = checkVertical(board, row, column);
		boolean b = checkHorizontal(board, row, column);
		boolean c = checkDiagonalOne(board, row, column);
		boolean d = checkDiagonalTwo(board, row, column);
		
		return a && b && c && d;
	}
	
	public static boolean checkVertical(int[][] board, int row, int column) {
		boolean isPossible = true;
		for(int i = 0;i < board.length; i++) {
			if(board[i][column] != 0) {
				isPossible = false;
				break;
			}
		}
		return isPossible;
	}
	
	public static boolean checkHorizontal(int[][] board, int row, int column) {
		boolean isPossible = true;
		for(int i = 0;i < board.length; i++) {
			if(board[row][i] != 0) {
				isPossible = false;
				break;
			}
		}
		return isPossible;
	}
	
	public static boolean checkDiagonalOne(int[][] board, int row, int column) {
		boolean isPossible = true;
		for(int i = row, j = column;i >= 0 && j >= 0; i--, j--) {
			if(board[i][j] != 0) {
				isPossible = false;
				break;
			}
		}
		for(int i = row + 1, j = column + 1;i < board.length  && j < board.length; i++, j++) {
			if(board[i][j] != 0) {
				isPossible = false;
				break;
			}
		}
		return isPossible;
	}
	
	public static boolean checkDiagonalTwo(int[][] board, int row, int column) {
		boolean isPossible = true;
		for(int i = row, j = column;i >= 0 && j < board.length; i--, j++) {
			if(board[i][j] != 0) {
				isPossible = false;
				break;
			}
		}
		for(int i = row + 1, j = column - 1;i < board.length  && j >= 0; i++, j--) {
			if(board[i][j] != 0) {
				isPossible = false;
				break;
			}
		}
		return isPossible;
	}
}
