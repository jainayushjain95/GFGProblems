package lld.snakeandladder;

import java.util.*;

public class SnakeAndLadderService {

	private SnakeAndLadderBoard snakeAndLadderBoard;
	private int playersCount;
	private Queue<Player> players;
	private boolean isGameCompleted;
	
	private static final int DEFAULT_BOARD_SIZE = 100;
	private static final int INITIAL_PLAYER_POSITION = 0;
	
	public SnakeAndLadderService(int boardSize) {
		this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
		this.players = new LinkedList<Player>();
		this.isGameCompleted = false;
	}
	
	public SnakeAndLadderService() {
		this(DEFAULT_BOARD_SIZE);
	}

	public void setPlayers(List<Player> players) {
		Map<String, Integer> playerPieces = new HashMap<String, Integer>();
		for(Player player : players) {
			players.add(player);
			playerPieces.put(player.getId(), INITIAL_PLAYER_POSITION);
		}
		
		this.playersCount = players.size();
		this.snakeAndLadderBoard.setPlayerPieces(playerPieces);
	}
	
	public void setSnakes(List<Snake> snakes) {
		this.snakeAndLadderBoard.setSnakes(snakes);
	}
	
	public void setLadders(List<Ladder> ladders) {
		this.snakeAndLadderBoard.setLadders(ladders);
	}
	
	public void startGame() {
		while(true) {
			int rolledDiceValue = DiceService.rollDice();
			Player currentPlayer = players.poll();
			movePlayer(currentPlayer, rolledDiceValue);
			if(isGameCompleted) {
				System.out.println("Player " + currentPlayer.getName() + " has won");
				break;
			}
			players.add(currentPlayer);
		}
	}
	
	
	private void movePlayer(Player currentPlayer, int rolledDiceValue) {
		int currentPosition = snakeAndLadderBoard.getPlayerPieces().get(currentPlayer.getId());
		int newPosition = currentPosition + rolledDiceValue;
		int boardSize = snakeAndLadderBoard.getSize();
		
		if(newPosition <= boardSize) {
			newPosition = getNewPosition(newPosition);
		}
		
		this.isGameCompleted = newPosition == boardSize;
		
		snakeAndLadderBoard.getPlayerPieces().put(currentPlayer.getId(), newPosition);
		System.out.print("Player " + currentPlayer.getName() + ", rolled a dice with value " + rolledDiceValue);
		System.out.println(", and moved to: " + newPosition);
	}
	
	private int getNewPosition(int newPosition) {
		int position;
		while(true) {
			position = newPosition;
			for(Snake snake : snakeAndLadderBoard.getSnakes()) {
				if(snake.getStartPosition() == newPosition) {
					newPosition = snake.getEndPosition();
				}
			}
			
			for(Ladder ladder : snakeAndLadderBoard.getLadders()) {
				if(ladder.getStartPosition() == newPosition) {
					newPosition = ladder.getEndPosition();
				}
			}
			if(position == newPosition) {
				break;
			}
		}
		return newPosition;
	}
	
}
