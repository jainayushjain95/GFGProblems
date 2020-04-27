package backtracking.problems;

public class BKT2 {

	public static void main(String[] args) {
		int[][] board = {
				{1, 0, 0, 0},
				{1, 1, 0, 1},
				{0, 1, 0, 0}, 
				{1, 1, 1, 1}
		};
		int[][] pathBoard = new int[board.length][board.length];
		boolean isSolved = canRatReachCheezeCellFromThisCell(pathBoard, board, 0, 0);
		System.out.println(isSolved);
		for(int i = 0; i < board.length; i++) {
			for(int j = 0;j < board.length; j++) {
				System.out.print(pathBoard[i][j] + ", ");
			}
			System.out.println();
		}
	}

	public static boolean canRatReachCheezeCellFromThisCell(int[][] pathBoard, int[][] board, int row, int column) {
		if(row == board.length - 1 && column == board.length - 1) {
			pathBoard[row][column] = 1;
			return true;
		}
		if(isValidCell(board, row, column)) {
			pathBoard[row][column] = 1;
			if(canRatReachCheezeCellFromThisCell(pathBoard, board, row + 1, column)) {
				return true;
			} else if(canRatReachCheezeCellFromThisCell(pathBoard, board, row, column + 1)) {
				return true;
			}
			pathBoard[row][column] = 0;
		}
		return false;
	}

	public static boolean isValidCell(int[][] board, int row, int column) {
		return row < board.length && column < board.length && board[row][column] == 1;
	}

}
