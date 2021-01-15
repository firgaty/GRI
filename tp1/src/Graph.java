import java.util.Arrays;

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
        // TODO
        return 0;
    }

    @Override
    public int verticesCount() {
        // TODO
        return 0;
    }

    @Override
    public int[] adjacencyList(int u) {
        // TODO
        return null;
    }

    @Override
    public void addEdges(int u, int[] edges) {
        adjacencyLists[u] = edges;

        // If not oriented add reverse edges if missing
        if (!oriented) {
            for (int v : edges) {
                if (!hasEdge(v, u, true)) {
                    addEdge(v, u, false);
                }
            }
        }
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

        if (!hasEdge(u, v, true)) {
            int[] newList = new int[adjacencyLists[u].length + 1];

            for (int i = 0; i < adjacencyLists[u].length; i++) {
                newList[i] = adjacencyLists[u][i];
            }

            newList[adjacencyLists[u].length] = v;

            adjacencyLists[u] = newList;
        }
        
        // If not oriented add reverse edge if missing
        if (checkOrientation && !oriented) {
            if (!hasEdge(v, u, true)) {
                addEdge(v, u, false);
            }
        }
    }

    @Override
    public boolean hasEdge(int u, int v) {
        return hasEdge(u, v, this.oriented);
    }

    private boolean hasEdge(int u, int v, boolean oriented) {
        if (adjacencyLists[u] != null) {
            for (int e : adjacencyLists[u])
                if (e == v)
                    return true;
        }

        if (!oriented && adjacencyLists[v] != null) {
            for (int e : adjacencyLists[v])
                if (e == u)
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
    }
}