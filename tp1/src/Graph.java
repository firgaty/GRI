import java.util.Arrays;

class Graph implements IGraph {

    private int[][] adjacencyLists;

    /**
     * Create new graph of n vertices
     * @param n Number of vertices
     */
    public Graph(int n) {
        this.adjacencyLists = new int[n][];
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
    }
    
    @Override
    public void addEdge(int u, int v) {
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
}