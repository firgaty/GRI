package graph;

public interface IGraph extends Iterable<Integer> {

    int edgeCount();

    int verticesCount();

    // int degreeMax();

    int degree(int u);

    // int distance(int u, int v);

    int[] adjacencyList(int u);

    Iterable<Integer> adjacencyListIter(int u);
}