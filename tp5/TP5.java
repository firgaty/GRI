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
            int u = Integer.parseInt(args[3]);
            int v = Integer.parseInt(args[4]);
            break;
        }
        case "delta12321": {
            int u = Integer.parseInt(args[3]);
            int v = Integer.parseInt(args[4]);
            break;
        }
        case "deplacements": {
            String fileDep = args[3];
            int nbNodesDep = Integer.parseInt(args[4]);
            try {
                Scanner s = new Scanner(new FileReader(args[3]));
            
                while (s.hasNext()) {
                    u = s.nextInt()
                    v = s.nextInt()
                    //delta(fileName, nbNodes, u, v);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
        case "phase": {
            break;
        }
        }

    }
}