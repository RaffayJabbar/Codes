import java.util.*;

public class EdmondsKarp {

    private static boolean bfs(int[][] residualCapacity, int source, int sink, int[] parent) {
        boolean[] visited = new boolean[residualCapacity.length];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < residualCapacity.length; v++) {
                if (!visited[v] && residualCapacity[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                    if (v == sink) return true;
                }
            }
        }
        return false;
    }

    public static int getMaximumFlow(int[][] capacity, int source, int sink) {
        int numVertices = capacity.length;
        int[][] residualCapacity = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(capacity[i], 0, residualCapacity[i], 0, numVertices);
        }

        int[] parent = new int[numVertices];
        int maxFlow = 0;


        while (bfs(residualCapacity, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;


            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualCapacity[u][v]);
            }


            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualCapacity[u][v] -= pathFlow;
                residualCapacity[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    public static void main(String[] args) {

        int numVertices = 7;
        int[][] capacity = new int[numVertices][numVertices];

        capacity[0][1] = 3;
        capacity[0][2] = 3;
        capacity[0][3] = 3;
        capacity[1][2] = 4;
        capacity[2][3] = 1;
        capacity[2][4] = 2;
        capacity[3][4] = 2;
        capacity[3][5] = 6;
        capacity[4][1] = 1;
        capacity[4][6] = 1;
        capacity[5][6] = 9;

        int maxFlow = getMaximumFlow(capacity, 0, 6);
        System.out.println("Maximal Flow: " + maxFlow);
    }
}