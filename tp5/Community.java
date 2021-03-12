import graph.IGraph;

public class Community {
    int[] community;
    int[] sumDegree;
    long modularity;
    long m2;
    IGraph graph;

    public Community(IGraph g) {
        community = new int[g.verticesCount()];
        sumDegree = new int[g.verticesCount()];

        graph = g;

        for (int i = 0; i < g.verticesCount(); i++) {
            community[i] = i;
            sumDegree[i] = graph.degree(i);
            m2 += sumDegree[i];
        }

        modularity = initModularity();
    }

    public void move(int u, int to) {
        modularity += computeModularityDelta(u, to);
        
        int from = community[u];
        
        community[u] = to;
        
        sumDegree[from] -= graph.degree(u);
        sumDegree[to] += graph.degree(u);
    }

    public long modularity() {
        return modularity;
    }

    public double modularityDouble() {
        return (double) modularity / (double) (m2 * m2);
    }

    public int communityOf(int u) {
        return community[u];
    }

    public long computeModularityDelta(int u, int to) {
        int b = community[u];
        long du = graph.degree(u);

        int duc = 0;
        int dub = 0;

        for (int i : graph.adjacencyListIter(u)) {
            if (community[i] == b) {
                dub += 1;
            }
            if (community[i] == to) {
                duc += 1;
            }
        }

        int sc = sumDegree[to];
        int sb = sumDegree[b];

        long delta = 2L * m2 * (duc - dub) - 2L * du * (sc - sb + du);

        return delta;
    }

    private long initModularity() {
        long out = 0;

        for (int i = 0; i < graph.verticesCount(); i++) {
            out -= sumDegree[i] * sumDegree[i];
        }

        return out;
    }
}
