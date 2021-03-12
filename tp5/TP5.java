import graph.IGraph;
import graph.parser.GraphParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TP5 {

    public static void main(String args[]) {
        String type = args[0];
        String fileName = args[1];
        int nbNodes = Integer.parseInt(args[2]);

        GraphParser gf = new GraphParser();
        // on considere le graphe non oriente
        IGraph g = gf.parse(fileName, nbNodes, false);
        int out = -1;

        switch (type) {
        case "delta": {
            Community c = new Community(g);
            int u = Integer.parseInt(args[3]);
            int v = Integer.parseInt(args[4]);

            System.out.println(c.modularity());

            c.move(u, c.communityOf(v));

            System.out.println(c.modularity());
            break;
        }
        case "delta12321": {
            Community c = new Community(g);
            int u = Integer.parseInt(args[3]);
            int v = Integer.parseInt(args[4]);
            int cW = Integer.parseInt(args[5]);

            int cU = c.communityOf(u);
            int cV = c.communityOf(v);

            System.out.println(c.modularity());
            c.move(u, cW);
            System.out.println(c.modularity());
            c.move(v, cW);
            System.out.println(c.modularity());
            c.move(u, cU);
            System.out.println(c.modularity());
            c.move(v, cV);
            System.out.println(c.modularity());

            break;
        }
        case "deplacements": {
            Community c = new Community(g);
            String fileDep = args[3];
            int nbNodesDep = Integer.parseInt(args[4]);
            try {
                Scanner s = new Scanner(new FileReader(args[3]));

                while (s.hasNext()) {
                    int u = s.nextInt();
                    int v = s.nextInt();
                    c.move(u, v);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.format("%.5f\n", c.modularityDouble());
            break;
        }
        case "phase": {
            new Louvain(g);
            break;
        }
        }

    }
}