package lc.graphs;

import java.util.*;

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
	
	public static void main(String[] args) {
		int[][] times = {
				{2, 1, 1},
				{2, 3, 1},
				{3, 4, 1}
		};
		System.out.println(new Probs2().networkDelayTime(times, 4, 2));
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
