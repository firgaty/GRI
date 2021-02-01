import graph.IGraph;
import graph.parser.GraphParser;
import graph.sweep.FourSweep;
import graph.sweep.IGraphSweep;
import graph.sweep.SumSweep;
import graph.sweep.TakesKostersSweep;
import graph.sweep.TwoSweep;
import graph.algorithms.ConnectCalculator;

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

        if (u == -1) {
            u = new ConnectCalculator().baseMaxConnectedNode(g);
        }

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
                sweep = new SumSweep();
                prefix += ">=";
                break;
            }
            case "diametre": {
                sweep = new TakesKostersSweep();
                prefix += "=";
                break;
            }
            case "all": {
                IGraphSweep[] sweeps = {
                    new TwoSweep(),
                    new FourSweep(),
                    new SumSweep(),
                    new TakesKostersSweep()
                };

                for (IGraphSweep s : sweeps) {
                    System.out.println(Integer.toString(s.sweep(g, u)));
                }

                return;
            }
            default: {
                return;
            }
        }

        int out = sweep.sweep(g, u);
        System.out.println(prefix + Integer.toString(out));

    }
}