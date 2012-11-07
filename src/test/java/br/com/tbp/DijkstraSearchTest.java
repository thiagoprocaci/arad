package br.com.tbp;


import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;
import br.com.tbp.search.dijkstra.DijkstraSearch;
import br.com.tbp.support.AradBucharestEnum;
import br.com.tbp.support.AradBucharestGraphBuilder;
import br.com.tbp.support.AradBucharestHeuristic;
import br.com.tbp.support.AradBucharestNode;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class DijkstraSearchTest {

    private DijkstraSearch dijkstraSearch;

    @Before
    public void doBefore() {
        dijkstraSearch = new DijkstraSearch();
    }

    @Test
    public void initMapTest() {
        AradBucharestGraphBuilder aradBucharestGraphBuilder = new AradBucharestGraphBuilder();
        Map<Integer, Node> map = aradBucharestGraphBuilder.buildAradBucharestGraph();
        Node arad = map.get(AradBucharestEnum.ARAD.id());

        Map<Integer, Node> map2 = new HashMap<Integer, Node>();
        dijkstraSearch.initNodeMap(arad, map2);

        assertEquals(map.size(), map2.size());
        for(Integer key: map.keySet()) {
            assertEquals(map.get(key), map2.get(key));
        }
    }

    @Test
    public void initDijsktraSearchTest() {
        AradBucharestGraphBuilder aradBucharestGraphBuilder = new AradBucharestGraphBuilder();
        Map<Integer, Node> map = aradBucharestGraphBuilder.buildAradBucharestGraph();
        Node start = map.get(AradBucharestEnum.ARAD.id());

        Map<Node, Double> distance = new HashMap<Node, Double>();
        Map<Node, Node> pred = new HashMap<Node, Node>();

        dijkstraSearch.initDijsktraSearch(start, map, distance, pred);

        assertEquals(map.size(), distance.size());
        assertEquals(map.size(), pred.size());

        Node node = null;
        int countFirstIf = 0;
        int countElse = 0;
        for(Integer key: map.keySet()) {
            node = map.get(key);
            assertNotNull(distance.get(node));
            if(start.equals(node)) {
                assertEquals(0, distance.get(node).intValue());
                countFirstIf++;
            } else {
                assertTrue(distance.get(node).equals(Double.MAX_VALUE));
                countElse++;
            }
            assertNull(pred.get(node));
        }
        assertEquals(1, countFirstIf);
        assertEquals(map.size() - 1, countElse);
    }

    @Test
    public void extractMinTest() {
        AradBucharestGraphBuilder aradBucharestGraphBuilder = new AradBucharestGraphBuilder();
        Map<Integer, Node> map = aradBucharestGraphBuilder.buildAradBucharestGraph();
        Node start = map.get(AradBucharestEnum.ARAD.id());

        Map<Node, Double> distance = new HashMap<Node, Double>();
        Map<Node, Node> pred = new HashMap<Node, Node>();

        // the method was tested before..
        dijkstraSearch.initDijsktraSearch(start, map, distance, pred);

        Set<Node> nodeSet = new HashSet<Node>(map.values());
        int size = nodeSet.size();

        assertEquals(start, dijkstraSearch.extractMin(nodeSet,distance));
        assertEquals(size - 1, nodeSet.size());
    }


    @Test
    public void runTest() {
        AradBucharestGraphBuilder aradBucharestGraphBuilder = new AradBucharestGraphBuilder();
        Map<Integer, Node> map = aradBucharestGraphBuilder.buildAradBucharestGraph();
        Node arad = map.get(AradBucharestEnum.ARAD.id());
        Node bucharest = map.get(AradBucharestEnum.BUCHAREST.id());

        List<Node> nodeList = dijkstraSearch.run(arad, bucharest);

        assertEquals("ARAD", nodeList.get(0).toString());
        assertEquals("SIBIU", nodeList.get(1).toString());
        assertEquals("RIMNICU_VILCEA", nodeList.get(2).toString());
        assertEquals("PITESTI", nodeList.get(3).toString());
        assertEquals("BUCHAREST", nodeList.get(4).toString());


    }



}
