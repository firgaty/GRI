package graph;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Edges {
    int[] tail, head;
    public int n, m;
    final Pattern pattern = Pattern.compile("^(\\d+)\\s+(\\d+)");

    public Edges() {
        int size_m = 4; // without a given estimation of m, let's begin with 4
        tail = new int[size_m];
        head = new int[size_m];
        n = 0;
        m = 0;
    }

    public void add(InputStreamReader input, int m_max) throws IOException {
        BufferedReader inp = new BufferedReader(input);
        while (m < m_max) {
            String line = inp.readLine();
            if (line == null)
                break;
            if (line.charAt(0) == '#') {
                // System.err.println(line);
                continue;
            }
            int u;
            int v;

            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                u = Integer.parseInt(matcher.group(1));
                v = Integer.parseInt(matcher.group(2));
            } else {
                throw new IOException("Bad format");
            }

            // add edge u v:
            if (m >= tail.length) {
                increaseCapacity();
            }
            if (u >= n) {
                n = u + 1;
            }
            if (v >= n) {
                n = v + 1;
            }
            tail[m] = u;
            head[m] = v;
            ++m;
        }
    }

    private void increaseCapacity() {
        // let's double array sizes:
        int[] t = new int[2 * tail.length];
        int[] h = new int[2 * tail.length];
        for (int i = 0; i < tail.length; ++i) {
            t[i] = tail[i];
            h[i] = head[i];
        }
        tail = t;
        head = h;
    }

    public int[] degrees(boolean symmetrize) {
        int[] deg = new int[n];
        for (int e = 0; e < m; ++e) {
            deg[tail[e]]++;
            if (symmetrize)
                deg[head[e]]++;
        }
        return deg;
    }
}
