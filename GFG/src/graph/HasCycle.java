package graph;

import java.util.*;

class Pair2 {
    int node;
    int parent;

    public Pair2(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}


public class HasCycle {
    public boolean[] visited;

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        return isCycleBFS(adj);
    }

    private boolean isCycleBFS(ArrayList<ArrayList<Integer>> adj) {
        visited = new boolean[adj.size()];
        for(int i = 0;i < adj.size(); i++) {
            if(!visited[i] && isCycleBFSHelper(i, adj)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCycleBFSHelper(int vertex, ArrayList<ArrayList<Integer>> adj) {
        visited[vertex] = true;
        Queue<Pair2> queue = new LinkedList<>();
        queue.add(new Pair2(vertex, -1));
        while(!queue.isEmpty()) {
            Pair2 pair = queue.poll();
            for(int adjacentNode : adj.get(pair.node)) {
                if(!visited[pair.node]) {
                    queue.add(new Pair2(adjacentNode, pair.node));
                    visited[adjacentNode] = true;
                } else if(adjacentNode != pair.parent) {
                    return true;
                }
            }
        }
        return false;
    }
}
