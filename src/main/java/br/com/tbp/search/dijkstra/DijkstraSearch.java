package br.com.tbp.search.dijkstra;


import br.com.tbp.model.Node;

import br.com.tbp.search.Algorithm;
import br.com.tbp.support.GraphUtil;

import java.util.*;

public class DijkstraSearch implements Algorithm {


    public List<Node> run(Node start, Node goal) {
        Map<Integer, Node> map = new HashMap<Integer, Node>();
        initNodeMap(start, map);

        Map<Node, Double> distance = new HashMap<Node, Double>();
        Map<Node, Node> pred = new HashMap<Node, Node>();
        initDijsktraSearch(start, map, distance, pred);

        Set<Node> q = new HashSet<Node>(map.values());
        List<Node> successors = null;
        Double weight = null;
        Node u = null;
        while(!q.isEmpty()) {
            u = extractMin(q, distance);
            successors = u.getSuccessors();
            for(Node v: successors) {
                weight = GraphUtil.distBetween(u, v);
                if(distance.get(v) > distance.get(u) + weight) {
                    distance.put(v, distance.get(u) + weight);
                    pred.put(v, u);

                }
            }
        }
        List<Node> nodeList = GraphUtil.reconstructPath(pred, goal);
        return nodeList;
    }


    public Node extractMin(Set<Node> nodeSet, Map<Node, Double> distance) {
        Node minNode = null;
        Double minDistance = null;
        for(Node key: nodeSet) {
           if(minNode == null) {
              minNode = key;
              minDistance = distance.get(key);
           }
           if(minDistance > distance.get(key)) {
               minNode = key;
               minDistance = distance.get(key);
           }
        }
        nodeSet.remove(minNode);
        return minNode;
    }


    public void initDijsktraSearch(Node start, Map<Integer, Node> map, Map<Node, Double> distance, Map<Node, Node> pred) {
        for (Integer key: map.keySet()) {
            distance.put(map.get(key), Double.MAX_VALUE);
            pred.put(map.get(key), null);
        }
        distance.put(map.get(start.getId()), 0d);
    }

    public void initNodeMap(Node start, Map<Integer,Node> map) {
        if(!map.containsKey(start.getId())) {
            List<Node> successors = start.getSuccessors();
            map.put(start.getId(), start);
            for (Node node: successors) {
                initNodeMap(node, map);
            }
        }
    }
}


