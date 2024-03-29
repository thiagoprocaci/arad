package br.com.tbp.search.astar;

import java.util.*;

import br.com.tbp.model.Node;
import br.com.tbp.model.semantic.ISemanticNode;
import br.com.tbp.search.Algorithm;
import br.com.tbp.support.GraphUtil;

/**
 * Implementacao do "a star search"
 *
 */
public class AStarSearch implements Algorithm {
    // heuristica que sera injetada
    private IHeuristic heuristic;

    /**
     *
     * @param start
     *            no inicial
     * @param goal
     *            no final
     * @return Retorna caminho encontrado pelo "a star search"
     */
    public List<Node> run(Node start, Node goal) {

        // The set of nodes already evaluated.
        Set<Node> closedSet = new HashSet<Node>();
        // The set of tentative nodes to be evaluated, initially containing the
        // start node
        Set<Node> openSet = new HashSet<Node>();
        openSet.add(start);
        // The map of navigated nodes.
        Map<Node, Node> cameFrom = new HashMap<Node, Node>();
        Map<Node, Double> g_score = new HashMap<Node, Double>();
        g_score.put(start, 0d);
        Map<Node, Double> f_score = new HashMap<Node, Double>();
        f_score.put(start, g_score.get(start) + heuristic.estimate(start, goal));
        Node current = null;
        Double tentative_g_score = null;

        while (!openSet.isEmpty()) {
            current = getNodeWithLowestFScore(openSet, f_score);

            if (current.equals(goal)) {
                List<Node> nodeList = GraphUtil.reconstructPath(cameFrom,goal);

                return nodeList;
            }
            openSet.remove(current);
            closedSet.add(current);

            for (Node neighbor : current.getSuccessors()) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }
                tentative_g_score = g_score.get(current) + GraphUtil.distBetween(current, neighbor);
                if (!openSet.contains(neighbor) || tentative_g_score <= g_score.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    g_score.put(neighbor, tentative_g_score);
                    f_score.put(neighbor, g_score.get(neighbor) + heuristic.estimate(neighbor, goal));
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }

        }
        return new ArrayList<Node>();
    }

    public Node getNodeWithLowestFScore(Set<Node> openSet, Map<Node, Double> f_score) {
        Double minScore = null;
        Node n = null;
        for (Node node : openSet) {
            if (f_score.get(node) != null) {
                if (n == null) {
                    // at the first time, we execute here..
                    n = node;
                    minScore = f_score.get(node);
                } else if (minScore > f_score.get(node)) {
                    n = node;
                    minScore = f_score.get(node);
                }
            }
        }
        return n;
    }



    public void setHeuristic(IHeuristic heuristic) {
        this.heuristic = heuristic;
    }
}
