package graph.algorithms;

import java.util.LinkedList;

import graph.IGraph;

public class ConnectCalculator implements IConnectedCalculator {
    @Override
    public int baseMaxConnectedNode(IGraph g) {
        boolean[] marked = new boolean[g.verticesCount()];

        int node = -1;
        int max = 0;

        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                int size = bfs(g, marked, i);

                if (size > max) {
                    max = size;
                    node = i;
                }
            }
        }

        return node;
    }

    private int bfs(IGraph g, boolean[] marked, int u) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int size = 0;

        queue.add(u);
        marked[u] = true;
        size++;

        while (!queue.isEmpty()) {
            int v = queue.removeFirst();

            if (g.adjacencyList(u) == null) {
                continue;
            }

            for (int w : g.adjacencyList(v)) {
                if (!marked[w]) {
                    size++;
                    marked[w] = true;
                    queue.addLast(w);
                }
            }
        }

        return size;
    }
}
