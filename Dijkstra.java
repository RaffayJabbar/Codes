import java.util.*;

public class Dijkstra {
    static class Node implements Comparable<Node> {
        int vertex, distance;
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    static class Edge {
        int target, weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void calculateShortestPath(Map<Integer, List<Edge>> graph, int source) {
        int numNodes = graph.size();
        int[] distances = new int[numNodes];
        int[] predecessors = new int[numNodes];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);
        distances[source] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));
        boolean[] visited = new boolean[numNodes];

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (visited[u]) continue;
            visited[u] = true;

            for (Edge edge : graph.getOrDefault(u, new ArrayList<>())) {
                int v = edge.target;
                int weight = edge.weight;

                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    predecessors[v] = u;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        for (int i = 0; i < numNodes; i++) {
            System.out.println("Node " + i + ": Distance = " + distances[i] + ", Path = " + getPathString(predecessors, i, source));
        }

        System.out.println("\nShortest Path Tree Edges:");
        for (int i = 0; i < numNodes; i++) {
            if (predecessors[i] != -1) {
                System.out.println(predecessors[i] + " -> " + i);
            }
        }
    }

    private static String getPathString(int[] predecessors, int target, int source) {
        if (target == source) return String.valueOf(source);
        List<Integer> path = new ArrayList<>();
        for (int v = target; v != -1; v = predecessors[v]) path.add(v);
        Collections.reverse(path);
        return path.toString().replace("[", "").replace("]", "").replace(", ", " -> ");
    }

    public static void main(String[] args) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i <= 8; i++) graph.put(i, new ArrayList<>());

        int[][] edges = {
                {0, 1, 4}, {0, 7, 8}, {1, 0, 4}, {1, 2, 8}, {1, 7, 11},
                {2, 1, 8}, {2, 3, 7}, {2, 5, 4}, {2, 8, 2}, {3, 2, 7},
                {3, 4, 9}, {3, 5, 14}, {4, 3, 9}, {4, 5, 10}, {5, 2, 4},
                {5, 3, 14}, {5, 4, 10}, {5, 6, 2}, {6, 5, 2}, {6, 7, 1},
                {6, 8, 6}, {7, 0, 8}, {7, 1, 11}, {7, 6, 1}, {7, 8, 7},
                {8, 2, 2}, {8, 6, 6}, {8, 7, 7}
        };

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
        }

        calculateShortestPath(graph, 0);
    }
}