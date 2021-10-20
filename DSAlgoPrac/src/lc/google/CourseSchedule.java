package lc.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule {

	int WHITE = 0;
	int GRAY = 1;
	int BLACK = 2;
	
	int[] colorsMap;
	
	public static void main(String[] args) {
		
	}
	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adjacency = getAdjacency(prerequisites);
		colorsMap = new int[numCourses];
		
		for(int i = 0;i < numCourses; i++) {
			if(colorsMap[i] == WHITE && hasCycle(adjacency, i)) {
				return false;
			}
		}
		
		return true;
    }
	
	public boolean hasCycle(Map<Integer, List<Integer>> adjacency, int vertex) {
		if(colorsMap[vertex] == GRAY) {
			return true;
		}
		
		colorsMap[vertex] = GRAY;
		List<Integer> adjacents = adjacency.get(vertex);
		
		if(adjacents != null) {
			for(int adjacent : adjacents) {
				if(colorsMap[adjacent] != BLACK && hasCycle(adjacency, adjacent)) {
					return true;
				}
			}	
		}
		colorsMap[vertex] = BLACK;
		return false;
	}
	
	public Map<Integer, List<Integer>> getAdjacency(int[][] prerequisites) {
		Map<Integer, List<Integer>> adjaceny = new HashMap<Integer, List<Integer>>();
		for(int[] pre : prerequisites) {
			if(adjaceny.containsKey(pre[1])) {
				adjaceny.get(pre[1]).add(pre[0]);
			} else {
				List<Integer> adj = new ArrayList<Integer>();
				adjaceny.put(pre[1], adj);
				adj.add(pre[0]);
			}
		}
		return adjaceny;
	}

}
