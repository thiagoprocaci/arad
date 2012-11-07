package br.com.tbp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.*;

import br.com.tbp.support.AradBucharestGraphBuilder;
import br.com.tbp.support.GraphUtil;
import org.junit.Before;
import org.junit.Test;

import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;
import br.com.tbp.search.astar.AStarSearch;
import br.com.tbp.support.AradBucharestEnum;
import br.com.tbp.support.AradBucharestHeuristic;

public class AStarSearchTest {
    private AStarSearch aStarSearch;

    @Before
    public void doBefore() {
        aStarSearch = new AStarSearch();
    }


    @Test
    public void nodeWithLowestFScoreTest() {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        node1.setId(1);
        node2.setId(2);
        node3.setId(3);
        node4.setId(4);
        Map<Node, Double> f_score = new HashMap<Node, Double>();
        f_score.put(node4, 1d);
        f_score.put(node3, 2d);
        f_score.put(node2, 20d);
        f_score.put(node1, 31d);
        Set<Node> openSet = new HashSet<Node>();
        openSet.add(node3);
        openSet.add(node2);
        openSet.add(node1);
        assertEquals(node3, aStarSearch.getNodeWithLowestFScore(openSet, f_score));
        openSet.add(node4);
        assertEquals(node4, aStarSearch.getNodeWithLowestFScore(openSet, f_score));
    }

    @Test
    public void distBetweenTest() {
        Node node1 = new Node();
        Node node2 = new Node();
        node1.setId(1);
        node2.setId(2);

        Edge edge12 = new Edge();
        edge12.setSrc(node1);
        edge12.setDest(node2);
        node1.getEdgeList().add(edge12);
        node2.getEdgeList().add(edge12);
        edge12.setDistance(10d);

        assertTrue(10 == GraphUtil.distBetween(node1, node2));
    }

    @Test
    public void AradBucharestTest() {
        AradBucharestGraphBuilder aradBucharestGraphBuilder = new AradBucharestGraphBuilder();
        Map<Integer, Node> map = aradBucharestGraphBuilder.buildAradBucharestGraph();
        Node arad = map.get(AradBucharestEnum.ARAD.id());
        Node bucharest = map.get(AradBucharestEnum.BUCHAREST.id());

        aStarSearch.setHeuristic(new AradBucharestHeuristic());
        List<Node> nodeList = aStarSearch.run(arad, bucharest);

        assertEquals("ARAD", nodeList.get(0).toString());
        assertEquals("SIBIU", nodeList.get(1).toString());
        assertEquals("RIMNICU_VILCEA", nodeList.get(2).toString());
        assertEquals("PITESTI", nodeList.get(3).toString());
        assertEquals("BUCHAREST", nodeList.get(4).toString());

    }


}
