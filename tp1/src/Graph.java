import java.util.*;

class Graph implements IGraph {

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

    @Override
    public int edgeCount() {
        int count = 0;
        for (int i = 0; i < adjacencyLists.length; i++) {
            count += adjacencyLists[i].length - 1;
        }
        return count;
    }

    @Override
    public int verticesCount() {
        return adjacencyLists.length - 1;
    }

    public int degree(int v) {
        int sortant = adjacencyLists[v].length - 1;
        int entrant = 0;

        for (int i = 0; i < adjacencyLists.length; i++) {
            if (i != v) {
                for (int j = 0; j < adjacencyLists[i].length; j++) {
                    if (adjacencyLists[i][j] == v)
                        entrant++;
                }
            }
        }
        if (oriented)
            return sortant + entrant;
        return (sortant + entrant) / 2;
    }

    @Override
    public int degreeMax() {
        int max = 0;
        for (int i = 0; i < adjacencyLists.length; i++) {
            int degre = degree(i);
            if (degre > max)
                max = degre;
        }
        return max;
    }

    @Override
    public int distance(int u, int v) {
        int dist = -1;
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

            // Si c'est le sommet voulu on s'arrete et on calcule la distance parcourue
            if (s == v) {
                dist = 0;
                while (parents[s] != -2) {
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

    @Override
    public void addEdges(int u, int[] edges) {
        adjacencyLists[u] = edges;
    }

    @Override
    public void addEdge(int u, int v) {
        addEdge(u, v, true);
    }

    private void addEdge(int u, int v, boolean checkOrientation) {
        if (adjacencyLists[u] == null) {
            adjacencyLists[u] = new int[] { v };
            return;
        }

        int[] newList = new int[adjacencyLists[u].length + 1];

        for (int i = 0; i < adjacencyLists[u].length; i++) {
            newList[i] = adjacencyLists[u][i];
        }

        newList[adjacencyLists[u].length] = v;

        adjacencyLists[u] = newList;

    }

    @Override
    public boolean hasEdge(int u, int v) {
        if (adjacencyLists[u] != null) {
            for (int e : adjacencyLists[u])
                if (e == v)
                    return true;
        }

        return false;
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

    public static void main(String[] args) {
        IGraph g = new Graph(3, false);

        g.addEdges(0, new int[] { 1, 2 });
        g.addEdge(1, 2);
        g.addEdge(2, 1);

        System.out.println(g.toString());
        System.out.println(g.degreeMax());
        System.out.println(g.distance(0, 2));
        System.out.println(g.edgeCount());
    }
}