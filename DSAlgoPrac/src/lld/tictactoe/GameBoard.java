package lld.tictactoe;

import java.util.Scanner;

import lld.tictactoe.GameStatus.Status;

public class GameBoard {

	char[][] board;
	int boardSize;
	Player[] players;
	Scanner input;
	boolean firstPlayerTurn;
	Status currentStatus;

	public GameBoard(int boardSize, Player firstPlayer, Player secondPlayer) {
		this.boardSize = boardSize;
		this.board = new char[2 * boardSize - 1][2 * boardSize - 1];
		initializeBoard();
		initializePlayers(firstPlayer, secondPlayer);
		input = new Scanner(System.in); 
		currentStatus = Status.UNDECIDED;
		startGame();
	}

	private void initializePlayers(Player firstPlayer, Player secondPlayer) {
		players = new Player[2];
		players[0] = firstPlayer;
		players[1] = secondPlayer;
		firstPlayerTurn = true;
	}

	private void initializeBoard() {
		for(int i = 0;i < board.length; i++) {
			for(int j = 0;j < board.length; j++) {
				if(i % 2 != 0) {
					if(j % 2 == 0) {
						board[i][j] = '-';
					} else {
						board[i][j] = '+';
					}
				} else {
					if(j % 2 != 0) {
						board[i][j] = '|';
					}
				}
			}
		}
	}

	private void printBoard() {
		for(int i = 0;i < board.length; i++) {
			System.out.println();
			for(int j = 0;j < board.length; j++) {
				System.out.print(board[i][j]);
			}
		}
	}

	private void startGame() {
		while(currentStatus == Status.UNDECIDED) {
			Player currentPlayer = getCurrentPlayer();
			Move move = getMoveFromPlayer(currentPlayer);
			boolean makeMove = makeMove(move);
			while(!makeMove) {
				System.out.println("Invalid Move, try again");
				move = getMoveFromPlayer(currentPlayer);
				makeMove = makeMove(move);
			}
			firstPlayerTurn = !firstPlayerTurn;	
		}
	}

	private Status getGameStatus() {
		Status status = Status.UNDECIDED;
		
		return status;
	}
	
	private boolean makeMove(Move move) {
		Player currentPlayer = getCurrentPlayer();
		boolean isValidMove = move.isValidMove(board, currentPlayer);
		if(isValidMove) {
			board[move.rowIndex][move.columnIndex] = currentPlayer.getPlayerSymbol();
		}
		return isValidMove;
	}

	private Player getCurrentPlayer() {
		return (firstPlayerTurn) ? players[0] : players[1];
	}

	private Move getMoveFromPlayer(Player currentPlayer) {
		System.out.println("Player " + currentPlayer.getPlayerName() + " turn");
		System.out.println("Enter Player's symbol, RowIndex, ColumnIndex");
		Move move = new Move(input.next().charAt(0), input.nextInt(), input.nextInt());
		return getMoveFromPlayer(currentPlayer);
	}
}
