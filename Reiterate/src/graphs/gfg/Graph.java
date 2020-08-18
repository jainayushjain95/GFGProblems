package graphs.gfg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


class VertexDistancePair {
	int vertex;
	int distance;
	public VertexDistancePair(int vertex, int distance) {
		super();
		this.vertex = vertex;
		this.distance = distance;
	}
}


class VertexDistancePairComparator implements Comparator<VertexDistancePair> {

	public int compare(VertexDistancePair o1, VertexDistancePair o2) {
		if(o1.distance > o2.distance) {
			return 1;
		} else if(o1.distance < o2.distance) {
			return -1;
		}
		return 0;
	}
}

public class Graph {

	int noOfVertices;
	
	class Edge {
		int endingVertex;
		int weight;
		public Edge(int endingVertex, int weight) {
			super();
			this.endingVertex = endingVertex;
			this.weight = weight;
		}
	}
	
	private List<List<Edge>> adjacencyList;
	private boolean isDirectedGraph;
	
	public Graph(boolean isDirectedGraph, int noOfVertices) {
		this.adjacencyList = new ArrayList<List<Edge>>();
		this.noOfVertices = noOfVertices;
		this.isDirectedGraph = isDirectedGraph;
		for(int i = 0;i < noOfVertices; i++) {
			this.adjacencyList.add(new ArrayList<Edge>());
		}
	}
	
	
	protected void addEdge(int startingVertex, int endingVertex, int weight) {
		Edge edge = new Edge(endingVertex, weight);
		List<Edge> edges = this.adjacencyList.get(startingVertex);
		edges.add(edge);
	}
	
	
	protected void breadthFirstTraversal(int startingVertex, int[] visited) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[startingVertex] = -1;
		queue.add(startingVertex);
		while(!queue.isEmpty()) {
			int currentVertex = queue.poll();
			System.out.println(currentVertex);
			List<Edge> edges = this.adjacencyList.get(currentVertex);
			for(Edge edge : edges) {
				if(visited[edge.endingVertex] == 0) {
					queue.add(edge.endingVertex);
					visited[edge.endingVertex] = -1;
				}
			}
		}
	}
	
	protected void printConnectedCompnonetsUsingBFS() {
		int[] visited = new int[this.noOfVertices];
		int count = 1;
		for(int i = 0;i < visited.length; i++) {
			if(visited[i] == 0) {
				System.out.println("\n\nPrinting " + count + " component");
				breadthFirstTraversal(i, visited);
				count++;
			}
		}
	}
	
	protected void depthFirstTraversal(int startingVertex, int[] visited) {
		System.out.println(startingVertex);
		visited[startingVertex] = -1;
		List<Edge> edges = this.adjacencyList.get(startingVertex);
		for(Edge edge : edges) {
			if(visited[edge.endingVertex] == 0) {
				depthFirstTraversal(edge.endingVertex, visited);
			}
		}
	}
	
	/*
	 * Uses BFS
	 * print Shortest Paths Distances In Undirected Unweighted Graph From Source Vertex
	 */
	protected void printShortestPathsDistancesInUndirectedUnweightedGraphFromSourceVertex(int startingVertex) {
		int[] visited = new int[this.noOfVertices];
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[startingVertex] = -1;
		queue.add(startingVertex);
		int[] disArray = new int[this.noOfVertices];
		for(int i = 0;i < disArray.length; i++) {
			disArray[i] = -1;
		}
		disArray[startingVertex] = 0;
		while(!queue.isEmpty()) {
			int currentVertex = queue.poll();
			List<Edge> edges = this.adjacencyList.get(currentVertex);
			for(Edge edge : edges) {
				if(visited[edge.endingVertex] == 0) {
					visited[edge.endingVertex] = -1;
					disArray[edge.endingVertex] = 1 + disArray[currentVertex];
					queue.add(edge.endingVertex);
				}
			}
		}
		
		for(int x : disArray) {
			System.out.println(x);
		}
	}
	
	/*
	 * Detect Cycle in a undirected graph Using DFS (can also be done by BFS)
	 */
	protected boolean detectCycleUsingDFS(int startingVertex, int[] visited, int parent) {
		visited[startingVertex] = -1;
		List<Edge> edges = this.adjacencyList.get(startingVertex);
		for(Edge edge : edges) {
			if(visited[edge.endingVertex] == 0) {
				if(detectCycleUsingDFS(edge.endingVertex, visited, startingVertex)) {
					return true;
				}
			} else if(edge.endingVertex != parent) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean detectCycleUsingDFSDriver(int[] visited) {
		boolean hasCycle = false;
		for(int i = 0; i < visited.length; i++) {
			if(visited[i] != -1) {
				boolean temp = detectCycleUsingDFS(i, visited, -1);
				if(temp) {
					hasCycle = true;
					break;
				}
			}
		}
		return hasCycle;
	}
	
	
	/*
	 * Detect Cycle in a directed graph using DFS
	 * youtube.com/watch?v=rKQaZuoUR4M
	 */
	protected boolean detectCycleUsingDFSInDirectedGraph(int startingVertex, int[] blackSet, int[] whiteSet, int[] graySet) {
		moveVertex(startingVertex, whiteSet, graySet);
		List<Edge> edges = this.adjacencyList.get(startingVertex);
		for(Edge edge : edges) {
			if(whiteSet[edge.endingVertex] == 1) {
				if(detectCycleUsingDFSInDirectedGraph(edge.endingVertex, blackSet, whiteSet, graySet)) {
					return true;
				}
			} else if(graySet[edge.endingVertex] == 1) {
				return true;
			}
		}
		moveVertex(startingVertex, graySet, blackSet);
		return false;
	}
	
	protected boolean detectCycleUsingDFSInDirectedGraphDriver() {
		boolean hasCycle = false;
		int[] blackSet = new int[this.noOfVertices];
		int[] whiteSet = new int[this.noOfVertices];
		
		for(int i = 0;i < this.noOfVertices; i++) {
			whiteSet[i] = 1;
		}
		
		int[] graySet = new int[this.noOfVertices];
		
		for(int i = 0;i < this.noOfVertices; i++) {
			if(blackSet[i] != 1) {
				if(detectCycleUsingDFSInDirectedGraph(i, blackSet, whiteSet, graySet)) {
					hasCycle = true;
					break;
				}
			}
		}
		return hasCycle;
	}
	
	public static void moveVertex(int vertex, int[] source, int[] destination) {
		source[vertex] = 0;
		destination[vertex] = 1;
	}
	
	
	/*
	 * Topological sorting in DAGs using DFS (Can also be done by BFS but DSF is a bit intuitive for Topological sorting )
	 */
	protected Stack<Integer> topologicalSortingUsingDFSDriver() {
		Stack<Integer> stack = new Stack<Integer>();
		int[] visited = new int[this.noOfVertices];
		for(int i = 0;i < this.noOfVertices; i++) {
			if(visited[i] == 0) {
				topologicalSortUsingDFS(i, stack, visited);
			}
		}
		return stack;
	}
	
	protected void topologicalSortUsingDFS(int startingVertex, Stack<Integer> stack, int[] visited) {
		visited[startingVertex] = -1;
		List<Edge> edges = this.adjacencyList.get(startingVertex);
		for(Edge edge : edges) {
			if(visited[edge.endingVertex] == 0) {
				topologicalSortUsingDFS(edge.endingVertex, stack, visited);
			}
		}
		stack.push(startingVertex);
	}
	
	protected void getSingleSourceShortestPathsInDAGsUsingTopSort(int startingVertex) {
		Stack<Integer> topSort = topologicalSortingUsingDFSDriver();
		int[] topSortArray = new int[topSort.size()];
		int i = 0;
		while(!topSort.isEmpty()) {
			topSortArray[i++] = topSort.pop();
		}
		int[] distancesArray = new int[topSortArray.length];
		for(i = 1; i < distancesArray.length; i++) {
			distancesArray[i] = Integer.MAX_VALUE;
		}
		
		for(i = 0;i < topSortArray.length; i++) {
			List<Edge> edges = this.adjacencyList.get(topSortArray[i]);
			for(Edge edge : edges) {
				distancesArray[edge.endingVertex] = Math.min(distancesArray[edge.endingVertex], distancesArray[topSortArray[i]] + edge.weight);
			}
		}
	}
	
	/*
	 * Dijkstra's Single Source Shortest Paths Algo
	 */
	protected int[] getSingleSourceShortestPathsDijkstrasAlgo(int startingVertex) {
		int[] distancesArray = new int[this.noOfVertices];
		PriorityQueue<VertexDistancePair> pq = new PriorityQueue<VertexDistancePair>(distancesArray.length, new VertexDistancePairComparator());
		setPriorityQueueAndDistancesArrayForDijktstras(startingVertex, pq, distancesArray);
		int[] finalized = new int[this.noOfVertices];
		while(!pq.isEmpty()) {
			VertexDistancePair vertexDistancePair = pq.poll();
			List<Edge> edges = this.adjacencyList.get(vertexDistancePair.vertex);
			for(Edge edge : edges) {
				int vertex = edge.endingVertex;
				if(finalized[vertex] != -1) {
					int distance = Math.min(distancesArray[vertex], distancesArray[vertexDistancePair.vertex] + edge.weight);
					distancesArray[vertex] = distance;
					pq.add(new VertexDistancePair(vertex, distance));	
				}
			}
			finalized[vertexDistancePair.vertex] = -1;
		}
		return distancesArray;
	}
	
	protected void setPriorityQueueAndDistancesArrayForDijktstras(int startingVertex, PriorityQueue<VertexDistancePair> pq, int[] distancesArray) {
		for(int i = 0; i < distancesArray.length; i++) {
			if(i == startingVertex) {
				continue;
			}
			distancesArray[i] = Integer.MAX_VALUE;
 			pq.add(new VertexDistancePair(i, Integer.MAX_VALUE));
		}
		pq.add(new VertexDistancePair(startingVertex, 0));
		distancesArray[startingVertex] = 0;
	}
}
