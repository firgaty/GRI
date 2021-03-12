import java.lang.annotation.Retention;
import java.util.LinkedList;

import graph.IGraph;

public class Community {
    int[] community;
    int[] sumDegree;
    int[] sumDegreeCommunity;
    long modularity;
    IGraph graph;

    public Community(IGraph g) {
        community = new int[g.verticesCount()];
        sumDegree = new int[g.verticesCount()];
        sumDegreeCommunity = new int[g.verticesCount()];

        graph = g;

        for (int i = 0; i < g.verticesCount(); i++) {
            community[i] = i;
            sumDegree[i] = graph.degree(i);
            sumDegreeCommunity[i] = 0;
        }

        modularity = initModularity();
    }

    public void move(int u, int to) {
        modularity += computeModularityDelta(u, to);
        int from = community[u];

        sumDegree[from] -= graph.degree(u);
        sumDegreeCommunity[from] -= 2 * sumCommunity(u);

        community[u] = to;

        sumDegree[to] += graph.degree(u);
        sumDegreeCommunity[to] += 2 * sumCommunity(u);
    }

    public double modularity() {
        return modularity / (2 * graph.verticesCount() * 2 * graph.verticesCount());
    }

    public long computeModularityDelta(int u, int to) {
        int c = community[u];
        long duc = sumDegreeCommunity[c];
        long dub = sumDegreeCommunity[to];
        long du = graph.degree(u);
        int m = graph.verticesCount();

        return 4L * m * (duc + dub) - 2 * du * (sumDegree[c] - sumDegree[to] + du);
    }

    private long initModularity() {
        long out = 0;

        for (int i = 0; i < graph.verticesCount(); i++) {
            out += -sumDegree[i];
        }

        return out;
    }

    private int sumCommunity(int u) {
        int sum = 0;
        int c = community[u];

        for (int i : graph.adjacencyListIter(u)) {
            if (community[i] == c) {
                sum += 1;
            }
        }

        return sum;
    }
}
