import java.lang.Math;

import graph.IGraph;
import graph.parser.GraphParser;

public class TP4 {

    public int[] graphe_aleatoire(int[] degrees) {
        return null;
    }

    public int[] exemple() {
        return null;
    }

    public int[] racine(int n) {
        int out[] = new int[n];

        int count = 0;

        for (int i = 0; i < n; i++) {
            double sqrt = Math.sqrt((double) (i + 1));
            int degree = (int) Math.floor(sqrt);

            count += degree;
            out[i] = degree;
        }

        if (count % 2 != 0) {
            out[n - 1]++;
        }

        return out;
    }

    public int[] puissance(int n, double gamma) {
        return null;
    }

    public void print_edges(int[] edges) {
        for (int i = 0; i < edges.length; i += 2) {
            System.out.println(Integer.toString(edges[i]) + " " + Integer.toString(edges[i + 1]));
        }
    }

    public static void main(String args[]) {
        TP4 tp = new TP4();
        String type = args[0];
        int nbNodes = Integer.parseInt(args[1]);

        // GraphParser gf = new GraphParser();
        // on considere le graphe non oriente
        // IGraph g = gf.parse(fileName, nbNodes, false);
        int out = -1;

        switch (type) {
            case "racine": {
                tp.print_edges(tp.racine(nbNodes));
            }
            case "puissance": {
                double gamma = Double.parseDouble(args[2]);
                tp.print_edges(tp.puissance(nbNodes, gamma));
            }
            case "exemple": {
                tp.print_edges(tp.exemple());
            }
            default: {
                return;
            }
        }

    }
}