package graph;

import java.util.*;

class EdgeNDT {
    int destination;
    int weight;

    public EdgeNDT(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class NetworkDelayTime {
    List<EdgeNDT>[] adjacenyList;
    int[] signalReceivedTimes;
    PriorityQueue<EdgeNDT> pq_NDT;

    public static void main(String[] args) {
        int[][] edges = {
                {2, 1, 1},
                {4, 3, 59},
                {3, 2, 98},
                {3, 4, 22}
        };
        NetworkDelayTime sp = new NetworkDelayTime();
        System.out.println(sp.networkDelayTime(edges, 5, 5));
    }


    public int networkDelayTime(int[][] times, int n, int k) {
        return networkDelayTimeDijkstra(times, n, k);
    }

    private int networkDelayTimeDijkstra(int[][] times, int n, int k) {
        initialize_NDT_Dijkstra(times, n, k);
        pq_NDT.add(new EdgeNDT(k, 0));
        signalReceivedTimes[k] = 0;
        while (!pq_NDT.isEmpty()) {
            EdgeNDT currEdge = pq_NDT.poll();
            int currentTime = currEdge.weight;
            int currentNode = currEdge.destination;

            for(EdgeNDT edge : adjacenyList[currentNode]) {
                if(currentTime + edge.weight < signalReceivedTimes[edge.destination]) {
                    signalReceivedTimes[edge.destination] = currentTime + edge.weight;
                    pq_NDT.add(new EdgeNDT(edge.destination, currentTime + edge.weight));
                }
            }
        }
        int minTime = Integer.MIN_VALUE;
        for(int i = 1;i < signalReceivedTimes.length; i++) {
            minTime = Math.max(signalReceivedTimes[i], minTime);
        }
        return (minTime == Integer.MAX_VALUE) ? -1 : minTime;
    }

    private void initialize_NDT_Dijkstra(int[][] times, int n, int k) {
        adjacenyList = new List[n + 1];
        pq_NDT = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for(int i = 1;i <= n; i++) {
            adjacenyList[i] = new ArrayList<>();
        }
        for(int[] edge : times) {
            adjacenyList[edge[0]].add(new EdgeNDT(edge[1], edge[2]));
            pq_NDT.add(new EdgeNDT(edge[0], edge[1]));
        }
        signalReceivedTimes = new int[n + 1];
        Arrays.fill(signalReceivedTimes, Integer.MAX_VALUE);
    }

    private int networkDelayTimeBFS(int[][] times, int n, int k) {
        initialize_NDT_DFS(times, n);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);
        int minTime = Integer.MIN_VALUE;
        int currentTime = 0;
        signalReceivedTimes[k] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            currentTime = signalReceivedTimes[node];
            for(EdgeNDT edge : adjacenyList[node]) {
                if(currentTime + edge.weight < signalReceivedTimes[edge.destination]) {
                    signalReceivedTimes[edge.destination] = currentTime + edge.weight;
                    queue.add(edge.destination);
                }
            }
        }

        for(int i = 1;i < signalReceivedTimes.length; i++) {
            minTime = Math.max(signalReceivedTimes[i], minTime);
        }
        return (minTime == Integer.MAX_VALUE) ? -1 : minTime;
    }

    private int networkDelayTimeDFS(int[][] times, int n, int k) {
        initialize_NDT_DFS(times, n);
        int minTime = Integer.MIN_VALUE;
        networkDelayTimeDFSSolve(k, 0);
        for(int i = 1;i < signalReceivedTimes.length; i++) {
            minTime = Math.max(signalReceivedTimes[i], minTime);
        }
        return (minTime == Integer.MAX_VALUE) ? -1 : minTime;
    }

    private void networkDelayTimeDFSSolve(int sourceVertex, int currentTimeTaken) {
        if(signalReceivedTimes[sourceVertex] <= currentTimeTaken) {
            return;
        }
        signalReceivedTimes[sourceVertex] = currentTimeTaken;
        for(EdgeNDT edge : adjacenyList[sourceVertex]) {
            networkDelayTimeDFSSolve(edge.destination, currentTimeTaken + edge.weight);
        }
    }

    private void initialize_NDT_DFS(int[][] times, int n) {
        adjacenyList = new List[n + 1];
        for(int i = 1;i <= n; i++) {
            adjacenyList[i] = new ArrayList<>();
        }
        for(int[] edge : times) {
            adjacenyList[edge[0]].add(new EdgeNDT(edge[1], edge[2]));
        }
        signalReceivedTimes = new int[n + 1];
        Arrays.fill(signalReceivedTimes, Integer.MAX_VALUE);
    }

}
