package graph.sweep;

import java.util.Arrays;
import java.util.LinkedList;

import graph.IGraph;

/**
 * EXO 2
 */
public class FourSweep implements IGraphSweep {
    @Override
    public int sweep(IGraph g, int u) {
        int[] parents = new int[g.verticesCount()];
        
        int v = bfs(g, u, parents);
        int w = bfs(g, v, parents);

        int distVW = dist(v, w, parents);
        int mid = nParent(w, distVW / 2, parents);

        int x = bfs(g, mid, parents);
        int y = bfs(g, x, parents);

        return dist(x, y, parents);
    }

    /**
     * BFS
     * 
     * @param g       GRaph
     * @param u       Start node
     * @param parents parents list
     * @return last_node
     */
    private int bfs(IGraph g, int u, int[] parents) {
        Arrays.fill(parents, -1);
        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.add(u);
        parents[u] = Integer.MAX_VALUE;
        int out = -1;

        while (!queue.isEmpty()) {
            int v = queue.removeFirst();
            out = v;

            if (g.adjacencyList(u) == null) {
                continue;
            }

            for (int w : g.adjacencyList(v)) {
                if (parents[w] < 0) {
                    parents[w] = v;
                    queue.addLast(w);
                }
            }
        }

        return out;
    }

    private int dist(int from, int to, int[] parents) {
        int i = 0;
        int u = to;
        while (u != from) {
            u = parents[u];
            i++;
        }
        return i;
    }

    private int nParent(int to, int n, int[] parents) {
        int u = to;

        for (int i = 0; i < n; i++) {
            u = parents[u];
        }

        return u;
    }

}
