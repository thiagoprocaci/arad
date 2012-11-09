package br.com.tbp.search.astar;


import br.com.tbp.model.Node;
import br.com.tbp.model.semantic.ISemanticNode;

public class OAHeuristic implements IHeuristic {
    @Override
    public double estimate(Node start, Node goal) {
        ISemanticNode startNode = (ISemanticNode) start;
        ISemanticNode goalNode = (ISemanticNode) goal;

        if(goalNode.getNodeWeight() != null) {
            //isso deve ser relativo..
            // senao eu influencio o g(n)
            if(goalNode.getNodeWeight() >= 5) {
                return 1000;
            } else if(goalNode.getNodeWeight() == 4) {
                return 100;
            } else if (goalNode.getNodeWeight() == 3) {
                 return 4;
            } else if(goalNode.getNodeWeight() <= 2) {
                return 0;
            }
        }
        return 0;
    }
}
