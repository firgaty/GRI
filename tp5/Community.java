import graph.IGraph;

public class Community {
    int[] community;
    int[] sc;
    long modularity;
    IGraph graph;

    public Community(IGraph g) {
        community = new int[g.verticesCount()];
        sc = new int[g.verticesCount()];

        graph = g;

        for (int i = 0; i < g.verticesCount(); i++) {
            community[i] = i;
            sc[i] = graph.degree(i);
        }

        modularity = initModularity();
    }

    public void move(int u, int to) {
        modularity += updateModularity(u, to);
        community[u] = to;
    }

    public long modularity() {
        return modularity / (2 * graph.verticesCount() * 2 * graph.verticesCount());
    }

    public long updateModularity(int u, int to) {
        int c = community[u];
        long duc = sumDegreeCommunity(c);
        long dub = sumDegreeCommunity(to);
        long du = graph.degree(u);
        int m = graph.verticesCount();

        return 4L * m * (duc + dub) - 2 * du * (sc[c] - sc[to] + du);
    }

    private long sumDegreeCommunity(int c) {
        return 0L;
    }

    private long initModularity() {
        long out = 0;

        for (int i = 0; i < graph.verticesCount(); i++) {
            out += -sc[i];
        }

        return out;
    }

    private void updateSc(int u) {

    }
}
