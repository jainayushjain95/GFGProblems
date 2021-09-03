package lld.snakeandladder;

import java.util.*;

public class SnakeAndLadderBoard {

	private int size;
	private List<Snake> snakes;
	private List<Ladder> ladders;
	private Map<String, Integer> playerPieces;
	
	
	public SnakeAndLadderBoard(int size) {
		this.size = size;
		this.snakes = new ArrayList<Snake>();
		this.ladders = new ArrayList<Ladder>();
		this.playerPieces = new HashMap<String, Integer>();
	}

	public int getSize() {
		return size;
	}

	public List<Snake> getSnakes() {
		return snakes;
	}

	public void setSnakes(List<Snake> snakes) {
		this.snakes = snakes;
	}

	public List<Ladder> getLadders() {
		return ladders;
	}

	public void setLadders(List<Ladder> ladders) {
		this.ladders = ladders;
	}

	public Map<String, Integer> getPlayerPieces() {
		return playerPieces;
	}

	public void setPlayerPieces(Map<String, Integer> playerPieces) {
		this.playerPieces = playerPieces;
	}
	
	
	
}