import graph.IGraph;
import graph.Cluster;
import graph.Coeur;
import graph.Triangles;
import graph.parser.GraphParser;
import graph.algorithms.ConnectCalculator;
import java.awt.Point;

public class TP3 {

    public static void main(String args[]) {
        String type = args[0];
        String fileName = args[1];
        int nbNodes = Integer.parseInt(args[2]);
        int u = Integer.parseInt(args[3]);

        GraphParser gf = new GraphParser();
        // on considere le graphe non oriente
        IGraph g = gf.parse(fileName, nbNodes, false);
        int out = -1;

        if (u == -1) {
            u = new ConnectCalculator().baseMaxConnectedNode(g);
        }

        switch (type) {
            case "triangles": {
                Triangles t = new Triangles(g.verticesCount());
                out = t.find_triangles(g,u);
                System.out.println(out);
                return;
            }
            case "clust": {
                Cluster c = new Cluster();
                int[] out_array = c.clust(g);
                System.out.format("%.5f\n", out_array[0]);
                System.out.format("%.5f\n", out_array[1]);
            }
            case "k-coeur": {
                Coeur c = new Coeur();
                int[] out_array = c.kCoeur(g);
                System.out.println(out_array[0]);
                System.out.println(out_array[1]);

            }
            default: {
                return;
            }
        }

    }
}