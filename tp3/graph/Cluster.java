package graph;

import graph.IGraph;
import graph.Triangles;

public class Cluster {

    public float[] clust(IGraph g) {
        Triangles triangles = new Triangles(g);
        float[] out = new float[2];

        int verticesCount = g.verticesCount();
        float meanClustering = 0;
        int triangleCount = 0; // 3 * tri(G)
        int vCount = 0; // nv(G)

        for (int i = 0; i < verticesCount; i++) {
            int deg = g.degree(i);
            
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

        out[1] = (float) triangleCount / (float) (vCount);

        return out;
    }
}