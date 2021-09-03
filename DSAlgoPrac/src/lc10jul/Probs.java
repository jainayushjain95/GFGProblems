package lc10jul;

import java.util.*;

class Pair {
	int rowIndex;
	int colIndex;
	public Pair(int rowIndex, int colIndex) {
		super();
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
	}
}

public class Probs {

	int[][] directions = {
			{0, 1}, {0, -1}, {-1, 0}, {1, 0}
	};

	public static void main(String[] args) {

		System.out.println(new Probs().countTriples(5));
	}

	public boolean sumGame(String num) {
		boolean aliceWins = false;
		int firstHalfSum = 0, secondHalfSum = 0, firstHalfEmptySlots = 0, secondHalfEmptySlots = 0;

		for(int i = 0;i < num.length()/2; i++) {
			if(num.charAt(i) == '?') {
				firstHalfEmptySlots++;
			} else {
				firstHalfSum += num.charAt(i) - '0';
			}
		}

		for(int i = num.length()/2;i < num.length(); i++) {
			if(num.charAt(i) == '?') {
				secondHalfEmptySlots++;
			} else {
				secondHalfSum += num.charAt(i) - '0';
			}
		}

		if(firstHalfEmptySlots + secondHalfEmptySlots == 0) {
			aliceWins = firstHalfSum != secondHalfSum;
		} else {
			boolean isAliceTurn = true;
			while(firstHalfEmptySlots + secondHalfEmptySlots != 0) {
				if(isAliceTurn) {
					if(firstHalfSum > secondHalfSum) {
						if(firstHalfEmptySlots > 0) {
							firstHalfSum += 9;
							firstHalfEmptySlots--;
						} else {
							secondHalfEmptySlots--;
						}
					} else {
						if(secondHalfEmptySlots > 0) {
							secondHalfSum += 9;
							secondHalfEmptySlots--;
						} else {
							firstHalfEmptySlots--;
						}
					}
				} else {
					if(firstHalfSum < secondHalfSum) {
						if(firstHalfEmptySlots == 0) {
							aliceWins = true;
							break;
						} 
						firstHalfSum += (secondHalfSum - firstHalfSum) % 10;
						firstHalfEmptySlots--;
					} else {
						if(secondHalfEmptySlots == 0) {
							if(firstHalfEmptySlots == 0) {
								aliceWins = true;
								break;
							} 	
						}
						secondHalfSum += (-secondHalfSum + firstHalfSum) % 10;
						secondHalfEmptySlots--;
					}
				}
				isAliceTurn = !isAliceTurn;
			}
		}

		return aliceWins || firstHalfSum != secondHalfSum;
	}

	public int nearestExit(char[][] maze, int[] entrance) {
		int count = 0;
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(entrance[0], entrance[1]));
		visited[entrance[0]][entrance[1]] = true;
		boolean flag = false;

		while(!queue.isEmpty() && !flag) {
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				Pair pair = queue.poll();
				for(int j = 0;j < directions.length; j++) {
					if(isValidMove(visited, maze, pair.rowIndex + directions[j][0], pair.colIndex + directions[j][1])) {
						if(isExit(maze, pair.rowIndex + directions[j][0], pair.colIndex + directions[j][1])) {
							flag = true;
							break;
						}
						visited[pair.rowIndex + directions[j][0]][pair.colIndex + directions[j][1]] = true;
						queue.add(new Pair(pair.rowIndex + directions[j][0], pair.colIndex + directions[j][1]));
					}
				}
			}
			count++;
		}

		return (flag) ? count : -1;
	}

	public boolean isExit(char[][] maze, int rowIndex, int colIndex) {
		return rowIndex + 1 == maze.length
				|| colIndex + 1 == maze[0].length
				|| rowIndex - 1 == -1
				|| colIndex - 1 == -1;
	}

	public boolean isValidMove(boolean[][] visited, char[][] maze, int rowIndex, int colIndex) {
		return rowIndex >= 0
				&& colIndex >= 0
				&& rowIndex < maze.length
				&& colIndex < maze[0].length
				&& !visited[rowIndex][colIndex]
						&& maze[rowIndex][colIndex] != '+';
	}

	public int countTriples(int n) {
		int count = 0;
		Map<Integer, Integer> sums = new HashMap<Integer, Integer>();

		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				int pow = (i * i) + (j * j);
				if(sums.containsKey(pow)) {
					sums.put(pow, sums.get(pow) + 1);
				} else {
					sums.put(pow, 1);
				}
			}
		}

		for(int i = 1; i <= n; i++) {
			if(sums.containsKey(i * i)) {
				count += sums.get(i * i);
			}
		}
		return count;
	}
}
