package graph;

import graph.IGraph;
import java.util.Arrays;

public class Triangles {
	boolean[] neighbour;
	IGraph g;

	public Triangles(IGraph g) {
		this.g = g;
		neighbour = new boolean[g.verticesCount()];
		Arrays.fill(neighbour, false);
	}

	public int triangles(int u) {
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