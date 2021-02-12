package graph;

import java.util.*;
import java.util.stream.IntStream;

public class Graph implements IGraph {

    private int[][] adjacencyLists;
    private boolean oriented;

    /**
     * Create new graph of n vertices
     * 
     * @param n Number of vertices
     */
    public Graph(int n) {
        this(n, false);
    }

    public Graph(int n, boolean oriented) {
        this.adjacencyLists = new int[n][];
        this.oriented = oriented;
    }

    public Graph(int n, boolean oriented, int nb_edge) {
        this.adjacencyLists = new int[n][];
        this.oriented = oriented;
    }

    @Override
    public int edgeCount() {
        int count = 0;
        for (int i = 0; i < adjacencyLists.length; i++) {
            if (adjacencyLists[i] != null) {
                count += adjacencyLists[i].length;
            }
        }
        return count;
    }

    @Override
    public int verticesCount() {
        return adjacencyLists.length;
    }

    @Override
    public int degree(int v) {
        int sortant;
        if (adjacencyLists[v] != null) {
            sortant = adjacencyLists[v].length;
        } else {
            sortant = 0;
        }

        if (!oriented)
            return sortant;

        int entrant = 0;

        for (int i = 0; i < adjacencyLists.length; i++) {
            if (i != v && adjacencyLists[i] != null) {
                for (int j = 0; j < adjacencyLists[i].length; j++) {
                    if (adjacencyLists[i][j] == v)
                        entrant++;
                }
            }
        }
        return sortant + entrant;
    }

    // @Override
    public int degreeMax() {
        int max = 0;
        for (int i = 0; i < adjacencyLists.length; i++) {
            int degree = degree(i);
            if (degree > max)
                max = degree;
        }
        return max;
    }

    // @Override
    public int distance(int u, int v) {
        if (u == v)
            return 0;
        // Si on ne trouve pas de chemin on renvoie le plus grand entier possible
        int dist = 2147483647;
        // initialisation
        Deque<Integer> File = new ArrayDeque<Integer>(adjacencyLists.length);
        int[] parents = new int[adjacencyLists.length];
        for (int i = 0; i < adjacencyLists.length; i++) {
            parents[i] = -1;
        }

        File.add(u);
        parents[u] = -2;

        while (!File.isEmpty()) {
            int s = File.poll();

            // Si c'est le sommet voulu on s'arrête et on calcule la distance parcourue
            if (s == v) {
                dist = 0;
                while (s != u) {
                    s = parents[s];
                    dist++;
                }
                return dist;
            }

            // Sinon on ajoute ses voisins a la File
            for (int j = 0; j < adjacencyLists[s].length; j++) {
                // Si pas marque on l'ajoute
                if (parents[adjacencyLists[s][j]] == -1) {
                    File.addLast(adjacencyLists[s][j]);
                    parents[adjacencyLists[s][j]] = s;
                }
            }
        }

        return dist;

    }

    @Override
    public int[] adjacencyList(int u) {
        return adjacencyLists[u];
    }

    public Iterable<Integer> adjacencyListIter(int u) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    int curr = 0;

                    public boolean hasNext() {
                        return curr < adjacencyLists[u].length;
                    }

                    public Integer next() {
                        if (curr >= adjacencyLists[u].length) {
                            throw new NoSuchElementException();
                        }
                        return adjacencyLists[u][curr++];
                    }
                    // UnsupportedOperationException
                };
            }
        };
    }

    // @Override
    public void addEdges(int u, int[] edges) {
        adjacencyLists[u] = edges;
    }

    public Iterator<Integer> iterator() {
        return IntStream.range(0, adjacencyLists.length).iterator();
    }

    @Override
    public String toString() {
        String s = "";

        for (int i = 0; i < adjacencyLists.length; i++) {
            if (adjacencyLists[i] != null)
                s += Integer.toString(i) + ": " + Arrays.toString(adjacencyLists[i]) + "\n";
        }

        return s;
    }
}