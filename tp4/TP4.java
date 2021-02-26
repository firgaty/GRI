public class TP4 {

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