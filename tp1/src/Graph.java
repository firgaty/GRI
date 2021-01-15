class Graph implements IGraph {

    private int[][] adjacences;

    /**
     * Create new graph of n vertices
     * @param n Number of vertices
     */
    public Graph(int n) {
        this.adjacences = new int[n][];
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
}