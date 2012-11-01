package br.com.tbp.search.dijkstra;


import br.com.tbp.GraphBuilder;
import br.com.tbp.model.Node;
import br.com.tbp.search.dijkstra.support.Vertex;
import br.com.tbp.search.dijkstra.support.Edge;

import java.util.*;

public class DijkstraSearch {

    public static void main(String... arg) {
        GraphBuilder graphBuilder = new GraphBuilder();
        List<Node> nodeList = graphBuilder.buildMacroGraph();
     //   System.out.print(nodeList);
        DijkstraSearch dijkstraSearch = new DijkstraSearch();
        dijkstraSearch.run(nodeList.get(3), nodeList.get(2));


    }

    public String run(Node start, Node goal) {
        Map<Integer, Vertex> map = new HashMap<Integer, Vertex>();
        init(start, map);
        Vertex[] vertices =  new Vertex[map.size()];
        int i = 0;
        for (Integer key: map.keySet()) {
            vertices[i] = map.get(key);
            i++;
        }
        computePaths(map.get(goal.getId()));
        for (Vertex v : vertices) {
            System.out.println("Distance to " + v + ": " + v.minDistance);
            List<Vertex> path = getShortestPathTo(v);
            System.out.println("Path: " + path);
        }
        return null;
    }

    private void init(Node start, Map<Integer, Vertex> map) {
        if(map.containsKey(start.getId())) {
            return;
        }
        List<Node> successors = start.getSuccessors();
        Vertex vertex = new Vertex(start.toString());
        vertex.adjacencies = new Edge[successors.size()];
        map.put(start.getId(), vertex);
        int i = 0;
        for (Node node: successors) {
               init(node, map);
               vertex.adjacencies[i] = new Edge(map.get(node.getId()), distBetween(start, node));
               i++;
        }

    }

    private void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();
            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    private List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    private Double distBetween(Node src, Node dest) {
        for (br.com.tbp.model.Edge edge : src.getEdgeList()) {
            if (dest.equals(edge.getDest()) && src.equals(edge.getSrc())) {
                return edge.getDistance();
            }
        }
        return null;
    }

}


