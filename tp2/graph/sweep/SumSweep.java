package graph.sweep;

import graph.IGraph;
import java.util.*;

/**
 * EXO 3
 */
public class SumSweep implements IGraphSweep {
    @Override
    public int sweep(IGraph g, int u) {
    	int[] distances_u = GetBfsDistances(BFS_parents(g, u));
    	int v = Get_Ecc(distances_u);

    	int[] distances_v = GetBfsDistances(BFS_parents(g, v));
    	int w = Get_Ecc(distances_v);

    	int[] distances_w = GetBfsDistances(BFS_parents(g, w));
    	int x = Get_Ecc(distances_w);

    	int[] distances_x = GetBfsDistances(BFS_parents(g, x));
    	int t = Get_Ecc(distances_x);

    	int max = distances_u[v];
    	max = max < distances_v[w] ? distances_v[w]:max;
    	max = max < distances_w[x] ? distances_w[x]:max;
    	max = max < distances_x[t] ? distances_x[t]:max;
        return max; // TODO
    }

    public int[] BFS_parents(IGraph g,int u){
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
		return parents;
    }

    public int[] GetBfsDistances(int[] parents){
		int i = 0;
		int n = parents.length;
		int[] dist = new int[n];
		while (i < n){
			if (parents[i] == -1)
				dist[i] = -1;
			else if (parents[i] == -2)
				dist[i] = 0;
			else{
				int a = i;
				int b = parents[i];
				while(a!=b & a!=-2 & b!=-2){
					dist[i] += 1;
					a = parents[a];
					b = parents[b];
				}
			}
			i++;
		}
		return dist;
	}

	public int Get_Ecc(int[] distances){
		int max = 0;
		int ecc = -4;
		for (int j = 0; j < distances.length; j++){
			if (distances[j] > max) {
				max = distances[j];
				ecc = j;
			}
		}
		return ecc;
	}
}
