import java.util.*;

public class Graph {
    private Map<Object, List<Edge>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(Object from, Object to, int energyCost) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, energyCost));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, energyCost));
    }

    public List<Edge> getEdges(Object node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public static class Edge {
        public static int getEnergyCost;
        Object to;
        int energyCost;

        public Edge(Object to, int energyCost) {
            this.to = to;
            this.energyCost = energyCost;
        }

        public Object getTo() {
            return to;
        }

    }
}
