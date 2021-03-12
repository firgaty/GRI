import graph.IGraph;

public class Louvain {
    IGraph g;
    Community community;

    public Louvain(IGraph g) {
        community = new Community(g);
        this.g = g;

        while (phase())
            ;
    }

    private boolean phase() {
        boolean flag = false;

        for (int u = 0; u < g.verticesCount(); u++) {
            int minPos = g.verticesCount() + 1;
            long max = 0;

            for (int v : g.adjacencyListIter(u)) {
                int c = community.communityOf(v);
                long delta = community.computeModularityDelta(u, c);

                if (delta > max || delta == max && c < minPos) {
                    minPos = c;
                    max = delta;
                }
            }

            if (minPos < g.verticesCount() + 1) {
                community.move(u, minPos);
                flag = true;
            }
        }

        System.out.format("%.5f\n", community.modularityDouble());

        return flag;
    }

}