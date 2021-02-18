package graph;
import java.awt.Point;
import graph.IGraph;
import graph.Pair;
import java.util.PriorityQueue;
import java.util.*;


class MaintientDegre { 
    boolean [] states; 
    int[] deg;
    //Pour pouvoir consulter les voisins sans faire de copie
    IGraph g;

    //Constructeur, initialise tous les sommets a True et calcule leur degre
    public MaintientDegre (IGraph g){
        int n = g.verticesCount();
        states = new boolean[n];
        deg = new int[n];
        this.g = g;
        for (int i = 0; i < n; i ++){
            states[i] = true;
            deg[i] = g.degree(i);
        }
    }

    //Change l'etat du sommet u et met a jour le degre de ses voisins
    //Si par la desactivation de u, l'un de ses voisins a un degre inferieur a k on le desactive recursivement
    public void desactivate_rec (int u, int k) {
        states[u] = false;
        Iterable<Integer> voisins  = g.adjacencyListIter(u);
        int v;

        voisins.forEach(
            (element) -> {
            if(states[element]){
                deg[element] = deg[element] - 1;
               // System.out.println("deg "+element +" - 1");
                if(deg[element] < k){
                    desactivate_rec((int)element,k);
                }
            } 
            } 
        );

    }

    public void desactivate(int u){
        desactivate_rec(u, - 1);
    }

    public int[] getDeg(){
        return deg;
    }

    public boolean getState(int i){
        return states[i];
    }

    public int getDeg (int i){
        return deg[i];
    }

    public void print(){
        for (int i =0; i< states.length ; i++){
            System.out.println("States "+i+" = "+ states[i]);
            System.out.println("Deg "+i+" = "+ deg[i]);
        }
        System.out.println("FIN");
    }
}

public class Coeur {

    

    public int[] kCoeur (IGraph g){
        int n = g.verticesCount();
        MaintientDegre Mdeg = new MaintientDegre(g);
        PriorityQueue<Pair> queue = new PriorityQueue(n);
        int [] res = new int[2];
        int k = 0;

        fillQueue(Mdeg, queue);
        int size = queue.size();
        int dernierK = 0;

        while(!queue.isEmpty()){
            Pair p = queue.peek();
            if(p.getDeg() < k && k < n){
                dernierK = p.getDeg();
               // System.out.println("desactive "+p.getNode() + " " + p.getDeg() + " " + k);
                Mdeg.desactivate_rec(p.getNode(), k);
                queue = new PriorityQueue(n);
                fillQueue(Mdeg, queue);
                //Mdeg.print();
            }else{
                k ++;
                size = queue.size();
            }
        }

        res[0] = dernierK;
        res[1] = size;
        return res;
    }

    //Remplissage de la PriorityQueue
    public void fillQueue(MaintientDegre m, PriorityQueue<Pair> queue){
        int [] deg = m.getDeg();
        for(int i = 0; i<deg.length; i++) {
            if(m.getState(i)) queue.add(new Pair(i, deg[i]));
        }
    }


}




 