package graph.parser;

import graph.IGraph;

public interface IGraphParser {
    IGraph parse(String file, int maxNode, boolean oriented);
}
