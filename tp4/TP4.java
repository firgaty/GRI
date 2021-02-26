import java.lang.Math;
import java.util.Random;

public class TP4 {

    public int[] graphe_aleatoire(int[] degrees) {
        return null;
    }

    public static void myShuffle(int[] t) {
		Random rand = new Random();
		for(int j = t.length - 1 ; j> 1 ; j --){
			int r = rand.nextInt(j + 1);
			int tmp = t[r];
			t[r] = t[j];
			t[j] = tmp;
		}
	}

    public static int[] exemple(int [] seq) {
         //On calcule la taille de E
        int taille = 0;
        for(int i = 0; i< seq.length; i++){
            taille += seq[i];
        }

        int[] E = new int[taille];
        int pos  = 0;
        for (int i =0 ; i< seq.length ; i ++){
            int tmp = 0;
            while(tmp < seq[i]){
                E[pos] = i;
                tmp ++;
                pos ++;
            }
        }
        myShuffle(E);
        return  E;
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

    public static void print_edges(int[] edges) {
        for (int i = 0; i < edges.length; i += 2) {
            System.out.println(Integer.toString(edges[i]) + " " + Integer.toString(edges[i + 1]));
        }
    }

    public static void main(String args[]) {
        String type = args[0];
        //String fileName = args[1];
        int out = -1;

        switch (type) {
            case "racine": {
                break;
            }
            case "puissance": {
                break ;
            }
            case "exemple": {
                int [] def = new int[] {1,2,1,4} ;
                int [] tab = exemple(def);
                print_edges(tab);
                break;
            }
            default: {
                return;
            }
        }

    }
}