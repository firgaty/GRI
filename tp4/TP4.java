public class TP4 {

    public int[] graphe_aleatoire(int[] degrees) {
        return null;
    }

    public int[] exemple() {
        return null;
    }

    public int[] racine(int n) {
        return null;
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
        String type = args[0];
        String fileName = args[1];
        int nbNodes = Integer.parseInt(args[2]);

        GraphParser gf = new GraphParser();
        // on considere le graphe non oriente
        IGraph g = gf.parse(fileName, nbNodes, false);
        int out = -1;

        

        switch (type) {
            case "racine": {
                return 0;
                }
            case "puissance": {
                return 0;
            }
            case "exemple": {
                return 0;
            }
            default: {
                return;
            }
        }

    }
}