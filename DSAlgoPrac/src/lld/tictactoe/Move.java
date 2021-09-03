package lld.tictactoe;

public class Move {

	char symbol;
	int rowIndex;
	int columnIndex;
	
	public Move(char symbol, int rowIndex, int columnIndex) {
		super();
		this.symbol = symbol;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	public boolean isValidMove(char[][] board, Player currentPlayer) {
		return (board[this.rowIndex][this.columnIndex] != 'O' 
				&& board[this.rowIndex][this.columnIndex] != 'X') 
				&& (this.symbol == currentPlayer.getPlayerSymbol());
	}
	
}
