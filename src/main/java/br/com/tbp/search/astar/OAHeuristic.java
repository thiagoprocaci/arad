package br.com.tbp.search.astar;


import br.com.tbp.model.Node;
import br.com.tbp.model.semantic.ISemanticNode;

public class OAHeuristic implements IHeuristic {
    @Override
    public double estimate(Node start, Node goal) {
        ISemanticNode startNode = (ISemanticNode) start;
        // ISemanticNode goalNode = (ISemanticNode) goal;
        double h = 0d;
        if (startNode.getNodeWeight() != null) {
            //isso deve ser relativo..
            // senao eu influencio o g(n)
            if (startNode.getNodeWeight() >= 5) {
                h = 1000d;
            } else if (startNode.getNodeWeight() == 4) {
                h = 100d;
            } else if (startNode.getNodeWeight() == 3) {
                h = 4d;
            } else if (startNode.getNodeWeight() <= 2) {
                h = 0d;
            }
        }
        System.out.println(start + " " + h);
        return h;
    }
}
