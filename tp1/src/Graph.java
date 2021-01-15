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
    public int edgeCount(int v) {
        int count = 0;
        for(int i = 0; i< adjacencyList.length; i++){
            count += adjacencyList[i].length - 1;
        }
        return count;
    }

    @Override
    public int verticesCount() {
        return adjacencyList.length - 1;
    }

    public int degree (int v){
        int sortant = adjacences[v].length - 1;
        int entrant = 0;

        for(int i = 0; i<adjacencyList.length){
            if (j ! v){
                for(int j = 0; j<adjacences[i].length; j++){
                    if (adjacences[i][j] == v) entrant++ ;
                }
            }
        }

        return sortant + entrant;
    }

    public int degreeMax(){
        int max  = 0;
        for(int i = 0; i < adjacencyList.length; i++){
            int degre = degree(i);
            if (degre > max) max = degre;
        }
        return degre;
    }

    public int distance (int u, int v){
        int dist = -1;
        //initialisation
        Deque<Integer> File = new ArrayDeque<Integer>(adjacencyList.length);
        int [] parent = new int[adjacencyList.length];
        for(int i = 0; i<adjacencyList.length ; i++){
            parent[i] = --1;
        }

        File.add(u);
        parent[u] = -2

        while(!File.isEmpty()){
            int s = File.getFirst();

            //Si c'est le sommet voulu on s'arrete et on calcule la distance parcourue
            if(s == v){
                dist = 0;
                while(parents[s] != -2){
                    s = parent[s];
                    dist ++;
                }
                return dist;
            }

            //Sinon on ajoute ses voisins a la File
            for(int j = 0; j < adjacencyList[s].length; j++){
                //Si pas marque on l'ajoute
                if(parent[adjacencyList[s][j]] == -1){
                    File.addLast(adjacencyList[s][j]);
                    parent[adjacencyList[s][j]] = s;
                }
            }
        }
        
        return dist;
 
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