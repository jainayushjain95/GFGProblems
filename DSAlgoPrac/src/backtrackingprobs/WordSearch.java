package backtrackingprobs;

public class WordSearch {

	public static void main(String[] args) {
		char[][] board = {
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}
		};
		System.out.println((new WordSearch()).exist(board, "ABCB"));
	}

	public boolean exist(char[][] board, String word) {
		boolean exists = false;
		for(int i = 0;!exists && i < board.length; i++) {
			for(int j = 0;!exists && j < board[i].length; j++) {
				if(board[i][j] == word.charAt(0)) {
					boolean[][] visited = new boolean[board.length][board[0].length];
					exists = existSolve(visited, board, word, 0, i, j, word.charAt(0) + "");
				}
			}
		}
		return exists;
	}

	public boolean existSolve(boolean[][] visited, char[][] board, String word, int currentIndexInWord, int rowIndex, int columnIndex, String currWord) {
		
		if(rowIndex < 0 || columnIndex < 0 || rowIndex >= board.length || columnIndex >= board[rowIndex].length || visited[rowIndex][columnIndex]) {
			return false;
		}
		
		if(currentIndexInWord == word.length() - 1) {
			return isValidCell(visited, board, word, currentIndexInWord, rowIndex, columnIndex, currWord) && currWord.equals(word);
		}

		if(rowIndex >= board.length) {
			return currWord.equals(word);
		}

		if(columnIndex == board[rowIndex].length) {
			columnIndex = 0;
			rowIndex++;
			if(rowIndex == board.length) {
				return currWord.equals(word);
			}
		}
		
		if(isValidCell(visited, board, word, currentIndexInWord, rowIndex, columnIndex, currWord)) {
			visited[rowIndex][columnIndex] = true;
			if(existSolve(visited, board, word, currentIndexInWord + 1, rowIndex, columnIndex + 1, currWord + word.charAt(currentIndexInWord + 1))) {
				return true;
			}
			if(existSolve(visited, board, word, currentIndexInWord + 1, rowIndex, columnIndex - 1, currWord + word.charAt(currentIndexInWord + 1))) {
				return true;
			}
			if(existSolve(visited, board, word, currentIndexInWord + 1, rowIndex - 1, columnIndex, currWord + word.charAt(currentIndexInWord + 1))) {
				return true;
			}
			if(existSolve(visited, board, word, currentIndexInWord + 1, rowIndex + 1, columnIndex, currWord + word.charAt(currentIndexInWord + 1))) {
				return true;
			}
			visited[rowIndex][columnIndex] = false;
		}

		return false;
	}

	public boolean isValidCell(boolean[][] visited, char[][] board, String word, int currentIndexInWord, int rowIndex, int columnIndex, String currWord) {
		return rowIndex >= 0
				&& rowIndex < board.length
				&& columnIndex >= 0
				&& columnIndex < board[rowIndex].length
				&& !visited[rowIndex][columnIndex]
						&& word.charAt(currentIndexInWord) == board[rowIndex][columnIndex];
	}
}
