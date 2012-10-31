package br.com.tbp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;
import br.com.tbp.search.AStarSearch;
import br.com.tbp.support.AradBucharestEnum;
import br.com.tbp.support.AradBucharestHeuristic;
import br.com.tbp.support.AradBucharestNode;

public class AStarSearchTest {
    private AStarSearch aStarSearch = new AStarSearch();

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

        assertTrue(10 == aStarSearch.distBetween(node1, node2));
    }

    @Test
    public void AradBucharestTest() {
        Node arad = new AradBucharestNode(AradBucharestEnum.ARAD, 1);
        Node bucharest = new AradBucharestNode(AradBucharestEnum.BUCHAREST, 2);
        Node craiova = new AradBucharestNode(AradBucharestEnum.CRAIOVA, 3);
        Node dobreta = new AradBucharestNode(AradBucharestEnum.DOBRETA, 4);
        Node eforie = new AradBucharestNode(AradBucharestEnum.EFORIE, 5);
        Node fagaras = new AradBucharestNode(AradBucharestEnum.FAGARAS, 6);
        Node giurgiu = new AradBucharestNode(AradBucharestEnum.GIURGIU, 7);
        Node hirsova = new AradBucharestNode(AradBucharestEnum.HIRSOVA, 8);
        Node iasi = new AradBucharestNode(AradBucharestEnum.IASI, 9);
        Node lugoj = new AradBucharestNode(AradBucharestEnum.LUGOJ, 10);
        Node mehadia = new AradBucharestNode(AradBucharestEnum.MEHADIA, 11);
        Node neamt = new AradBucharestNode(AradBucharestEnum.NEAMT, 12);
        Node oradea = new AradBucharestNode(AradBucharestEnum.ORADEA, 13);
        Node pitesti = new AradBucharestNode(AradBucharestEnum.PITESTI, 14);
        Node rimnicu_vilcea = new AradBucharestNode(AradBucharestEnum.RIMNICU_VILCEA, 15);
        Node sibiu = new AradBucharestNode(AradBucharestEnum.SIBIU, 16);
        Node timisoara = new AradBucharestNode(AradBucharestEnum.TIMISOARA, 17);
        Node urziceni = new AradBucharestNode(AradBucharestEnum.URZICENI, 18);
        Node vaslui = new AradBucharestNode(AradBucharestEnum.VASLUI, 19);
        Node zerind = new AradBucharestNode(AradBucharestEnum.ZERIND, 20);

        createEdge(arad, zerind, 75d);
        createEdge(arad, timisoara, 118d);
        createEdge(arad, sibiu, 140d);
        createEdge(zerind, oradea, 71d);
        createEdge(oradea, sibiu, 151d);
        createEdge(timisoara, lugoj, 111d);
        createEdge(lugoj, mehadia, 70d);
        createEdge(mehadia, dobreta, 75d);
        createEdge(dobreta, craiova, 120d);
        createEdge(craiova, rimnicu_vilcea, 146d);
        createEdge(craiova, pitesti, 138d);
        createEdge(sibiu, rimnicu_vilcea, 80d);
        createEdge(sibiu, fagaras, 99d);
        createEdge(rimnicu_vilcea, pitesti, 97d);
        createEdge(fagaras, bucharest, 211d);
        createEdge(pitesti, bucharest, 101d);
        createEdge(bucharest, giurgiu, 90d);
        createEdge(bucharest, urziceni, 85d);
        createEdge(urziceni, hirsova, 98d);
        createEdge(hirsova, eforie, 86d);
        createEdge(urziceni, vaslui, 142d);
        createEdge(vaslui, iasi, 92d);
        createEdge(iasi, neamt, 87d);

        aStarSearch.setHeuristic(new AradBucharestHeuristic());
        assertEquals("ARAD -> SIBIU -> RIMNICU_VILCEA -> PITESTI -> BUCHAREST", aStarSearch.run(arad, bucharest));
    }

    private void createEdge(Node node1, Node node2, Double distance) {
        Edge edge = new Edge(node1, node2, distance);
        node1.getEdgeList().add(edge);
        node2.getEdgeList().add(edge);
        Edge edge2 = new Edge(node2, node1, distance);
        node1.getEdgeList().add(edge2);
        node2.getEdgeList().add(edge2);
    }
}
