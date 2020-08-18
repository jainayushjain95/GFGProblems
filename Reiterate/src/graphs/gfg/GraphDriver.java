package graphs.gfg;

import java.util.Scanner;

public class GraphDriver {

	public static void main(String[] args) {
		Graph graph = new Graph(false, 9);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 2, 8);
		graph.addEdge(1, 0, 4);
		graph.addEdge(1, 2, 11);
		graph.addEdge(1, 3, 8);
		graph.addEdge(2, 0, 8);
		graph.addEdge(2, 1, 11);
		graph.addEdge(2, 4, 7);
		graph.addEdge(2, 5, 1);
		graph.addEdge(3, 1, 8);
		graph.addEdge(3, 4, 2);
		graph.addEdge(3, 6, 7);
		graph.addEdge(3, 7, 4);
		graph.addEdge(4, 2, 7);
		graph.addEdge(4, 3, 2);
		graph.addEdge(4, 5, 6);
		graph.addEdge(5, 2, 1);
		graph.addEdge(5, 4, 6);
		graph.addEdge(5, 7, 2);
		graph.addEdge(6, 3, 7);
		graph.addEdge(6, 7, 14);
		graph.addEdge(6, 8, 9);
		graph.addEdge(7, 5, 2);
		graph.addEdge(7, 3, 4);
		graph.addEdge(7, 6, 14);
		graph.addEdge(7, 8, 10);
		graph.addEdge(8, 6, 9);
		graph.addEdge(8, 7, 10);
		
		
		
		int[] disArray = graph.getSingleSourceShortestPathsDijkstrasAlgo(0);
		for(int x : disArray) {
			System.out.println(x);
		}
//		System.out.println(graph.detectCycleUsingBFSInDirectedGraphDriver());
	}	
}
