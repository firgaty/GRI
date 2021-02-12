package graph;

import graph.IGraph;
import java.util.Arrays;

public class Triangles {
	boolean[] neighbour;

    public Triangles (int vertices_count){
    	neighbour = new boolean[vertices_count];
    	Arrays.fill(neighbour, false);
    }

    public int find_triangles(IGraph g, int u){
    	int[] u_adj_lst = g.adjacencyList(u);
    	for (int n : u_adj_lst) {
    		neighbour[n] = true;
    	}

    	int total = 0;
    	for (int x : u_adj_lst) {
    		int[] neighbour_of_x = g.adjacencyList(x);
    		for (int z : neighbour_of_x) {
    			if (neighbour[z])
    				total++;
    		}
    	}

    	for (int n : u_adj_lst) {
    		neighbour[n] = false;
    	}

    	return total / 2;
    }

}