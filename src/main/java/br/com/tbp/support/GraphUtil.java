package br.com.tbp.support;


import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;
import br.com.tbp.model.semantic.Disciplina;
import br.com.tbp.model.semantic.Topico;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GraphUtil {

    public static Double distBetween(Node src, Node dest) {
        for (Edge edge : src.getEdgeList()) {
            if (dest.equals(edge.getDest()) && src.equals(edge.getSrc())) {
                return edge.getDistance();
            }
        }
        return null;
    }

    public static List<Node> reconstructPath(Map<Node, Node> cameFrom, Node currentNode) {
        if (cameFrom.get(currentNode) != null) {
            List<Node> p = reconstructPath(cameFrom, cameFrom.get(currentNode));
            p.add(currentNode);
            return p;
        }
        List<Node> nodeList = new ArrayList<Node>();
        nodeList.add(currentNode);
        return nodeList;
    }

    public static Disciplina getDisciplinaRoot(Collection<Disciplina> nodeList) {
        for(Disciplina node:nodeList) {
              if(node.getAntecessors().size() == 0) {
                  return node;
              }

           }
        return null;
    }

    public static Topico getTopicoRoot(Collection<Topico> nodeList) {
        for(Topico t:nodeList) {
            if(t.isRoot()) {
                return t;
            }
        }
        return null;
    }

    public static Topico getTopicoGoal(Collection<Topico> nodeList) {
        for(Topico t:nodeList) {
            if(t.isGoal()) {
                return t;
            }
        }
        return null;
    }
}



