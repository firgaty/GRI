import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphFactory {

    final Pattern pattern = Pattern.compile("^(\\d+)\\s+(\\d+)$");

    public IGraph generate(String file, int maxNode, boolean oriented) {
        int[] from = new int[maxNode];
        int[] to = new int[maxNode];

        BufferedReader objReader = null;

        // O(n)
        try {
            String strCurrentLine;
            objReader = new BufferedReader(new FileReader(file));
            while ((strCurrentLine = objReader.readLine()) != null && strCurrentLine.charAt(0) == '#')
                System.out.println(strCurrentLine);

            for (int i = 0; i < maxNode && strCurrentLine != null; i++, strCurrentLine = objReader.readLine()) {
                Matcher matcher = pattern.matcher(strCurrentLine);

                if (matcher.find()) {
                    from[i] = Integer.parseInt(matcher.group(1));
                    to[i] = Integer.parseInt(matcher.group(2));
                } else {
                    break;
                }
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
        return graphFromArrays(from, to, oriented);
    }

    private IGraph graphFromArrays(int[] from, int[] to, boolean oriented) {
        Integer[] idx;
        // Calculate nodes
        int max = 0;

        System.out.println("from length: " + from.length);

        idx = new Integer[from.length];
        for (int i = 0; i < from.length; i++) {
            idx[i] = i;
        }

        // First Sort
        {
            System.out.println("First sort...");
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
                if (!contains(from, to, to[i], from[i], from.length - 1)
                        && !contains(newFrom, newTo, to[i], from[i], newFrom.length - 1)) {
                    newFrom[i + from.length] = to[i];
                    newTo[i + from.length] = from[i];
                }

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
            System.out.println("Second sort...");
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

        System.out.println("Create Graph");
        // Create graph
        IGraph g = new Graph(max + 1, oriented);

        // O(n)
        int inf = 0;
        // Pass null values
        for (; inf < to.length && from[inf] == 0 && to[inf] == 0; inf++)
            ;

        for (int i = inf; i < to.length; i++) {
            // System.out.println(from[i] + ":" + to[i]);
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

        System.out.println("Ok!");
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

    public boolean contains(final int[] from, final int[] to, final int keyA, final int keyB, int high) {
        int low = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comp = compare(mid, keyA, keyB, from, to);

            if (comp < 0) {
                low = mid + 1;
            } else if (comp > 0) {
                high = mid - 1;
            } else if (comp == 0) {
                return true;
            }
        }

        return false;
    }

    public int compare(final int idx, final int fromVal, final int toVal, final int[] from, final int[] to) {
        if (from[idx] < fromVal)
            return -1;
        if (from[idx] > fromVal)
            return 1;
        if (to[idx] < toVal)
            return -1;
        if (to[idx] > toVal)
            return 1;
        return 0;
    }

    public static void main(String[] args) {
        String file = "web-BerkStan.txt";
        // IGraph g = gf.generate(args[0], Integer.parseInt(args[1]),false);
        GraphFactory gf = new GraphFactory();

        // IGraph g = gf.generate(file, 28980, false);
        IGraph g = gf.generate(file, 7600595, false);

        // int[] from = new int[] { 1, 2, 5, 6, 7, 7, 7, 7, 8, 9, 10 };
        // int[] to = new int[] { 5, 3, 6, 1, 2, 3, 4, 6, 1, 4, 10 };

        // System.out.println(gf.compare(6, 6, 4, from, to));
        // System.out.println(gf.contains(from, to, 6, 2, from.length -1));
    }

}
