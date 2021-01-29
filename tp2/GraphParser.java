import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.System;

public class GraphParser implements IGraphParser {

    final Pattern pattern = Pattern.compile("^(\\d+)\\s+(\\d+)$");

    @Override
    public IGraph parse(String file, int maxNode, boolean oriented) {
        int[] from = new int[maxNode];
        int[] to = new int[maxNode];
        int i = 0;

        BufferedReader objReader = null;

        // O(n)
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(file));
            while ((strCurrentLine = objReader.readLine()) != null && strCurrentLine.charAt(0) == '#')
                ;

            while (i < maxNode && strCurrentLine != null) {
                Matcher matcher = pattern.matcher(strCurrentLine);

                if (matcher.find()) {
                    from[i] = Integer.parseInt(matcher.group(1));
                    to[i] = Integer.parseInt(matcher.group(2));
                } else {
                    System.out.println(i);
                    break;
                }
                i++;
                strCurrentLine = objReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // O(n log n)
        // Memory.mem();
        return graphFromArrays(from, to, oriented, i);
    }

    private IGraph graphFromArrays(int[] from, int[] to, boolean oriented, int nb_edges) {

        // O(n)
        {
            int[] newFrom = new int[nb_edges];
            int[] newTo = new int[nb_edges];

            System.arraycopy(from, 0, newFrom, 0, nb_edges);
            System.arraycopy(to, 0, newTo, 0, nb_edges);

            from = newFrom;
            to = newTo;
        }

        // Ask to free memory
        System.gc();

        // Calculate nodes
        int max = 0;

        Integer[] idx = new Integer[from.length];
        for (int i = 0; i < from.length; i++) {
            idx[i] = i;
        }

        // First Sort
        {
            // O(n log n)
            sort(idx, from, to);

            // O(n)
            int[] newFrom = new int[from.length];
            int[] newTo = new int[from.length];

            for (int i = 0; i < to.length; i++) {
                // reorder
                newFrom[i] = from[idx[i]];
                newTo[i] = to[idx[i]];

                // set max
                if (from[i] > max)
                    max = from[i];
                if (to[i] > max)
                    max = to[i];
            }

            from = newFrom;
            to = newTo;
        }

        // Ask to free memory
        System.gc();

        // Add reverse edges if not oriented
        if (!oriented) {
            // O(n log n)
            idx = new Integer[from.length * 2];
            int[] newFrom = new int[from.length * 2];
            int[] newTo = new int[from.length * 2];

            for (int i = 0; i < from.length; i++) {
                newFrom[i] = from[i];
                newTo[i] = to[i];

                // O(log n)
                // if (!contains(from, to, to[i], from[i], from.length - 1)
                // && !contains(newFrom, newTo, to[i], from[i], newFrom.length - 1)) {
                newFrom[i + from.length] = to[i];
                newTo[i + from.length] = from[i];
                // }

                idx[i] = i;
                idx[i + from.length] = i + from.length;
            }

            to = newTo;
            from = newFrom;
        }

        // Ask to free memory
        System.gc();

        // Sort
        {
            // O(n log n)
            sort(idx, from, to);

            // O(n)
            int[] newFrom = new int[from.length];
            int[] newTo = new int[from.length];

            for (int i = 0; i < to.length; i++) {
                // reorder
                newFrom[i] = from[idx[i]];
                newTo[i] = to[idx[i]];
            }

            from = newFrom;
            to = newTo;
        }

        // Ask to free memory
        System.gc();

        // Create graph
        IGraph g = new Graph(max + 1, oriented);
        // System.out.println("n=" + (max + 1));
        // System.out.println("m=" + nb_edges);
        // O(n)
        int inf = 0;
        // Pass null values
        for (; inf < to.length && from[inf] == 0 && to[inf] == 0; inf++)
            ;

        for (int i = inf; i < to.length; i++) {
            if (from[i] != from[inf]) {
                int[] edges = new int[i - inf];

                for (int j = 0; j < i - inf; j++) {
                    edges[j] = to[inf + j];
                }

                g.addEdges(from[inf], edges);

                inf = i;
            }
        }

        { // Last part
            int[] edges = new int[to.length - inf];

            for (int j = 0; j < to.length - inf; j++) {
                edges[j] = to[inf + j];
            }

            g.addEdges(from[inf], edges);
        }

        return g;
    }

    private void sort(final Integer[] idx, final int[] from, final int[] to) {
        // O(n log n)
        // Sort, remove and sort again to filter out
        Arrays.sort(idx, (a, b) -> {
            if (from[a] < from[b])
                return -1;
            if (from[a] > from[b])
                return 1;
            if (to[a] < to[b])
                return -1;
            if (to[a] > to[b])
                return 1;
            return 0;
        });
    }

    // private boolean contains(final int[] from, final int[] to, final int keyA, final int keyB, int high) {
    //     int low = 0;

    //     while (low <= high) {
    //         int mid = (low + high) / 2;
    //         int comp = compare(mid, keyA, keyB, from, to);

    //         if (comp < 0) {
    //             low = mid + 1;
    //         } else if (comp > 0) {
    //             high = mid - 1;
    //         } else if (comp == 0) {
    //             return true;
    //         }
    //     }

    //     return false;
    // }

    // private int compare(final int idx, final int fromVal, final int toVal, final int[] from, final int[] to) {
    //     if (from[idx] < fromVal)
    //         return -1;
    //     if (from[idx] > fromVal)
    //         return 1;
    //     if (to[idx] < toVal)
    //         return -1;
    //     if (to[idx] > toVal)
    //         return 1;
    //     return 0;
    // }

    public static void main(String[] args) {
        String file = "web-BerkStan.txt";
        // IGraph g = gf.parse(args[0], Integer.parseInt(args[1]),false);
        GraphParser gf = new GraphParser();

        // IGraph g = gf.parse(file, 28980, false);
        IGraph g = gf.parse(file, 7600595, false);
    }

}
