package lc.sheet;

import java.util.LinkedList;
import java.util.Queue;

class PairWallsAndGates {
	int i;
	int j;
	public PairWallsAndGates(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

}

public class WallsAndGates {

	public int EMPTY_ROOM = 2147483647;
	public int WALL = -1;
	public int GATE = 0;
	public int[][] directions = {
			{1, 0}, {-1, 0}, {0, 1}, {0, -1}
	};

	public static void main(String[] args) {
		int[][] rooms = {
				{2147483647,-1,0,2147483647},
				{2147483647,2147483647,2147483647,-1},
				{2147483647,-1,2147483647,-1},
				{0,-1,2147483647,2147483647}	
		};
		WallsAndGates obj = new WallsAndGates();
		obj.wallsAndGates(rooms);
	}

	public void wallsAndGates(int[][] rooms) {
		Queue<PairWallsAndGates> queue = new LinkedList<PairWallsAndGates>();
		for(int i = 0;i < rooms.length; i++) {
			for(int j = 0;j < rooms[i].length; j++) {
				if(rooms[i][j] == GATE) {
					PairWallsAndGates pairWallsAndGates = new PairWallsAndGates(i, j);
					queue.add(pairWallsAndGates);
				}
			}
		}
		int steps = 0;
		while(!queue.isEmpty()) {
			steps++;
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				PairWallsAndGates pair = queue.poll();
				int ri = pair.i;
				int ci = pair.j;

				for(int j = 0;j < 4; j++) {
					int newRowIndex = ri + directions[j][0];
					int newColIndex = ci + directions[j][1];
					if(isCellWithinRange(rooms, newRowIndex, newColIndex) && rooms[newRowIndex][newColIndex] == EMPTY_ROOM) {
						rooms[newRowIndex][newColIndex] = steps;
						queue.add(new PairWallsAndGates(newRowIndex, newColIndex));
					}
				}
			}
		}
	}

	public void triggerBFS(int[][] rooms, int rowIndex, int columnIndex) {
		Queue<PairWallsAndGates> queue = new LinkedList<PairWallsAndGates>();
		boolean[][] visited = new boolean[rooms.length][rooms[0].length];
		PairWallsAndGates pairWallsAndGates = new PairWallsAndGates(rowIndex, columnIndex);
		queue.add(pairWallsAndGates);
		visited[rowIndex][columnIndex] = true;
		int steps = 0;
		boolean found = false;

		while(!found && !queue.isEmpty()) {
			steps++;
			int queueSize = queue.size();
			for(int i = 0;!found && i < queueSize; i++) {
				PairWallsAndGates pair = queue.poll();
				int ri = pair.i;
				int ci = pair.j;

				for(int j = 0;j < 4; j++) {
					int newRowIndex = ri + directions[j][0];
					int newColIndex = ci + directions[j][1];

					if(isCellWithinRange(rooms, newRowIndex, newColIndex) && rooms[newRowIndex][newColIndex] == GATE) {
						found = true;
						break;
					}

					if(isValidCell(visited, rooms, newRowIndex, newColIndex)) {
						PairWallsAndGates pair2 = new PairWallsAndGates(newRowIndex, newColIndex);
						queue.add(pair2);
						visited[newRowIndex][newColIndex] = true;
					} 

				}
			}
		}
		if(found) {
			rooms[rowIndex][columnIndex] = steps;
		}
	}

	public boolean isCellWithinRange(int[][] rooms, int rowIndex, int columnIndex) {
		return rowIndex >= 0
				&& columnIndex >= 0
				&& rowIndex < rooms.length
				&& columnIndex < rooms[0].length;		
	}

	public boolean isValidCell(boolean[][] visited, int[][] rooms, int rowIndex, int columnIndex) {
		return isCellWithinRange(rooms, rowIndex, columnIndex)
				&& !visited[rowIndex][columnIndex]
						&& rooms[rowIndex][columnIndex] != WALL;		
	}

}
