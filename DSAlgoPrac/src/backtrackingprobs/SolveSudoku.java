package backtrackingprobs;

public class SolveSudoku {

	public static void main(String[] args) {
		char[][] board = {
				{'5','3','.','.','7','.','.','.','.'},
				{'6','.','.','1','9','5','.','.','.'},
				{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'},
				{'4','.','.','8','.','3','.','.','1'},
				{'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'},
				{'.','.','.','4','1','9','.','.','5'},
				{'.','.','.','.','8','.','.','7','9'}
		};
		(new SolveSudoku()).solveSudoku(board);
	}
	
	public void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);
        for(int i = 0;i < 9; i++) {
        	for(int j = 0;j < 9; j++) {
            	System.out.print(board[i][j] + " ");
            }
        	System.out.println("\n");
        }
    }
	
	public boolean solveSudoku(char[][] board, int rowIndex, int columnIndex) {
        if(columnIndex == board.length) {
        	columnIndex = 0;
        	rowIndex++;
        	if(rowIndex == board.length) {
        		return true;
        	}
        }
        
        if(board[rowIndex][columnIndex] != '.') {
        	return solveSudoku(board, rowIndex, columnIndex + 1);
        }
        
        for(int i = 1;i <= 9; i++) {
        	char charTry = (char)(i + '0');
        	board[rowIndex][columnIndex] = charTry;
        	 if(isValidPlacement(board, rowIndex, columnIndex)) {
        		 if(solveSudoku(board, rowIndex, columnIndex + 1)) {
        			 return true;
        		 }
        	 }
        	 board[rowIndex][columnIndex] = '.';
        }
       
		return false;
    }
	
	public boolean isValidPlacement(char[][] board, int rowIndex, int columnIndex) {
		return isValidPlacementRowCheck(board, rowIndex, columnIndex)
				&& isValidPlacementColumnCheck(board, rowIndex, columnIndex)
				&& isValidPlacementBoxCheck(board, rowIndex, columnIndex);
	}

	public boolean isValidPlacementRowCheck(char[][] board, int rowIndex, int columnIndex) {
		boolean isValid = true;
		char placedChar = board[rowIndex][columnIndex];
		for(int i = 0; isValid && i < board.length; i++) {
			if(columnIndex != i && board[rowIndex][i] == placedChar) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	public boolean isValidPlacementColumnCheck(char[][] board, int rowIndex, int columnIndex) {
		boolean isValid = true;
		char placedChar = board[rowIndex][columnIndex];
		for(int i = 0; isValid && i < board.length; i++) {
			if(rowIndex != i && board[i][columnIndex] == placedChar) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	public boolean isValidPlacementBoxCheck(char[][] board, int rowIndex, int columnIndex) {
		
		boolean isValid = true;
		char placedChar = board[rowIndex][columnIndex];
		
		int boxRowIndex = 3 * (rowIndex/3);
		int boxColumnIndex = 3 * (columnIndex/3);
		
		for(int i = 0;i < 3; i++) {
			for(int j = 0;j < 3; j++) {
				if(i + boxRowIndex == rowIndex && j + boxColumnIndex == columnIndex) {
					continue;
				}
				char currChar = board[i + boxRowIndex][j + boxColumnIndex];
				if(currChar == placedChar) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}
	
	public void print(char[][] board) {
		for(int i = 0;i < 9; i++) {
        	for(int j = 0;j < 9; j++) {
            	System.out.print(board[i][j] + " ");
            }
        	System.out.println("\n");
        }
	}
}
