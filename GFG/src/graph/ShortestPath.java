package graph;

import java.util.*;

class PairSP {
    int distance;
    int node;

    public PairSP(int distance, int node) {
        this.node = node;
        this.distance = distance;
    }
}

public class ShortestPath {

    List<PairSP>[] adjacenyListSP;
    int[] distanceSP;
    int[] parentSP;
    PriorityQueue<PairSP> pqSP;

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2, 2}, {1, 4, 1},
                {2, 3, 4}, {2, 5, 5},
                {3, 5, 1}, {3, 4, 3}
        };
        ShortestPath sp = new ShortestPath();
        System.out.println(sp.shortestPath(5, 6, edges));
    }

    public List<Integer> shortestPath(int n, int m, int[][] edges) {
        initializeSF(n, m, edges);
        runDijkstra();
        List<Integer> path = new ArrayList<>();
        while(parentSP[n] != n) {
            path.add(n);
            n = parentSP[n];
        }
        path.add(1);
        Collections.reverse(path);
        return path;
    }

    private void runDijkstra() {
        pqSP.add(new PairSP(0, 1));
        while(!pqSP.isEmpty()) {
            PairSP curr = pqSP.poll();
            int node = curr.node;
            int distance = curr.distance;
            List<PairSP> neighbors = adjacenyListSP[node];
            for(PairSP neighbor : neighbors) {
                int newDistance = distance + neighbor.distance;
                if(newDistance < distanceSP[neighbor.node]) {
                    distanceSP[neighbor.node] = newDistance;
                    parentSP[neighbor.node] = node;
                    pqSP.add(new PairSP(newDistance, neighbor.node));
                }
            }
        }
    }

    private void initializeSF(int n, int m, int edges[][]) {
        adjacenyListSP = new ArrayList[n + 1];
        for (int i = 0; i < adjacenyListSP.length; i++) {
            adjacenyListSP[i] = new ArrayList<>();
        }
        for(int[] edge : edges) {
            adjacenyListSP[edge[0]].add(new PairSP(edge[2], edge[1]));
            adjacenyListSP[edge[1]].add(new PairSP(edge[2], edge[0]));
        }
        distanceSP = new int[n + 1];
        parentSP = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            distanceSP[i] = Integer.MAX_VALUE;
            parentSP[i] = i;
        }
        pqSP = new PriorityQueue<>(
                (x, y) -> x.distance - y.distance
        );

        distanceSP[1] = 0;
    }

}
