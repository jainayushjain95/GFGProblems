package lc.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class DataMap {
	Map<Integer, List<Integer>> adjaceny;
	int[] indegrees;
	public DataMap(Map<Integer, List<Integer>> adjaceny, int[] indegrees) {
		super();
		this.adjaceny = adjaceny;
		this.indegrees = indegrees;
	}
}

public class CourseSchedule2 {

	public static void main(String[] args) {
		int[][] prerequisites = {
				{1, 0}
			};
		int[] topologicalSort = (new CourseSchedule2().findOrder(2, prerequisites));
		for(int x : topologicalSort) {
			System.out.println(x);	
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] topologicalSort = new int[numCourses];
		DataMap dataMap = getDataMap(numCourses, prerequisites);
		int[] indegrees = dataMap.indegrees;
		Map<Integer, List<Integer>> adjacenyMap = dataMap.adjaceny;
		Queue<Integer> queue = new LinkedList<Integer>();
		addZeroIndegreeVerticesToQueue(queue, indegrees);
		
		int index = 0;
		while(!queue.isEmpty()) {
			int vertex = queue.poll();
			topologicalSort[index++] = vertex;
			List<Integer> adjacents = adjacenyMap.get(vertex);
			if(adjacents != null) {
				for(int x : adjacents) {
					indegrees[x]--;
					if(indegrees[x] == 0) {
						queue.add(x);
					}
				}
			}
		}
		
		if(index < numCourses) {
			topologicalSort = new int[0];
		}
		
		return topologicalSort;
    }
	
	public void addZeroIndegreeVerticesToQueue(Queue<Integer> queue, int[] indegrees) {
		for(int i = 0;i < indegrees.length; i++) {
			if(indegrees[i] == 0) {
				queue.add(i);
			}
		}
	}
	
	public DataMap getDataMap(int numCourses, int[][] prerequisites) {
		int[] indegrees = new int[numCourses];
		Map<Integer, List<Integer>> adjaceny = new HashMap<Integer, List<Integer>>();
		
		for(int[] pre : prerequisites) {
			if(adjaceny.containsKey(pre[1])) {
				adjaceny.get(pre[1]).add(pre[0]);
			} else {
				List<Integer> adj = new ArrayList<Integer>();
				adjaceny.put(pre[1], adj);
				adj.add(pre[0]);
			}
			indegrees[pre[0]]++;
		}
		
		return new DataMap(adjaceny, indegrees);
	}
}
