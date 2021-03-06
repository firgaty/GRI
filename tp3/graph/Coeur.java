package graph;

import utils.ProgressBar;

import java.util.PriorityQueue;
import java.util.*;

class NodeState {
    public int node;
    public boolean state;
    public int degree;

    public NodeState(int node, boolean state, int degree) {
        this.node = node;
        this.state = state;
        this.degree = degree;
    }
}

class MaintientDegre {
    NodeState[] states;

    // Pour pouvoir consulter les voisins sans faire de copie
    IGraph g;
    PriorityQueue<NodeState> queue;

    // Constructeur, initialise tous les sommets a True et calcule leur degre
    public MaintientDegre(IGraph g) {
        int n = g.verticesCount();
        states = new NodeState[n];
        queue = new PriorityQueue<>(n, new Comparator<NodeState>() {
            @Override
            public int compare(NodeState a, NodeState b) {
                return Integer.compare(a.degree, b.degree);
            }
        });

        this.g = g;

        ProgressBar bar = new ProgressBar(n, "pop");
        for (int i = 0; i < n; i++) {
            bar.show();
            states[i] = new NodeState(i, true, g.degree(i));
            queue.add(states[i]);
            bar.iter();
        }
        bar.show();
    }

    public void removeNode(int u, int k) {
        states[u].state = false;
        queue.remove(states[u]);

        g.adjacencyListIter(u).forEach((element) -> {
            NodeState nodeState = states[element];

            if (nodeState.state) {
                queue.remove(nodeState);
                nodeState.degree = nodeState.degree - 1;
                queue.add(nodeState);
            }
        });
    }

    public boolean getState(int i) {
        return states[i].state;
    }

    public int getDeg(int i) {
        return states[i].degree;
    }

    public NodeState nextState() {
        return queue.peek();
    }

    public boolean queueIsEmpty() {
        return queue.isEmpty();
    }

    public int queueSize() {
        return queue.size();
    }

    public void print() {
        for (int i = 0; i < states.length; i++) {
            System.out.println("States " + i + " = " + states[i]);
            System.out.println("Deg " + i + " = " + getDeg(i));
        }
        System.out.println("FIN");
    }

}



public class Coeur {

    public int[] kCoeur(IGraph g) {
        
        int n = g.verticesCount();
        MaintientDegre maintientDegre = new MaintientDegre(g);
        int[] res = new int[2];
        int dernierK = 0;
        int size = 0;
        ProgressBar bar = new ProgressBar(n, "k-coeur");

        for (int k = 0; !maintientDegre.queueIsEmpty() && k < n; k++) {
            size = maintientDegre.queueSize();
            dernierK = k - 1;

            for (NodeState state = maintientDegre.nextState(); !maintientDegre.queueIsEmpty()
                    && state.degree < k; state = maintientDegre.nextState()) {
                bar.show();
                maintientDegre.removeNode(state.node, k);
                bar.iter();
            }
        }

        bar.show();

        res[0] = dernierK;
        res[1] = size;
        return res;
    }
}
