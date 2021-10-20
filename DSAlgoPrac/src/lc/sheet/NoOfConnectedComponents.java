package lc.sheet;

import java.util.*;

public class NoOfConnectedComponents {

	boolean[] visited;
	
	public static void main(String[] args) {
		int[][] edges = {
				{0, 1},
				{1, 2},
				{3, 4}
		};
		System.out.println((new NoOfConnectedComponents().countComponents(5, edges)));
	}
	
	public int countComponents(int n, int[][] edges) {
		int count = 0;
		Map<Integer, List<Integer>> adjacencyMap = getAdjacencyMap(count, edges);
		visited = new boolean[n];
		for(int i = 0;i < n; i++) {
			if(!visited[i]) {
				count++;
				triggerDFS(adjacencyMap, i);
			}
		}
		return count;
    }
	
	public void triggerDFS(Map<Integer, List<Integer>> adjacencyMap, int sourceVertex) {
		visited[sourceVertex] = true;
		List<Integer> adjacents = adjacencyMap.get(sourceVertex);
		if(adjacents != null) {
			for(int adjacent : adjacents) {
				if(!visited[adjacent]) {
					triggerDFS(adjacencyMap, adjacent);
				}
			}
		}
	}

	public Map<Integer, List<Integer>> getAdjacencyMap(int n, int[][] edges) {
		Map<Integer, List<Integer>> adjacencyMap = new HashMap<Integer, List<Integer>>();
		for(int[] edge : edges) {
			if(!adjacencyMap.containsKey(edge[0])) {
				adjacencyMap.put(edge[0], new ArrayList<Integer>());
			}
			adjacencyMap.get(edge[0]).add(edge[1]);
			
			if(!adjacencyMap.containsKey(edge[1])) {
				adjacencyMap.put(edge[1], new ArrayList<Integer>());
			}
			adjacencyMap.get(edge[1]).add(edge[0]);
		}
		return adjacencyMap;
	}
}
