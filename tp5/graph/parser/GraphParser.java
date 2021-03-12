package graph.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import graph.Graph;
import graph.IGraph;
import graph.Edges;

public class GraphParser implements IGraphParser {

    final Pattern pattern = Pattern.compile("^(\\d+)\\s+(\\d+)");

    @Override
    public IGraph parse(String file, int maxNode, boolean oriented) {
        Edges edg = new Edges();
        try {
            edg.add(new FileReader(file), maxNode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        IGraph g = new Graph(edg, true);
        
        return g;
    }
}
