package graph;

import graph.IGraph;
import graph.Triangles;

public class Cluster {

    public float[] clust(IGraph g) {
        Triangles triangles = new Triangles(g);
        float[] out = new float[2];

        int verticesCount = g.verticesCount();
        float meanClustering = 0;
        int triangleCount = 0;
        int vCount = 0;

        for (int i = 0; i < verticesCount; i++) {
            int deg = g.degree(i);

            if (deg <= 0)
                continue;
            
            if (deg > 1) {
                int localTriangles = triangles.triangles(i);
                float localCluster = (float) (2 * localTriangles) / (float) (deg * (deg - 1));

                meanClustering += localCluster;
                triangleCount += localTriangles;
                vCount += deg * (deg - 1);
            }

        }

        meanClustering /= verticesCount;

        out[0] = meanClustering;

        // triangleCount = 3 * tri(G)
        // nv = nv(G)

        out[1] = triangleCount / (float) (vCount);

        return out;
    }
}