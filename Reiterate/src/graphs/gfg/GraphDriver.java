package graphs.gfg;

public class GraphDriver {

	public static void main(String[] args) {
		Graph graph = new Graph(false, 5);
//		graph.addEdge(0, 1, 1);
//		graph.addEdge(1, 0, 1);
		graph.addEdge(0, 2, 1);
		graph.addEdge(2, 0, 1);
		graph.addEdge(2, 3, 1);
		graph.addEdge(3, 2, 1);
		graph.addEdge(1, 3, 1);
		graph.addEdge(3, 1, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(4, 1, 1);
//		graph.addEdge(4, 3, 1);
//		graph.addEdge(3, 4, 1);
		System.out.println(graph.detectCycleUsingDFSDriver(new int[graph.noOfVertices]));
	}	
}
