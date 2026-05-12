import java.util.*;

public class GraphRep {
    public static void main(String[] args) {
        int V = 4;

        int[][] matrix = new int[V][V];

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    matrix[i][j] = 1;
                    adjList.get(i).add(j);
                }
            }
        }

        System.out.println("Adjacency Matrix:");
        for (int[] row : matrix) System.out.println(Arrays.toString(row));

        System.out.println("\nAdjacency List:");
        for (int i = 0; i < V; i++) System.out.println(i + " -> " + adjList.get(i));
    }
}