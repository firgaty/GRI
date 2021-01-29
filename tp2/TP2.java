import graph.IGraph;
import graph.parser.GraphParser;
import graph.sweep.FourSweep;
import graph.sweep.IGraphSweep;
import graph.sweep.TakesKosterSweep;
import graph.sweep.TwoSweep;

public class TP2 {

    public static void main(String args[]) {
        String type = args[0];
        String fileName = args[1];
        int nbNodes = Integer.parseInt(args[2]);
        int u = Integer.parseInt(args[3]);

        GraphParser gf = new GraphParser();
        // on considere le graphe non oriente
        IGraph g = gf.parse(fileName, nbNodes, false);
        IGraphSweep sweep;
        String prefix = "diam";

        switch (type) {
            case "2-sweep": {
                sweep = new TwoSweep();
                prefix += ">=";
                break;
            }
            case "4-sweep": {
                sweep = new FourSweep();
                prefix += ">=";
                break;
            }
            case "sum-sweep": {
                sweep = new FourSweep();
                prefix += ">=";
                break;
            }
            case "diametre": {
                sweep = new TakesKosterSweep();
                prefix += "=";
                break;
            }
            default: {
                return;
            }
        }

        int out = sweep.sweep(g, u);
        System.out.println(prefix + Integer.toString(out));

    }
}