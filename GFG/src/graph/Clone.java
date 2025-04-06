package graph;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Clone {
    public static void main(String[] args) {
        Clone obj = new Clone();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        List<Node> neighbors1 = new ArrayList<>();
        neighbors1.add(n2);
        n1.neighbors = neighbors1;

        List<Node> neighbors2 = new ArrayList<>();
        neighbors2.add(n1);
        n2.neighbors = neighbors2;

        obj.cloneGraph(n1);

    }

    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Map<Integer, List<Integer>> adjacencyList = prepareAdjacenyCloned(node);

        return null;
    }

    private Map<Integer, List<Integer>> prepareAdjacenyCloned(Node node) {
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> processed = new HashSet<>();
        queue.add(node);
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            List<Integer> list = new ArrayList<>();
            adjacencyList.put(curr.val, list);
            for(Node neighbour : curr.neighbors) {
                list.add(neighbour.val);
                if(!processed.contains(neighbour)) {
                    queue.add(neighbour);
                }
            }
            processed.add(curr.val);
        }
        return adjacencyList;
    }
}
