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

        GraphParser gf = new GraphParser();
        // on considere le graphe non oriente
        IGraph g = gf.parse(fileName, nbNodes, false);
        int out = -1;

        

        switch (type) {
            case "triangles": {
                int u = Integer.parseInt(args[3]);
                
                if (u == -1) {
                    u = new ConnectCalculator().baseMaxConnectedNode(g);
                }
                
                Triangles t = new Triangles(g);
                out = t.triangles(u);
                System.out.println(out);
                break;
            }
            case "clust": {
                Cluster c = new Cluster();
                float[] out_array = c.clust(g);
                System.out.format("%.5f\n", out_array[0]);
                System.out.format("%.5f\n", out_array[1]);
                break;
            }
            case "k-coeur": {
                Coeur c = new Coeur();
                int[] out_array = c.kCoeur(g);
                System.out.println(out_array[0]);
                System.out.println(out_array[1]);
                break;
            }
            default: {
                return;
            }
        }

    }
}