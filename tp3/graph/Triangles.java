package graph;

import graph.IGraph;
import java.util.Arrays;

public class Triangles {
	Boolean[] neighbour;

	public int triangles(IGraph g, int u) {
		neighbour = new Boolean[g.verticesCount()];
		Arrays.fill(neighbour, Boolean.FALSE);
		int[] uNeighbour = g.adjacencyList(u);

		for (int i = 0; i < uNeighbour.length; i++) {
			neighbour[uNeighbour[i]] = Boolean.TRUE;
		}

		return findTriangles(g, u, uNeighbour);
	}

	private int findTriangles(IGraph g, int u, int[] uNeighbour) {
		int total = 0;

		for (int i = 0; i < uNeighbour.length; i++) {
			int[] uuNeighbour = g.adjacencyList(uNeighbour[i]);
			for (int j = 0; j < uuNeighbour.length; j++) {
				if (neighbour[uuNeighbour[j]])
					total++;
			}
		}

		return total / 2;
	}

}