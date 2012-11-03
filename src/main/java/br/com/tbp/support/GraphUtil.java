package br.com.tbp.support;


import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;

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

    public static String reconstructPath(Map<Node, Node> cameFrom, Node currentNode) {
        if (cameFrom.get(currentNode) != null) {
            String p = reconstructPath(cameFrom, cameFrom.get(currentNode));
            return (p + " -> " + currentNode.toString());
        }
        return currentNode.toString();
    }

}
