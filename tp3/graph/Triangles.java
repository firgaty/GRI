package graph;

import graph.IGraph;
import java.util.Arrays;

public class Triangles {
	Boolean[] neighbour;

    public int triangles (IGraph g, int u){
    	neighbour = new Boolean[g.verticesCount()];
    	Arrays.fill(neighbour, Boolean.FALSE);
    	int[] u_neighbour = g.adjacencyList(u);
    	for (int i = 0; i < u_neighbour.length; i++) {
    		neighbour[u_neighbour[i]] = Boolean.TRUE;
    	}
        return find_triangles(g, u, u_neighbour);
    }

    public int find_triangles(IGraph g, int u, int[] u_neighbour){
    	int total = 0;
    	for (int i = 0; i < u_neighbour.length; i++) {
    		int[] uu_neighbour = g.adjacencyList(u_neighbour[i]);
    		for (int j = 0; j < uu_neighbour.length; j++) {
    			if (neighbour[uu_neighbour[j]])
    				total++;
    		}
    	}
    	return total / 2;

    }

}