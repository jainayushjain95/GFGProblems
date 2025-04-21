package graph;

import java.util.*;

public class ReconstructItinerary {

    private Map<String, PriorityQueue<String>> adjacencyList_ri;
    private List<String> itinerary;

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(new ArrayList<>(Arrays.asList("MUC", "LHR")));
        tickets.add(new ArrayList<>(Arrays.asList("JFK", "MUC")));
        tickets.add(new ArrayList<>(Arrays.asList("SFO", "SJC")));
        tickets.add(new ArrayList<>(Arrays.asList("LHR", "SFO")));
        ReconstructItinerary r = new ReconstructItinerary();
        System.out.println(r.findItinerary(tickets));
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        initialize_ri(tickets);
        dfs_ri("JFK");
        reverse();
        return itinerary;
    }

    private void reverse() {
        int beginIndex = 0, endIndex = itinerary.size() - 1;
        while (beginIndex < endIndex) {
            String from = itinerary.get(beginIndex);
            itinerary.set(beginIndex, itinerary.get(endIndex));
            itinerary.set(endIndex, from);
            beginIndex++;
            endIndex--;
        }
    }

    private void dfs_ri(String from) {
        PriorityQueue<String> queue = adjacencyList_ri.get(from);
        while(queue != null && !queue.isEmpty()) {
            String next = queue.poll();
            dfs_ri(next);
        }
        itinerary.add(from);
    }

    private void initialize_ri(List<List<String>> tickets) {
        adjacencyList_ri = new HashMap<>();

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!adjacencyList_ri.containsKey(from)) {
                adjacencyList_ri.put(from, new PriorityQueue<>());
            }
            adjacencyList_ri.get(from).add(to);
        }

        itinerary = new ArrayList<>();
    }
}
