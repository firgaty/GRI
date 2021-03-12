import graph.IGraph;
import java.util.ArrayList;

public class Louvain{

    public static Long[] algoPhase(IGraph g){
        Community c = new Community(g);

        ArrayList<Long> res = new ArrayList<>(0);
        long modularity = c.modularity();
        long new_mod = modularity + 1L;
        while(modularity < new_mod){
            unePhase(c,g);  
            new_mod = c.modularity();
            res.add(new_mod);
        }

        return res.toArray(new Long[res.size()]);
    }

    private static void unePhase (Community c, IGraph g){
        //on inspecte les sommets dans l'ordre 0 a n-1
        for(int i =0; i<g.verticesCount(); i++){
            Iterable<Integer> voisins = g.adjacencyListIter(i);
            final int j  = i;
            voisins.forEach((element) -> {
                long mod_max = 0L ;
                int com_max = - 1;
                //SI un des voisins n'est pas dans la meme communaute
                if (c.community[element] != c.community[j]) {
                    long mod = c.computeModularityDelta(j, c.community[element]);
                    if(mod > mod_max){
                        mod_max =  mod;
                        com_max = c.community[element] ;
                    }else if (mod ==  mod_max && com_max > c.community[element]){
                        com_max = c.community[element] ;
                    }   
                }
                if(mod_max > 0L){
                    c.move(j, com_max);
                }
            });
        }
    }
}