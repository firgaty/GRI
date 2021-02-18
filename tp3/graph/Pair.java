package graph;

import java.util.* ;
public class Pair implements Comparable {

    private int node;
    private int degree;

    public Pair(int node, int deg) {
        this.node = node;
        this.degree =  deg;
    }

    public int getNode(){
        return node;
    }    
    public int getDeg() {
        return degree;
    } 

    public void modifyDeg(int nVal){
        degree = nVal;
    }

    @Override
    public int compareTo(Object y){
        Pair p = (Pair)y;
        int degy = p.getDeg();
        if (degree > degy){
            return 1;
        } else if (degree < degy) {
            return -1;
        }
        return 0;
    }
}