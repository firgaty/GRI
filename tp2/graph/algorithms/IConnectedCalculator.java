package graph.algorithms;

import graph.IGraph;

public interface IConnectedCalculator {
    /**
     * @return base node of the connected graph of maximum vertices.
     */
    int baseMaxConnectedNode(IGraph g);

}
