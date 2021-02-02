package graph.sweep;

import graph.IGraph;
import java.util.*;

/**
 * EXO 3
 */
public class SumSweep implements IGraphSweep {
    @Override
    public int sweep(IGraph g, int u) {
    	int ecc_max = 0;
    	int[] t = {0,u};
    	int[] sumdist = new int[g.verticesCount()];

    	for (int i = 0; i < 4; i++) {
    		SumDistAndEcc(t, BFS_parents(g, t[1]), sumdist);
    		ecc_max = t[0] > ecc_max ? t[0]:ecc_max;
    	}

    	return ecc_max;
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

    public void SumDistAndEcc(int[] t, int[] parents, int[] sumdist){
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
					sumdist[i] += 1;
					a = parents[a];
					b = parents[b];
				}
			}
			i++;
		}

		int max_sumdist = 0;
		int next = -4;
		int max_ecc = -4;
		for (int j = 0; j < dist.length; j++){
			if (dist[j] > max_ecc) {
				max_ecc = dist[j];
			}
			if (sumdist[j] > max_sumdist) {
				max_sumdist = sumdist[j];
				next = j;
			}
		}
		t[0] = max_ecc;
		t[1] = next;
	}

}
