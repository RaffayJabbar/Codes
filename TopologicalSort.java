import java.util.*;

public class TopologicalSort {
    static void dfs(String u, Map<String, List<String>> adj, Set<String> visited, Stack<String> stack) {
        visited.add(u);
        if (adj.containsKey(u)) {
            for (String v : adj.get(u)) {
                if (!visited.contains(v)) dfs(v, adj, visited, stack);
            }
        }
        stack.push(u);
    }

    public static void main(String[] args) {
        Map<String, List<String>> adj = new HashMap<>();

        adj.put("m", Arrays.asList("q", "r", "x"));
        adj.put("n", Arrays.asList("q", "u", "o"));
        adj.put("p", Arrays.asList("o", "s", "z"));
        adj.put("o", Arrays.asList("r", "v", "s"));
        adj.put("s", Arrays.asList("r"));
        adj.put("u", Arrays.asList("t"));
        adj.put("r", Arrays.asList("u", "y"));
        adj.put("y", Arrays.asList("v"));
        adj.put("v", Arrays.asList("x", "w"));

        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        String[] nodes = {"m", "n", "p", "o", "s", "r", "y", "v", "x", "w", "z", "t", "q", "u"};

        for (String node : nodes) {
            if (!visited.contains(node)) dfs(node, adj, visited, stack);
        }

        System.out.print("Topological Order: ");
        while (!stack.isEmpty()) System.out.print(stack.pop() + " ");
    }
}