interface IGraph {

    int edgeCount();
    int verticesCount();
    int degreeMax();
    int distance (int u, int v);


    int[] adjacencyList(int u);

    /**
     * Add edge list to adjacency lists
     * @param u Source vertice
     * @param edges Neighbours
     */
    void addEdges(int u, int[] edges);

    /**
     * Add edge v to adjacency list of u
     * 
     * Costly, prefer using addEdges
     * 
     * @param u Source
     * @param v Target
     */
    void addEdge(int u, int v);


    boolean hasEdge(int u, int v);
}