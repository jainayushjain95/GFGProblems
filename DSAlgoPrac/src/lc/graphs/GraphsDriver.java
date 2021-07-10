package lc.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class Pair {
	int i;
	int j;
	public Pair(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}
}

class VertexProbPair {
	int vertex;
	double prob;
	
	public VertexProbPair(int vertex, double prob) {
		super();
		this.vertex = vertex;
		this.prob = prob;
	}
}

class PairWithWeight {
	int i;
	int j;
	int weight;
	int index;

	public PairWithWeight(int i, int j, int weight, int index) {
		super();
		this.i = i;
		this.j = j;
		this.weight = weight;
		this.index = index;
	}
}

class PairWithWeightComp implements Comparator<PairWithWeight> {

	@Override
	public int compare(PairWithWeight o1, PairWithWeight o2) {
		return o1.weight - o2.weight;
	}

}

class VertexProbPairCompare implements Comparator<VertexProbPair> {

	@Override
	public int compare(VertexProbPair o1, VertexProbPair o2) {
		if(o2.prob - o1.prob > 0) {
			return 1;
		} else if(o2.prob - o1.prob < 0) {
			return -1;
		}
		return 0;
	}
	
}

public class GraphsDriver {

	private int COLOR_A = -1;
	private int COLOR_B = 1;
	private int NO_COLOR = 0;

	private char SAFE = 'S';
	private char O = 'O';
	private char X = 'X';

	private int WHITE_COLOR = 0;
	private int BLACK_COLOR = -1;
	private int GRAY_COLOR = 1;
	
	private int[] vertexColorMap;

	private int EMPTY_CELL = 0;
	private int FRESH_ORANGE_CELL = 1;
	private int ROTTEN_ORANGE_CELL = 2;

	private char LAND = '1';
	private char WATER = '0';
	private char PROHIBITED = '2';

	private int iLAND = 1;
	private int iWATER = 0;
	private int iPROHIBITED = 2;
	
	List<List<Integer>> allPaths;
	int destinationVertex;


	public static void main(String[] args) {
		String[] words = {"abc", "ab"};
		int[][] graph = {
				{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}
		};
		System.out.println((new GraphsDriver()).isBipartite(graph));
	}
	
	public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
		allPaths = new ArrayList<List<Integer>>();
		destinationVertex = graph.length - 1;
		
		List<Integer> path = new ArrayList<Integer>();
		path.add(0);
		
		allPathsSourceTarget2Solve(graph, allPaths, path, 0);
		
		return allPaths;
    }
	
	public void allPathsSourceTarget2Solve(int[][] graph, List<List<Integer>> allPaths, List<Integer> currPath, int sourceVertex) {
		if(sourceVertex == destinationVertex) {
			allPaths.add(new ArrayList<Integer>(currPath));
		}
		for(int i = 0;i < graph[sourceVertex].length; i++) {
			currPath.add(graph[sourceVertex][i]);
			allPathsSourceTarget2Solve(graph, allPaths, currPath, graph[sourceVertex][i]);
			currPath.remove(currPath.size() - 1);
		}
	}
	
	public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
//		https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
		double maxProbability = 0d;
		double[] probabilities = new double[n];
		probabilities[start] = 1;
		boolean[] visited = new boolean[n];
		visited[start] = true;
		Map<Integer, List<Integer>> adjaccency = new HashMap<Integer, List<Integer>>();
		
		for(int i = 0;i < edges.length; i++) {
			if(!adjaccency.containsKey(edges[i][0])) {
				adjaccency.put(edges[i][0], new ArrayList<Integer>());
			}
			if(!adjaccency.containsKey(edges[i][1])) {
				adjaccency.put(edges[i][1], new ArrayList<Integer>());
			}
			
			adjaccency.get(edges[i][0]).add(i);
			adjaccency.get(edges[i][1]).add(i);
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.comparingDouble(key -> -probabilities[key]));
		for(int i = 0;i < n; i++) {
			pq.add(i);
		}
		
		while(!pq.isEmpty()) {
			int currVertex = pq.poll();
			if(currVertex == end) {
				maxProbability = probabilities[currVertex];
				break;
			}
			if(adjaccency.containsKey(currVertex)) {
				List<Integer> adjacents = adjaccency.get(currVertex);
				for(int edgeIndex : adjacents) {
					int destinationVertex = edges[edgeIndex][1];
					if(!visited[destinationVertex]) {
						double currProb = probabilities[destinationVertex];
						double newProb = probabilities[currVertex] * succProb[destinationVertex];
					}
				}
			}
		}
		
		return maxProbability;
    }

	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		allPaths = new ArrayList<List<Integer>>();
		destinationVertex = graph.length - 1;
		List<Integer> path = new ArrayList<Integer>();
		path.add(0);
		allPathsSourceTargetSolve(0, graph, path); 
		
		return allPaths;
    }
	
	public void allPathsSourceTargetSolve(int sourceVertex, int[][] graph, List<Integer> path) {
		if(sourceVertex == destinationVertex) {
			allPaths.add(new ArrayList<Integer>(path));
			return;
		}
		
		for(int i = 0;i < graph[sourceVertex].length; i++) {
			path.add(graph[sourceVertex][i]);
			allPathsSourceTargetSolve(graph[sourceVertex][i], graph, path);
			path.remove(path.size() - 1);
		}
	}
	
	public boolean isBipartite(int[][] graph) {
		int noOfVertices = graph.length;
		int[] color = new int[noOfVertices];
		Set<Integer> visited = new HashSet<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean isBipartite = true;
		
		
		for(int i = 0;i < noOfVertices; i++) {
			if(visited.contains(i)) {
				continue;
			}
			
			queue.add(i);
			visited.add(i);
			color[i] = 1;
			
			while(isBipartite && !queue.isEmpty()) {
				int vertex = queue.poll();
				for(int j = 0;j < graph[vertex].length; j++) {
					if(color[graph[vertex][j]] == color[vertex]) {
						isBipartite = false;
						break;
					}
					
					if(!visited.contains(graph[vertex][j])) {
						color[graph[vertex][j]] = -color[vertex];
						visited.add(graph[vertex][j]);
						queue.add(graph[vertex][j]);
					}
				}
			}
			
		}
		
		return isBipartite;
    }

	public int[][] updateMatrix(int[][] mat) {
		return updateMatrixBFS(mat);
	}

	public int[][] updateMatrixDP(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		int[][] dis = new int[m][n];
		initializeUMDP(dis, mat, m, n);

		for(int i = 0;i < m; i++) {
			for(int j = 0;j < n; j++) {
				if(dis[i][j] != 0) {
					
				}
			}
		}

		return dis;
	}

	public int[][] updateMatrixBFS(int[][] mat) {
		int m = mat.length, n = mat[0].length;
		int[][] dis = new int[m][n];
		Queue<Pair> queue = new LinkedList<Pair>();
		initializeUMBFS(dis, queue, mat, m, n);
		int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

		while(!queue.isEmpty()) {
			Pair pair = queue.poll();
			for(int i = 0;i < 4; i++) {
				int newRowIndex = pair.i + directions[i][0];
				int newColumnIndex = pair.j + directions[i][1];

				if(isValidUpdateMatrixBFS(dis, mat, newRowIndex, newColumnIndex, pair.i, pair.j)) {
					queue.add(new Pair(newRowIndex, newColumnIndex));
					dis[newRowIndex][newColumnIndex] = 1 + dis[pair.i][pair.j];
				}
			}
		}

		return dis;
	}

	public void initializeUMBFS(int[][] dis, Queue<Pair> queue, int[][] mat, int m, int n) {
		for(int i = 0;i < m; i++) {
			for(int j = 0;j < n; j++) {
				if(mat[i][j] == 0) {
					queue.add(new Pair(i, j));
				} else {
					dis[i][j] = Integer.MAX_VALUE;
				}
			}	
		}
	}

	public void initializeUMDP(int[][] dis, int[][] mat, int m, int n) {
		for(int i = 0;i < m; i++) {
			for(int j = 0;j < n; j++) {
				if(mat[i][j] == 1) {
					dis[i][j] = Integer.MAX_VALUE;
				}
			}	
		}
	}

	public boolean isValidUpdateMatrixBFS(int[][] dis, int[][] mat, int rowIndex, int columnIndex, int i, int j) {
		return rowIndex >= 0
				&& columnIndex >= 0
				&& rowIndex < mat.length
				&& columnIndex < mat[rowIndex].length
				&& mat[rowIndex][columnIndex] == 1
				&& dis[rowIndex][columnIndex] > (1 + dis[i][j]);
	}




	public int openLock(String[] deadends, String target) {
		int steps = 0;
		Set<String> deadendsSet = getDeadEndsSet(deadends);
		Set<String> seenSet = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		String start = "0000";
		if(target.equals(start)) {
			return 0;
		}
		if(deadendsSet.contains(start)) {
			return -1;
		}

		queue.add(start);
		seenSet.add(start);
		boolean found = false;

		while(!found && !queue.isEmpty()) {
			steps++;
			int queueSize = queue.size();

			for(int i = 0;i < queueSize; i++) {
				String currString = queue.poll();

				for(int pos = 0;pos < 4; pos++) {
					int currPosInt = currString.charAt(pos) - '0';
					String addStr = currString.substring(0, pos) + ((currPosInt == 9) ? 0 : currPosInt + 1) + currString.substring(pos + 1);
					String subtractStr = currString.substring(0, pos) + ((currPosInt == 0) ? 9 : currPosInt - 1) + currString.substring(pos + 1);

					if(target.equals(addStr) || target.equals(subtractStr)) {
						found = true;
						break;
					}

					if(!seenSet.contains(addStr) && !deadendsSet.contains(addStr)) {
						queue.add(addStr);
						seenSet.add(addStr);
					}

					if(!seenSet.contains(subtractStr) && !deadendsSet.contains(subtractStr)) {
						queue.add(subtractStr);
						seenSet.add(subtractStr);
					}
				}
			}
		}

		return (found) ? steps : -1;
	}

	public Set<String> getDeadEndsSet(String[] deadends) {
		Set<String> deadendsSet = new HashSet<String>();
		for(String deadend : deadends) {
			deadendsSet.add(deadend);
		}
		return deadendsSet;
	}


	public int maxAreaOfIsland(int[][] grid) {
		int maxArea = 0;
		for(int i = 0;i < grid.length; i++) {
			for(int j = 0;j < grid[i].length; j++) {
				if(grid[i][j] == iLAND) {
					int size = getIslandBFS(grid, i, j);
					if(maxArea < size) {
						maxArea = size;
					}
				}
			}
		}
		return maxArea;
	}

	public int getIslandBFS(int[][] grid, int rowIndex, int columnIndex) {
		int size = 0;
		Queue<Pair> queue = new LinkedList<Pair>();
		Pair top = new Pair(rowIndex, columnIndex);
		queue.add(top);
		size++;
		grid[rowIndex][columnIndex] = iPROHIBITED;

		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				Pair pair = queue.poll();
				if(isValidCellMax(grid, pair.i + 1, pair.j)) {
					Pair peek = new Pair(pair.i + 1, pair.j);
					queue.add(peek);
					size++;
					grid[pair.i + 1][pair.j] = iPROHIBITED;
				}

				if(isValidCellMax(grid, pair.i - 1, pair.j)) {
					Pair peek = new Pair(pair.i - 1, pair.j);
					queue.add(peek);
					size++;
					grid[pair.i - 1][pair.j] = iPROHIBITED;
				}

				if(isValidCellMax(grid, pair.i, pair.j + 1)) {
					Pair peek = new Pair(pair.i, pair.j + 1);
					queue.add(peek);
					size++;
					grid[pair.i][pair.j + 1] = iPROHIBITED;
				}

				if(isValidCellMax(grid, pair.i, pair.j - 1)) {
					Pair peek = new Pair(pair.i, pair.j - 1);
					queue.add(peek);
					size++;
					grid[pair.i][pair.j - 1] = iPROHIBITED;
				}
			}
		}
		return size;
	}

	public boolean isValidCellMax(int[][] grid, int rowIndex, int columnIndex) {
		return rowIndex >= 0
				&& columnIndex >= 0
				&& rowIndex < grid.length
				&& columnIndex < grid[rowIndex].length
				&& grid[rowIndex][columnIndex] == iLAND;
	}

	public int minCostConnectPoints(int[][] points) {
		int minCost = 0;
		int verticesCount = points.length;
		if(verticesCount == 1) {
			return 0;
		}
		List<PairWithWeight> graph = new ArrayList<PairWithWeight>();
		PriorityQueue<PairWithWeight> priorityQueue = new PriorityQueue<PairWithWeight>(new PairWithWeightComp());
		prepareGraph(verticesCount, points, graph, priorityQueue);

		boolean[] visited = new boolean[verticesCount];
		visited[0] = true;

		while(!priorityQueue.isEmpty()) {
			PairWithWeight pairWithWeight = priorityQueue.poll();
			visited[pairWithWeight.index] = true; 
			for(int i = 0;i < verticesCount; i++) {
				if(visited[i]) {
					continue;
				}
				PairWithWeight currPairWithWeight = graph.get(i);
				int edgeWeight = getManhattanDistance(pairWithWeight.i, pairWithWeight.j, currPairWithWeight.i, currPairWithWeight.j);
				if(edgeWeight < currPairWithWeight.weight) {
					priorityQueue.remove(currPairWithWeight);
					currPairWithWeight.weight = Math.min(edgeWeight, currPairWithWeight.weight);
					priorityQueue.add(currPairWithWeight);	
				}
			}
		}

		for(PairWithWeight pairWithWeight : graph) {
			minCost += pairWithWeight.weight;
		}

		return minCost;
	}

	public static int getManhattanDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static void prepareGraph(int verticesCount, int[][] points, List<PairWithWeight> graph, PriorityQueue<PairWithWeight> priorityQueue) {
		for(int i = 0;i < verticesCount; i++) {
			int x = points[i][0];
			int y = points[i][1];
			int weight = (i == 0) ? 0 : Integer.MAX_VALUE;
			PairWithWeight pairWithWeight = new PairWithWeight(x, y, weight, i);
			graph.add(pairWithWeight);
			priorityQueue.add(pairWithWeight);
		}
	}

	public int orangesRotting(int[][] grid) {
		boolean[][] visitedROCell = new boolean[grid.length][grid[0].length];
		Queue<Pair> cellsQueue = new LinkedList<Pair>();
		int freshOrangesCount = 0;
		int totalOrangesCount = 0;

		for(int i = 0;i < grid.length; i++) {
			for(int j = 0;j < grid[i].length; j++) {
				if(grid[i][j] == FRESH_ORANGE_CELL) {
					freshOrangesCount++;
				}

				if(grid[i][j] != EMPTY_CELL) {
					totalOrangesCount++;
				}

				if(!visitedROCell[i][j] && grid[i][j] == ROTTEN_ORANGE_CELL) {
					cellsQueue.add(new Pair(i, j));
					visitedROCell[i][j] = true;
				}
			}
		}

		int minutes = 0;
		while(!cellsQueue.isEmpty()) {
			minutes++;
			int queueSize = cellsQueue.size();
			for(int i = 0;i < queueSize; i++) {
				Pair cell = cellsQueue.poll();
				if(isValidCellRO(grid, cell.i - 1, cell.j, visitedROCell)) {
					visitedROCell[cell.i - 1][cell.j] = true;
					grid[cell.i - 1][cell.j] = ROTTEN_ORANGE_CELL;
					cellsQueue.add(new Pair(cell.i - 1, cell.j));
					freshOrangesCount--;
				}

				if(isValidCellRO(grid, cell.i + 1, cell.j, visitedROCell)) {
					visitedROCell[cell.i + 1][cell.j] = true;
					grid[cell.i + 1][cell.j] = ROTTEN_ORANGE_CELL;
					cellsQueue.add(new Pair(cell.i + 1, cell.j));
					freshOrangesCount--;
				}

				if(isValidCellRO(grid, cell.i, cell.j - 1, visitedROCell)) {
					visitedROCell[cell.i][cell.j - 1] = true;
					grid[cell.i][cell.j - 1] = ROTTEN_ORANGE_CELL;
					cellsQueue.add(new Pair(cell.i, cell.j - 1));
					freshOrangesCount--;
				}

				if(isValidCellRO(grid, cell.i, cell.j + 1, visitedROCell)) {
					visitedROCell[cell.i][cell.j + 1] = true;
					grid[cell.i][cell.j + 1] = ROTTEN_ORANGE_CELL;
					cellsQueue.add(new Pair(cell.i, cell.j + 1));
					freshOrangesCount--;
				}
			}
		}

		int ans = 0;

		if(totalOrangesCount > 0) {
			if(freshOrangesCount > 0) {
				ans = -1;
			} else {
				ans = minutes - 1;
			}
		}

		return ans;
	}

	public boolean isValidCellRO(int[][] grid, int rowIndex, int columnIndex, boolean[][] visitedROCell) {
		return rowIndex >= 0
				&& columnIndex >= 0
				&& rowIndex < grid.length
				&& columnIndex < grid[rowIndex].length
				&& grid[rowIndex][columnIndex] == FRESH_ORANGE_CELL
				&& !visitedROCell[rowIndex][columnIndex];
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		boolean canFinish = true;
		Map<Integer, List<Integer>> adj = prepareAdjacencyLists(numCourses, prerequisites);
		Map<Integer, Integer> indegreesMap = prepareIndegreesMap(adj);
		int[] topologicalSort = new int[numCourses];
		Queue<Integer> setOfVerticesWithZeroInDegree = new LinkedList<Integer>();

		for(int i = 0;i < numCourses; i++) {
			if(!indegreesMap.containsKey(i)) {
				setOfVerticesWithZeroInDegree.add(i);
			}
		}

		canFinish = setOfVerticesWithZeroInDegree.size() > 0;
		int index = 0;
		if(canFinish) {
			while(!setOfVerticesWithZeroInDegree.isEmpty()) {
				int vertex = setOfVerticesWithZeroInDegree.poll();
				topologicalSort[index++] = vertex;
				List<Integer> adjacents = adj.get(vertex);
				for(int x : adjacents) {
					if(indegreesMap.containsKey(x)) {
						indegreesMap.put(x, indegreesMap.get(x) - 1);
						if(indegreesMap.get(x) == 0) {
							setOfVerticesWithZeroInDegree.add(x);
							indegreesMap.remove(x);
						}	
					}
				}
			}	
		}

		for(int x : topologicalSort) {
			System.out.println(x);
		}

		return canFinish && indegreesMap.size() == 0;
	}

	public boolean hasCycle(Map<Integer, List<Integer>> adj, int sourceVertex) {
		if(vertexColorMap[sourceVertex] == GRAY_COLOR) {
			return true;
		}
		vertexColorMap[sourceVertex] = GRAY_COLOR;
		List<Integer> adjacents = adj.get(sourceVertex);
		for(int i = 0;i < adjacents.size(); i++) {
			if(vertexColorMap[adjacents.get(i)] != BLACK_COLOR && hasCycle(adj, adjacents.get(i))) {
				return true;
			}
		}
		vertexColorMap[sourceVertex] = BLACK_COLOR;
		return false;
	}

	public Map<Integer, Integer> prepareIndegreesMap(Map<Integer, List<Integer>> adj) {
		Map<Integer, Integer> indegreesMap = new HashMap<Integer, Integer>();
		for(int i = 0;i < adj.size(); i++) {
			List<Integer> adjacents = adj.get(i);
			if(adjacents != null && adjacents.size() > 0) {
				for(int head : adjacents) {
					if(indegreesMap.containsKey(head)) {
						indegreesMap.put(head, indegreesMap.get(head) + 1);
					} else {
						indegreesMap.put(head, 1);
					}
				}	
			}
		}
		return indegreesMap;
	}

	public Map<Integer, List<Integer>> prepareAdjacencyLists(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adj = new HashMap<Integer, List<Integer>>();
		for(int i = 0;i < prerequisites.length; i++) {
			int head = prerequisites[i][0];
			int tail = prerequisites[i][1];
			if(adj.containsKey(tail)) {
				adj.get(tail).add(head);
			} else {
				List<Integer> list = new ArrayList<Integer>();
				list.add(head);
				adj.put(tail, list);
			}
		}
		for(int i = 0;i < numCourses; i++) {
			if(!adj.containsKey(i)) {
				adj.put(i, new ArrayList<Integer>());
			}
		}
		return adj;
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		int length = 1;
		Queue<String> queue = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();

		Set<String> map = new HashSet<String>();
		for(String s : wordList) {
			map.add(s);
		}

		boolean wordFound = false;

		queue.add(beginWord);
		visited.add(beginWord);

		while(!wordFound && !queue.isEmpty()) {
			length++;
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				String currentWord = queue.poll();
				for(int j = 0;j < currentWord.length(); j++) {
					for(char alpha = 'a'; alpha <= 'z'; alpha++) {
						String tempWord = "";
						if(j == 0) {
							tempWord = alpha + currentWord.substring(1);
						} else if(j == currentWord.length() - 1) {
							tempWord = currentWord.substring(0, currentWord.length() - 1) + alpha;
						} else {
							tempWord = currentWord.substring(0, j) + alpha + currentWord.substring(j + 1);
						}
						if(map.contains(tempWord) && !visited.contains(tempWord)) {
							visited.add(tempWord);
							queue.add(tempWord);
							if(tempWord.equals(endWord)) {
								wordFound = true;
								break;
							}
						}
					}
				}
			}
		}

		return (wordFound) ? length : 0;
	}

	public boolean isWordEdge(String word1, String word2) {
		int distance = 0;
		for(int i = 0; distance <= 2 && i < word1.length(); i++) {
			if(word1.charAt(i) != word2.charAt(i)) {
				distance++;
			}
		}
		return distance == 1;
	}

	public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
		vertexColorMap = new int[V];
		boolean hasCycle = false;
		for(int i = 0;!hasCycle && i < adj.size(); i++) {
			if(vertexColorMap[i] == WHITE_COLOR) {
				hasCycle = hasCycleFromThisVertex(i, adj);
			}
		}
		return hasCycle;
	}

	public boolean hasCycleFromThisVertex(int vertex, ArrayList<ArrayList<Integer>> adj) {
		if(vertexColorMap[vertex] == GRAY_COLOR) {
			return true;
		}
		vertexColorMap[vertex] = GRAY_COLOR;
		ArrayList<Integer> adjj = adj.get(vertex);
		for(int i = 0;i < adjj.size(); i++) {
			if(vertexColorMap[adjj.get(i)] != BLACK_COLOR && hasCycleFromThisVertex(adjj.get(i), adj)) {
				return true;
			} 
		}
		vertexColorMap[vertex] = BLACK_COLOR;
		return false;
	}


	public void surroundedRegions(char[][] board) {
		boolean[][] visited = new boolean[board.length][board[0].length];

		for(int i = 0;i < board[0].length; i++) {
			if(board[0][i] == O) {
				surroundedRegionsTriggerBFS(visited, board, 0, i);
			}
		}

		for(int i = 1;i < board.length; i++) {
			if(board[i][board[i].length - 1] == O) {
				surroundedRegionsTriggerBFS(visited, board, i, board[i].length - 1);
			}
		}

		for(int i = board[board.length - 1].length - 2;i >= 0; i--) {
			if(board[board.length - 1][i] == O) {
				surroundedRegionsTriggerBFS(visited, board, board.length - 1, i);
			}
		}

		for(int i = board.length - 2; i >= 1; i--) {
			if(board[i][0] == O) {
				surroundedRegionsTriggerBFS(visited, board, i, 0);
			}
		}

		for(int i = 1;i < board.length - 1; i++) {
			for(int j = 1;j < board[i].length - 1; j++) {
				if(board[i][j] == SAFE) {
					board[i][j] = O;
				} else if(board[i][j] == O) {
					board[i][j] = X;
				}
			}
		}
	}

	public void surroundedRegionsTriggerBFS(boolean[][] visited, char[][] board, int rowIndex, int columnIndex) {
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(rowIndex, columnIndex));
		visited[rowIndex][columnIndex] = true;

		while(!queue.isEmpty()) {
			Pair pair = queue.poll();
			if(isValidCellSurroundedRegions(visited, board, pair.i, pair.j - 1)) {
				board[pair.i][pair.j - 1] = SAFE;
				visited[pair.i][pair.j - 1] = true;
				queue.add(new Pair(pair.i, pair.j - 1));
			}

			if(isValidCellSurroundedRegions(visited, board, pair.i, pair.j + 1)) {
				board[pair.i][pair.j + 1] = SAFE;
				visited[pair.i][pair.j + 1] = true;
				queue.add(new Pair(pair.i, pair.j + 1));
			}

			if(isValidCellSurroundedRegions(visited, board, pair.i - 1, pair.j)) {
				board[pair.i - 1][pair.j] = SAFE;
				visited[pair.i - 1][pair.j] = true;
				queue.add(new Pair(pair.i - 1, pair.j));
			}

			if(isValidCellSurroundedRegions(visited, board, pair.i + 1, pair.j)) {
				board[pair.i + 1][pair.j] = SAFE;
				visited[pair.i + 1][pair.j] = true;
				queue.add(new Pair(pair.i + 1, pair.j));
			}
		}
	}

	public boolean isValidCellSurroundedRegions(boolean[][] visited, char[][] board, int rowIndex, int columnIndex) {
		return rowIndex > 0
				&& columnIndex > 0
				&& rowIndex < board.length - 1
				&& columnIndex < board[rowIndex].length - 1
				&& !visited[rowIndex][columnIndex]
						&& board[rowIndex][columnIndex] != X;		
	}


	public boolean isGraphBipartite(int[][] graph) {
		boolean isBipartite = true;
		boolean[] visited = new boolean[graph.length];
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] color = new int[graph.length];

		for(int i = 0; i < graph.length; i++) {
			if(visited[i]) {
				continue;
			}
			queue.add(i);
			color[i] = COLOR_A;
			visited[i] = true;

			while(isBipartite && !queue.isEmpty()) {
				int vertexIndex = queue.poll();
				int vertexColor = color[vertexIndex];

				for(int j = 0;j < graph[vertexIndex].length; j++) {
					int currentVertex = graph[vertexIndex][j];
					int currentVertexColor = color[currentVertex];

					if(!areOppositeColor(vertexColor, currentVertexColor)) {
						isBipartite = false;
						break;
					}

					if(!visited[currentVertex]) {
						queue.add(currentVertex);
						visited[currentVertex] = true;
						color[currentVertex] = getColor(vertexColor);
					}
				}

			}
		}

		return isBipartite;
	}

	private int getColor(int vertexColor) {
		return (vertexColor == COLOR_A) ? COLOR_B : COLOR_A;
	}

	private boolean areOppositeColor(int currentVertexColor, int vertexColor) {
		if(currentVertexColor == NO_COLOR || vertexColor == NO_COLOR) {
			return true;
		}
		return ((currentVertexColor == COLOR_A && vertexColor == COLOR_B)
				|| (currentVertexColor == COLOR_B && vertexColor == COLOR_A));
	}




	public int[][] floodFill(int[][] image, int row, int col, int newColor) {
		boolean[] visited = new boolean[image.length * image[0].length];
		Queue<Integer> queue = new LinkedList<Integer>();

		int startColor = image[row][col];

		image[row][col] = newColor;
		visited[getCellIndex(row, col, image)] = true;
		queue.add(getCellIndex(row, col, image));

		while(!queue.isEmpty()) {
			int cell = queue.poll();
			int rowIndex = getRowIndex(cell, image);
			int colIndex = getColIndex(cell, image);
			image[rowIndex][colIndex] = newColor;

			if(canBeAddedInQueue(rowIndex - 1, colIndex, image, startColor, visited)) {
				visited[getCellIndex(rowIndex - 1, colIndex, image)] = true;
				queue.add(getCellIndex(rowIndex - 1, colIndex, image));
			}

			if(canBeAddedInQueue(rowIndex, colIndex + 1, image, startColor, visited)) {
				visited[getCellIndex(rowIndex, colIndex + 1, image)] = true;
				queue.add(getCellIndex(rowIndex, colIndex + 1, image));
			}

			if(canBeAddedInQueue(rowIndex + 1, colIndex, image, startColor, visited)) {
				visited[getCellIndex(rowIndex + 1, colIndex, image)] = true;
				queue.add(getCellIndex(rowIndex + 1, colIndex, image));
			}


			if(canBeAddedInQueue(rowIndex, colIndex - 1, image, startColor, visited)) {
				visited[getCellIndex(rowIndex, colIndex - 1, image)] = true;
				queue.add(getCellIndex(rowIndex, colIndex - 1, image));
			}
		}

		return image;
	}

	public static boolean canBeAddedInQueue(int row, int column, int[][] matrix, int newColor, boolean[] visited) {
		return row >= 0
				&& column >= 0
				&& row < matrix.length
				&& column < matrix[row].length
				&& matrix[row][column] == newColor
				&& !visited[getCellIndex(row, column, matrix)];
	}

	public static int getRowIndex(int cell, int[][] matrix) {
		return cell/matrix[0].length;
	}

	public static int getColIndex(int cell, int[][] matrix) {
		return cell%matrix[0].length;
	}

	public static int getCellIndex(int row, int column, int[][] matrix) {
		return row * matrix[row].length + column;
	}


	public boolean canVisitAllRoomsDFS(List<List<Integer>> rooms) {
		boolean[] a = new boolean[3];
		Set<Integer> visited = new HashSet<Integer>();
		visited.add(0);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);

		while(!stack.isEmpty()) {
			int room = stack.pop();
			List<Integer> keys = rooms.get(room);
			for(int x : keys) {
				if(!visited.contains(x) && x != room) {
					stack.push(x);
					visited.add(x);
				}
			}
		}

		return rooms.size() == visited.size();
	}

	public ArrayList<Integer> bfsOfGraph(int sourceVertex, ArrayList<ArrayList<Integer>> adj) {
		ArrayList<Integer> bfs = new ArrayList<Integer>();
		int[] visited = new int[adj.size()];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(sourceVertex);
		visited[sourceVertex] = -1;

		while(!queue.isEmpty()) {
			int poppedVertex = queue.poll();
			ArrayList<Integer> adjacents = adj.get(poppedVertex);
			bfs.add(poppedVertex);
			for(int x : adjacents) {
				if(visited[x] != -1) {
					visited[x] = -1;
					queue.add(x);
				}
			}
		}

		return bfs;
	}

	public int numIslandsDFS(char[][] grid) {
		int isLandsCount = 0;
		for(int i = 0;i < grid.length; i++) {
			for(int j = 0;j < grid[0].length; j++) {
				if(grid[i][j] == LAND) {
					triggerBFSFromLand(grid, i, j);
					isLandsCount++;
				}
			}
		}
		return isLandsCount;
	}

	public void triggerDFSFromLand(char[][] grid, int rowIndex, int columnIndex) {
		if(!isValidCellNOI(grid, rowIndex, columnIndex)) {
			return;
		}
		grid[rowIndex][columnIndex] = PROHIBITED;
		triggerDFSFromLand(grid, rowIndex - 1, columnIndex);
		triggerDFSFromLand(grid, rowIndex + 1, columnIndex);
		triggerDFSFromLand(grid, rowIndex, columnIndex - 1);
		triggerDFSFromLand(grid, rowIndex, columnIndex + 1);
	}

	public int numIslands(char[][] grid) {
		int isLandsCount = 0;
		for(int i = 0;i < grid.length; i++) {
			for(int j = 0;j < grid[0].length; j++) {
				if(grid[i][j] == PROHIBITED || grid[i][j] == WATER) {
					continue;
				}
				triggerBFSFromLand(grid, i, j);
				isLandsCount++;
			}
		}
		return isLandsCount;
	}

	public void triggerBFSFromLand(char[][] grid, int rowIndex, int columnIndex) {
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(rowIndex, columnIndex));
		grid[rowIndex][columnIndex] = PROHIBITED;

		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			for(int i = 0;i < queueSize; i++) {
				Pair piece = queue.poll();
				rowIndex = piece.i;
				columnIndex = piece.j;
				if(isValidCellNOI(grid, rowIndex - 1, columnIndex)) {
					queue.add(new Pair(rowIndex - 1, columnIndex));
					grid[rowIndex - 1][columnIndex] = PROHIBITED;
				}
				if(isValidCellNOI(grid, rowIndex + 1, columnIndex)) {
					queue.add(new Pair(rowIndex + 1, columnIndex));
					grid[rowIndex + 1][columnIndex] = PROHIBITED;
				}
				if(isValidCellNOI(grid, rowIndex, columnIndex - 1)) {
					queue.add(new Pair(rowIndex, columnIndex - 1));
					grid[rowIndex][columnIndex - 1] = PROHIBITED;
				}
				if(isValidCellNOI(grid, rowIndex, columnIndex + 1)) {
					queue.add(new Pair(rowIndex, columnIndex + 1));
					grid[rowIndex][columnIndex + 1] = PROHIBITED;
				}
			}
		}
	}

	public boolean isValidCellNOI(char[][] grid, int rowIndex, int columnIndex) {
		return rowIndex >= 0
				&& columnIndex >= 0
				&& rowIndex < grid.length
				&& columnIndex < grid[rowIndex].length
				&& grid[rowIndex][columnIndex] == LAND;
	}



	public int numDistinctIslands(int[][] grid) {
		Set<String> signatures = new HashSet<String>();

		for(int i = 0;i < grid.length; i++) {
			for(int j = 0;j < grid[i].length; j++) {
				if(grid[i][j] == iPROHIBITED || grid[i][j] == iWATER) {
					continue;
				}
				StringBuilder signature = new StringBuilder();
				getIslandDFS('0', signature, grid, i, j);
				signatures.add(signature.toString());
			}
		}


		return signatures.size();
	}

	public void getIslandDFS(char c, StringBuilder signature, int[][] grid, int rowIndex, int columnIndex) {
		if(!isValidCellU(grid, rowIndex, columnIndex)) {
			return;
		}
		grid[rowIndex][columnIndex] = iPROHIBITED;
		signature.append(c);

		getIslandDFS('U', signature, grid, rowIndex - 1, columnIndex);
		getIslandDFS('D', signature, grid, rowIndex + 1, columnIndex);
		getIslandDFS('L', signature, grid, rowIndex, columnIndex - 1);
		getIslandDFS('R', signature, grid, rowIndex, columnIndex + 1);
		signature.append('0');
	}

	public boolean isValidCellU(int[][] grid, int rowIndex, int columnIndex) {
		return rowIndex >= 0
				&& columnIndex >= 0
				&& rowIndex < grid.length
				&& columnIndex < grid[rowIndex].length
				&& grid[rowIndex][columnIndex] == iLAND;
	}
	
	public String alienOrder(String[] words) {
		StringBuilder topologicalSOrt = new StringBuilder();
		Map<Character, List<Character>> adjacencyMap = prepareAdjMap(words);
		Set<Character> charSet = getCharSet(words);
		if(adjacencyMap != null) {
			Map<Character, Integer> indegreesMap = prepareIndegreesMap1(adjacencyMap);
			Queue<Character> setOfCharactersWithZeroIndegree = prepareQueue(charSet, indegreesMap, words);
			
			while(!setOfCharactersWithZeroIndegree.isEmpty()) {
				char c = setOfCharactersWithZeroIndegree.poll();
				topologicalSOrt.append(c);
				
				List<Character> adjacents = adjacencyMap.getOrDefault(c, new ArrayList<Character>());
				
				for(char c1 : adjacents) {
					if(indegreesMap.containsKey(c1)) {
						int indegree = indegreesMap.get(c1);
						if(indegree == 1) {
							indegreesMap.remove(c1);
							setOfCharactersWithZeroIndegree.add(c1);
						} else {
							indegreesMap.put(c1, indegree - 1);
						}
						
					}
				}
				
			}
				
		}
		return (topologicalSOrt.length() == charSet.size()) ? topologicalSOrt.toString() : "";
    }
	
	public Set<Character> getCharSet(String[] words) {
		Set<Character> set = new HashSet<Character>();
		for(String word : words) {
			for(int i = 0;i < word.length(); i++) {
				if(!set.contains(word.charAt(i))) {
					set.add(word.charAt(i));
				}
			}
		}
		return set;
	}
	
	public Queue<Character> prepareQueue(Set<Character> set, Map<Character, Integer> indegreesMap, String[] words) {
		Queue<Character> setOfCharactersWithZeroIndegree = new LinkedList<Character>();
		for(char c : set) {
			if(!indegreesMap.containsKey(c)) {
				setOfCharactersWithZeroIndegree.add(c);
			}
		}
		
		return setOfCharactersWithZeroIndegree;
	}
	
	public Map<Character, Integer> prepareIndegreesMap1(Map<Character, List<Character>> adjacencyMap){
		Map<Character, Integer> indegreesMap = new HashMap<Character, Integer>();
		
		for(char c : adjacencyMap.keySet()) {
			List<Character> adj = adjacencyMap.get(c);
			if(adj != null && adj.size() > 0) {
				for(char c1 : adj) {
					indegreesMap.put(c1, 1 + indegreesMap.getOrDefault(c1, 0));
				}
			}
		}
		
		return indegreesMap;
	}
	
	public Map<Character, List<Character>> prepareAdjMap(String[] words){
		Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
		if(words.length == 1) {
			map.put(words[0].charAt(0), new ArrayList<Character>());
		} else {
			for(int i = 0;i < words.length - 1; i++) {
				String word1 = words[i];
				String word2 = words[i + 1];
				if(word1.length() != word2.length() && word1.indexOf(word2) == 0) {
					map = null;
					break;
				}
				int beginIndex = 0;
				while(beginIndex < word1.length() && beginIndex < word2.length()) {
					if(word1.charAt(beginIndex) != word2.charAt(beginIndex)) {
						if(!map.containsKey(word1.charAt(beginIndex))) {
							map.put(word1.charAt(beginIndex), new ArrayList<Character>());
						}
						map.get(word1.charAt(beginIndex)).add(word2.charAt(beginIndex));
						break;
					}
					beginIndex++;
				}
				
			}	
		}
		return map;
	}

}
