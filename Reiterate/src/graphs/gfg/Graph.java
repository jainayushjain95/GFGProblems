package graphs.gfg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
	
}
