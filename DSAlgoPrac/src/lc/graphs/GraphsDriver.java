package lc.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

	public static void main(String[] args) {
		int[][] prerequisites = {
				{1, 0}, {2, 0}, {3, 1}, {3, 2}
		};	
		
		
		System.out.println((new GraphsDriver()).canFinish(4, prerequisites));
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

}
