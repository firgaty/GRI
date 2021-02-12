package graph;

import graph.IGraph;
import java.util.Arrays;

public class Triangles {
	boolean[] neighbour;

	public Triangles(int vertices_count) {
		neighbour = new boolean[vertices_count];
		Arrays.fill(neighbour, false);
	}

	public int find_triangles(IGraph g, int u) {
		for (int n : g.adjacencyListIter(u)) {
			neighbour[n] = Boolean.TRUE;
		}

		int total = 0;
		for (int x : g.adjacencyListIter(u)) {
			for (int z : g.adjacencyListIter(x)) {
				if (neighbour[z])
					total++;
			}
		}

		for (int n : g.adjacencyListIter(u)) {
			neighbour[n] = false;
		}

		return total / 2;
	}

}