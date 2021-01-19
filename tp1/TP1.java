public class TP1 {

    public static void main(String args[]) {
        String fileName = args[0];
        int nbNodes = Integer.parseInt(args[1]);
        int firstNode = Integer.parseInt(args[2]);
        int secondNode = Integer.parseInt(args[3]);

        GraphParser gf = new GraphParser();
        // on considere le graphe non oriente
        IGraph g = gf.parse(fileName, nbNodes, false);
        Memory.mem();

        System.out.println("degmax=" + g.degreeMax());
        System.out.println("dist=" + g.distance(firstNode, secondNode));

    }
}