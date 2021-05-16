package backtrackingprobs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Pair {
	int a;
	int b;
	public Pair(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

}

public class MinimumKnightMoves {

	boolean[][] visitedMap;
	Queue<Pair> queue;
	int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
    int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };
	
	public static void main(String[] args) {
		System.out.println((new MinimumKnightMoves()).minKnightMoves(5, 5));
	}

	public int minKnightMoves(int x, int y) {
		queue = new LinkedList<Pair>();
		visitedMap = new boolean[601][601];
		return minKnightMovesSolve(x, y);
	}
	
	public boolean isFound(int x, int y, int tempX, int tempY) {
		return tempX == x && tempY == y;
	}
	
	public int minKnightMovesSolve(int x, int y) {
		int minMoves = 0;
		boolean found = false;
		if(x != 0 || y != 0) {
			addToVisited(0, 0);
			queue.add(new Pair(0, 0));
			while(!found && !queue.isEmpty()) {
				minMoves++;
				int queueSize = queue.size();
				
				for(int i = 0;i < queueSize; i++) {
					Pair currPair = queue.poll();
					for(int j = 0; j < 8; j++) {
						int newX = currPair.a + dx[j];
						int newY = currPair.b + dy[j];
						if(isFound(newX, newY, x, y)) {
							found = true;
							break;
						}
						if(!isVisited(newX, newY)) {
							addToVisited(newX, newY);
							queue.add(new Pair(newX, newY));
						}
					}
				}
				
			}
		}
		
		return minMoves;
	}
	
	public void addToVisited(int x, int y) {
		visitedMap[x + 300][y + 300] = true;
	}
	
	public boolean isVisited(int x, int y) {
		return visitedMap[x + 300][y + 300];
	}
	
}