package graph;

import graph.IGraph;
import java.util.Arrays;

public class Triangles {
	boolean[] neighbour;
	boolean[] marked;
	int[] count;
	IGraph g;

	public Triangles(IGraph g) {
		this.g = g;

		neighbour = new boolean[g.verticesCount()];
		marked = new boolean[g.verticesCount()];
		count = new int[g.verticesCount()];
	}

	public int triangles(int u) {
		if (marked[u]) {
			return count[u];
		}

		marked[u] = true;

		for (int n : g.adjacencyListIter(u)) {
			if (!marked[n])
				neighbour[n] = true;
		}

		for (int v : g.adjacencyListIter(u)) {
			if (!marked[v]) {
				for (int w : g.adjacencyListIter(v)) {
					if (neighbour[w]) {
						count[u]++;
						count[v]++;
						count[w]++;
					}
				}
				neighbour[v] = false;
			}
		}

		return count[u];
	}

}