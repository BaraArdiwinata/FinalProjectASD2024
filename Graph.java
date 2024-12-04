import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(String from, String to, int energyCost) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, energyCost));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, energyCost));
    }

    public List<Edge> getEdges(String node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public static class Edge {
        String to;
        int energyCost;

        public Edge(String to, int energyCost) {
            this.to = to;
            this.energyCost = energyCost;
        }

        public String getTo() {
            return to;
        }

        public int getEnergyCost() {
            return energyCost;
        }
    }
}
