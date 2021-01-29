package graph.sweep;

import graph.IGraph;
import java.awt.Point;
import java.util.*;
/**
 * EXO 2
 */
public class FourSweep implements IGraphSweep {
    @Override
    public int sweep(IGraph g, int u) {
        Point firstBFS = BFS_max(g, u);
        int v = (int)firstBFS.getX();
        
        int m = demi_BFS(g,v);

        firstBFS = BFS_max(g, m);
        v = (int)firstBFS.getX();
        Point sdBFS = BFS_max(g, v); 
        int w = (int)sdBFS.getX();
        int dist = (int)sdBFS.getY();

        return dist; // TODO
    }


   public Point BFS_max(IGraph g,int u){
        // initialisation
        int t = g.verticesCount();
        Deque<Integer> File = new ArrayDeque<Integer>(t);
        int[] parents = new int[t];
        for (int i = 0; i < t; i++) {
            parents[i] = -1;
        }

        File.add(u);
        parents[u] = -2;
        int s = u;
        int v;

        while (!File.isEmpty()) {
            s = File.poll();
            int [] voisins = g.adjacencyList(s);
            // On ajoute ses voisins a la File
            for (int j = 0; j < voisins.length; j++) {
                // Si pas marque on l'ajoute
                if (parents[voisins[j]] == -1) {
                    File.addLast(voisins[j]);
                    parents[voisins[j]] = s;
                }
            }
        }

        // Si c'est le sommet voulu on s'arrete et on calcule la distance parcourue
        int dist = 0;
        v = s;
        while (s != u) {
            s = parents[s];
            dist++;
        }

        return new Point(v,dist);

    }

    public int demi_BFS(IGraph g, int u){
        // initialisation
        int t = g.verticesCount();
        Deque<Integer> File = new ArrayDeque<Integer>(t);
        int[] parents = new int[t];
        for (int i = 0; i < t; i++) {
            parents[i] = -1;
        }

        File.add(u);
        parents[u] = -2;
        int s = u;
        int v;

        while (!File.isEmpty()) {
            s = File.poll();
            int [] voisins = g.adjacencyList(s);
            // On ajoute ses voisins a la File
            for (int j = 0; j < voisins.length; j++) {
                // Si pas marque on l'ajoute
                if (parents[voisins[j]] == -1) {
                    File.addLast(voisins[j]);
                    parents[voisins[j]] = s;
                }
            }
        }

        //on calcule la distance parcourue
        int dist = 0;
        v = s;
        while (s != u) {
            s = parents[s];
            dist++;
        }

        //on cherche le milieu du chemin u,v
        int middle = 0;
        s = v;
        while(middle < dist/2){
            s = parents[s];
            middle++;
        }

        return s;
    }
}
