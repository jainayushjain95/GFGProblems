package lc.graphs;

import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

class Parent {
	int a;
	int b;

	public Parent() {

	}

	public Parent(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public Parent(int a) {
		this(a, 1);
		this.a = a;
	}

}

class Pair2 {
	int vertex;
	int dis;
	public Pair2(int vertex, int dis) {
		super();
		this.vertex = vertex;
		this.dis = dis;
	}
}

class ComparePair implements Comparator<Pair2>{

	@Override
	public int compare(Pair2 o1, Pair2 o2) {
		return o1.dis - o2.dis;
	}

}

public class Probs2 {

	boolean[] visited;
	int[] distance;

	int indexOffset;
	int[][] map;

	public static void main(String[] args) {
		int[][] board = {
				//				{-1,-1,-1,-1,-1,-1},
				//				{-1,-1,-1,-1,-1,-1},
				//				{-1,-1,-1,-1,-1,-1},
				//				{-1,35,-1,-1,13,-1},
				//				{-1,-1,-1,-1,-1,-1},
				//				{-1,15,-1,-1,-1,-1}

				{-1, -1, -1},
				{-1, 9, 8},
				{-1, 8, 9}
		};
		//		{{-1,-1},{-1,3}};
		System.out.println(new Probs2().snakesAndLadders(board));
	}
	
	public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
		int minCost = 0;
		boolean[] processed = new boolean[n];
		PriorityQueue<Pair2> pq = preparePQ(wells);
		int[][] adjacency = prepareAdjacency(n, pipes);
		
		while(!pq.isEmpty()) {
			Pair2 pair = pq.poll();
			int vertex = pair.vertex;
			processed[vertex] = true;
			
			for(int i = 0;i < n; i++) {
				if(adjacency[vertex][i] != -1 && !processed[i]) {
					if(wells[i] < adjacency[vertex][i]) {
						wells[i] = adjacency[vertex][i];
						
					}
				}
			}
			
		}
		
		return minCost;
    }
	
	public int[][] prepareAdjacency(int n, int[][] pipes) {
		int[][] adjacency = new int[n][n];
		
		for(int i = 0;i < n; i++) {
			for(int j = 0;j < n; j++) {
				adjacency[i][j] = -1;
			}
		}
		
		for(int i = 0;i < pipes.length; i++) {
			int edgeWeight = pipes[i][2];
			adjacency[pipes[i][0]][pipes[i][1]] = edgeWeight;
			adjacency[pipes[i][1]][pipes[i][0]] = edgeWeight;
		}
		return adjacency;
	}
	
	public PriorityQueue<Pair2> preparePQ(int[] wells) {
		PriorityQueue<Pair2> pq = new PriorityQueue<Pair2>(
					(pair1, pair2) -> pair1.dis - pair2.dis
				);
		return pq;
	}

	public int snakesAndLadders(int[][] board) {
		int count = 0, n = board.length;
		indexOffset = n - 1;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		boolean reached = false;
		prepareMap(n);

		while(!reached && !queue.isEmpty()) {
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				int currPosition = queue.poll();
				int[] destinationRange = getDestinationsRange(currPosition, n);
				for(int j = destinationRange[0]; j <= destinationRange[1]; j++) {
					if(j == n * n) {
						reached = true;
						break;
					} else if(j == currPosition) {

					} else {
						int[] mappedRowIndexAndColumnIndex = getMappedRowIndexAndColumnIndex(j, n);
						if(board[mappedRowIndexAndColumnIndex[0]][mappedRowIndexAndColumnIndex[1]] == -1) {
							queue.add(j);
						} else {
							queue.add(board[mappedRowIndexAndColumnIndex[0]][mappedRowIndexAndColumnIndex[1]]);
						}
					}
				}
				count++;
			}
		}

		return (reached) ? count : -1;
	}

	public void prepareMap(int n) {
		int total = n * n;
		map = new int[1 + n * n][2];
		int rowIndex = 0, colIndex = 0;

		for(int i = total;i >= 1;) {
			colIndex = 0;
			for(int j = 0;j < n; j++) {
				map[i - j][0] = rowIndex;
				map[i - j][1] = colIndex++;
			}
			rowIndex += 2;
			i = i - 2*n;
		}

		rowIndex = 1;
		for(int i = total - n;i >= 1;) {
			colIndex = n - 1;
			for(int j = 0;j < n; j++) {
				map[i - j][0] = rowIndex;
				map[i - j][1] = colIndex--;
			}
			rowIndex += 2;
			i = i - 2*n;
		}
	}

	public int[] getDestinationsRange(int currPosition, int n) {
		int[] destinationRange = new int[2];
		destinationRange[0] = currPosition + 1;
		destinationRange[1] = Math.min(currPosition + 6, n * n);

		if(destinationRange[0] > destinationRange[1]) {
			int swap = destinationRange[0];
			destinationRange[0] = destinationRange[1];
			destinationRange[1] = swap;
		}

		return destinationRange;
	}

	public int[] getMappedRowIndexAndColumnIndex(int currPosition, int n) {
		int[] mappedRowIndexAndColumnIndex = new int[2];
		mappedRowIndexAndColumnIndex[0] = map[currPosition][0];
		mappedRowIndexAndColumnIndex[1] = map[currPosition][1];

		return mappedRowIndexAndColumnIndex;
	}

	public int networkDelayTime(int[][] times, int n, int k) {
		int minTime = 0;
		int[] distance = getDistanceArray(n, k);
		PriorityQueue<Pair2> pq = getPQ(times, n, k);
		List<List<Pair2>> adjacenyMap = getAdjacency(times, n, k);

		while(!pq.isEmpty()) {
			Pair2 pair = pq.poll();
			List<Pair2> adjaceny = adjacenyMap.get(pair.vertex);
			for(Pair2 currPair : adjaceny) {
				int currDistance = distance[currPair.vertex];
				int newDistance = distance[pair.vertex] + currPair.dis;
				if(currDistance > newDistance) {
					currPair.dis = newDistance;
					distance[currPair.vertex] = newDistance;
					pq.add(new Pair2(currPair.vertex, Math.min(newDistance, currDistance)));
				}
			}
		}

		for(int x : distance) {
			if(x == Integer.MAX_VALUE) {
				minTime = -1;
				break;
			}
			minTime = Math.max(minTime, x);
		}

		return minTime;
	}

	public List<List<Pair2>> getAdjacency(int[][] times, int n, int k) {
		List<List<Pair2>> adjacenyMap = new ArrayList<List<Pair2>>();
		for(int i = 0;i < n; i++) {
			adjacenyMap.add(new ArrayList<Pair2>());
		}
		for(int i = 0;i < times.length; i++) {
			int vertex = times[i][0] - 1;
			adjacenyMap.get(vertex).add(new Pair2(times[i][1] - 1, times[i][2]));
		}

		return adjacenyMap;
	}

	public PriorityQueue<Pair2> getPQ(int[][] times, int n, int k) {
		PriorityQueue<Pair2> pq = new PriorityQueue<Pair2>(new ComparePair());
		pq.add(new Pair2(k - 1, 0));
		return pq;
	}

	public int[] getDistanceArray(int n, int k) {
		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[k - 1] = 0;
		return distance;
	}

	public int findCircleNum(int[][] isConnected) {
		int count = 0;
		visited = new boolean[isConnected.length];

		for(int i = 0; i < isConnected.length; i++) {
			if(!visited[i]) {
				count++;	
				triggerDFS(i, isConnected);
			}

		}

		return count;
	}

	public void triggerDFS(int index, int[][] isConnected) {
		for(int i = 0;i < isConnected[index].length; i++) {
			if(isConnected[index][i] == 1 && !visited[i]) {
				visited[i] = true;
				triggerDFS(i, isConnected);
			}
		}
	}

}
