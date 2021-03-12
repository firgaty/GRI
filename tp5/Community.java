import graph.IGraph;

public class Community {
    int[][] communities;
    long modularity;

    public Community(IGraph g) {
        communities = new int[g.verticesCount()][1];
    }

    public void move(int from, int to) {

    }

    public long modularity() {
        return modularity;
    }

    private void init_modularity() {

    }

    private void update_modularity() {
        
    }
}
