package graph.sweep;

import java.util.Arrays;
import java.util.LinkedList;

import graph.IGraph;

/**
 * EXO 4
 */
public class TakesKostersSweep implements IGraphSweep {
    @Override
    public int sweep(IGraph g, int u) {
        int diamlow = 0;

        int[] eccsup = new int[g.verticesCount()];
        Arrays.fill(eccsup, Integer.MAX_VALUE);

        return _sweep(g, eccsup, diamlow, u);
    }

    private int _sweep(IGraph g, int[] eccsup, int diamlow, int u) {
        if (eccsup[u] <= diamlow) {
            return diamlow;
        }

        int[] distance = new int[eccsup.length];

        boolean[] marked = bfs(g, u, distance);

        int[] ecc = max(distance);

        if (ecc[1] > diamlow) {
            diamlow = ecc[1];
        }

        for (int v = 0; v < marked.length; v++) {
            if (marked[v]) {
                int b = distance[v] + ecc[1];
                if (b < eccsup[v]) {
                    eccsup[v] = b;
                }
            }
        }

        return _sweep(g, eccsup, diamlow, ecc[0]);
    }

    private boolean[] bfs(IGraph g, int u, int[] distance) {
        boolean[] marked = new boolean[distance.length];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.add(u);
        marked[u] = true;

        while (!queue.isEmpty()) {
            int v = queue.removeFirst();

            if (g.adjacencyList(u) == null) {
                continue;
            }

            for (int w : g.adjacencyList(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distance[w] = distance[v] + 1;
                    queue.addLast(w);
                }
            }
        }

        return marked;
    }

    private int[] max(int[] array) {
        int[] max = new int[2];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max[1]) {
                max[0] = i;
                max[1] = array[i];
            }
        }

        return max;
    }
}
