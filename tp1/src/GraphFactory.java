import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

public class GraphFactory {

  public static int[] split_string(String s)
  {
    String[] split = s.replaceAll("(\\t|\\s)+"," ").split(" ");
    return new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
  }

  public static void fill_graph(Graph g, String line)
  {
    int[] n = split_string(line);
    g.addEdge(n[0], n[1]);
  }

  public static int get_max_node(String file) {
    BufferedReader objReader = null;
    int max = 0;
    Boolean check_comment = true;
    try {
    String strCurrentLine;
    objReader = new BufferedReader(new FileReader(file));
      while ((strCurrentLine = objReader.readLine()) != null) {
        if (check_comment) {
          if (strCurrentLine.charAt(0) != '#'){
            int[] tmp = split_string(strCurrentLine);
            max = Math.max(tmp[0],tmp[1]);
            check_comment=false;
          }
        }
        else{
          int[] tmp = split_string(strCurrentLine);
          max = Math.max(max,Math.max(tmp[0],tmp[1]));
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
  return max;
  }

 public static Graph create(String file, int max_node) {
  BufferedReader objReader = null;
  Boolean check_comment = true;
  Graph g = new Graph(max_node + 1);
  try {
   String strCurrentLine;
   objReader = new BufferedReader(new FileReader(file));
   while ((strCurrentLine = objReader.readLine()) != null) {
    if (check_comment) {
      if (strCurrentLine.charAt(0) != '#'){
        fill_graph(g, strCurrentLine);
        check_comment=false;
      }
    }
    else
      fill_graph(g, strCurrentLine);
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
  return g;
 }

/* public static void main(String[] args) {
  Graph g = GraphFactory.create(args[0], get_max_node(args[0]));
 }*/

}
