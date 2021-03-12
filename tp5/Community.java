import graph.IGraph;

public class Community {
    int[][] communities;
    int[] communitySize;
    int[] verticeCommunity;
    long modularity;
    IGraph graph;

    public Community(IGraph g) {
        communities = new int[g.verticesCount()][1];
        communitySize = new int[g.verticesCount()];
        verticeCommunity = new int[g.verticesCount()];
        graph = g;
        
        for (int i = 0; i < g.verticesCount(); i++) {
            communities[i][0] = i;
            communitySize[i] = 1;
            verticeCommunity[i] = i;
        }
        
        modularity = initModularity();
    }

    public void move(int u, int from, int to) {
        
    }

    public long modularity() {
        return modularity / (2 * graph.verticesCount() * 2 * graph.verticesCount());
    }

    public long updateModularity(int u, int from, int to) {
        return 0L;
    }

    private long initModularity() {
        long out = 0;

        for (int i = 0; i < communities.length; i++) {
            out += -graph.degree(communities[i][0]);
        }

        return out;
    }
}
