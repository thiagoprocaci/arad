package br.com.tbp.support;


import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;

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

    public static Node getRoot(Collection<? extends Node> nodeList) {
        for(Node node:nodeList) {
              if(node.getAntecessors().size() == 0) {
                  return node;
              }

           }
        return null;
    }
}

