package br.com.tbp;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;

public class GraphTest {
    private Node node1;
    private Node node2;
    private Node node3;
    private Node node4;
    private Node node5;

    private Edge edge12;
    private Edge edge21;
    private Edge edge24;
    private Edge edge14;
    private Edge edge23;
    private Edge edge45;
    private Edge edge35;
    private Edge edge53;

    @Before
    public void doBefore() {
        node1 = new Node();
        node2 = new Node();
        node3 = new Node();
        node4 = new Node();
        node5 = new Node();
        node1.setId(1);
        node2.setId(2);
        node3.setId(3);
        node4.setId(4);
        node5.setId(5);

        edge12 = new Edge();
        edge21 = new Edge();
        edge24 = new Edge();
        edge14 = new Edge();
        edge23 = new Edge();
        edge45 = new Edge();
        edge35 = new Edge();
        edge53= new Edge();

        edge12.setSrc(node1);
        edge12.setDest(node2);
        node1.getEdgeList().add(edge12);
        node2.getEdgeList().add(edge12);
        edge12.setDistance(1d);

        edge14.setSrc(node1);
        edge14.setDest(node4);
        node1.getEdgeList().add(edge14);
        node4.getEdgeList().add(edge14);

        edge21.setSrc(node2);
        edge21.setDest(node1);
        node2.getEdgeList().add(edge21);
        node1.getEdgeList().add(edge21);

        edge23.setSrc(node2);
        edge23.setDest(node3);
        node2.getEdgeList().add(edge23);
        node3.getEdgeList().add(edge23);

        edge24.setSrc(node2);
        edge24.setDest(node4);
        node2.getEdgeList().add(edge24);
        node4.getEdgeList().add(edge24);

        edge35.setSrc(node3);
        edge35.setDest(node5);
        node3.getEdgeList().add(edge35);
        node5.getEdgeList().add(edge35);

        edge45.setSrc(node4);
        edge45.setDest(node5);
        node4.getEdgeList().add(edge45);
        node5.getEdgeList().add(edge45);

        edge53.setSrc(node5);
        edge53.setDest(node3);
        node5.getEdgeList().add(edge53);
        node3.getEdgeList().add(edge53);
    }

    @Test
    public void testNodeId() {
        assertEquals(1, node1.getId().intValue());
        assertEquals(2, node2.getId().intValue());
        assertEquals(3, node3.getId().intValue());
        assertEquals(4, node4.getId().intValue());
        assertEquals(5, node5.getId().intValue());
    }

    @Test
    public void testEdgeSrc() {
        assertEquals(node1, edge12.getSrc());
        assertEquals(node1, edge14.getSrc());
        assertEquals(node2, edge21.getSrc());
        assertEquals(node2, edge23.getSrc());
        assertEquals(node2, edge24.getSrc());
        assertEquals(node3, edge35.getSrc());
        assertEquals(node4, edge45.getSrc());
        assertEquals(node5, edge53.getSrc());
    }

    @Test
    public void testEdgeDest() {
        assertEquals(node2, edge12.getDest());
        assertEquals(node4, edge14.getDest());
        assertEquals(node1, edge21.getDest());
        assertEquals(node3, edge23.getDest());
        assertEquals(node4, edge24.getDest());
        assertEquals(node5, edge35.getDest());
        assertEquals(node5, edge45.getDest());
        assertEquals(node3, edge53.getDest());
    }

    @Test
    public void testSuccessorsNode1() {
        List<Node> nodeList = node1.getSuccessors();
        assertNotNull(nodeList);
        assertEquals(2, nodeList.size());
        assertTrue(nodeList.contains(node2));
        assertTrue(nodeList.contains(node4));
        assertFalse(nodeList.contains(node1));
        assertFalse(nodeList.contains(node3));
        assertFalse(nodeList.contains(node5));
    }

    @Test
    public void testSuccessorsNode2() {
        List<Node> nodeList = node2.getSuccessors();
        assertNotNull(nodeList);
        assertEquals(3, nodeList.size());
        assertTrue(nodeList.contains(node3));
        assertTrue(nodeList.contains(node4));
        assertTrue(nodeList.contains(node1));
        assertFalse(nodeList.contains(node2));
        assertFalse(nodeList.contains(node5));
    }

    @Test
    public void testSuccessorsNode3() {
        List<Node> nodeList = node3.getSuccessors();
        assertNotNull(nodeList);
        assertEquals(1, nodeList.size());
        assertTrue(nodeList.contains(node5));
        assertFalse(nodeList.contains(node4));
        assertFalse(nodeList.contains(node1));
        assertFalse(nodeList.contains(node3));
        assertFalse(nodeList.contains(node2));
    }

    @Test
    public void testSuccessorsNode4() {
        List<Node> nodeList = node4.getSuccessors();
        assertNotNull(nodeList);
        assertEquals(1, nodeList.size());
        assertTrue(nodeList.contains(node5));
        assertFalse(nodeList.contains(node4));
        assertFalse(nodeList.contains(node1));
        assertFalse(nodeList.contains(node3));
        assertFalse(nodeList.contains(node2));
    }

    @Test
    public void testSuccessorsNode5() {
        List<Node> nodeList = node5.getSuccessors();
        assertNotNull(nodeList);
        assertEquals(1, nodeList.size());
        assertTrue(nodeList.contains(node3));
        assertFalse(nodeList.contains(node4));
        assertFalse(nodeList.contains(node1));
        assertFalse(nodeList.contains(node5));
        assertFalse(nodeList.contains(node2));
    }

    @Test
    public void testAntecessorsNode1() {
        List<Node> nodeList = node1.getAntecessors();
        assertNotNull(nodeList);
        assertEquals(1, nodeList.size());
        assertTrue(nodeList.contains(node2));
        assertFalse(nodeList.contains(node4));
        assertFalse(nodeList.contains(node1));
        assertFalse(nodeList.contains(node3));
        assertFalse(nodeList.contains(node5));
    }

    @Test
    public void testAntecessorsNode2() {
        List<Node> nodeList = node2.getAntecessors();
        assertNotNull(nodeList);
        assertEquals(1, nodeList.size());
        assertTrue(nodeList.contains(node1));
        assertFalse(nodeList.contains(node4));
        assertFalse(nodeList.contains(node2));
        assertFalse(nodeList.contains(node3));
        assertFalse(nodeList.contains(node5));
    }

    @Test
    public void testAntecessorsNode3() {
        List<Node> nodeList = node3.getAntecessors();
        assertNotNull(nodeList);
        assertEquals(2, nodeList.size());
        assertTrue(nodeList.contains(node2));
        assertTrue(nodeList.contains(node5));
        assertFalse(nodeList.contains(node4));
        assertFalse(nodeList.contains(node1));
        assertFalse(nodeList.contains(node3));
    }

    @Test
    public void testAntecessorsNode4() {
        List<Node> nodeList = node4.getAntecessors();
        assertNotNull(nodeList);
        assertEquals(2, nodeList.size());
        assertTrue(nodeList.contains(node2));
        assertTrue(nodeList.contains(node1));
        assertFalse(nodeList.contains(node4));
        assertFalse(nodeList.contains(node5));
        assertFalse(nodeList.contains(node3));
    }

    @Test
    public void testAntecessorsNode5() {
        List<Node> nodeList = node5.getAntecessors();
        assertNotNull(nodeList);
        assertEquals(2, nodeList.size());
        assertTrue(nodeList.contains(node3));
        assertTrue(nodeList.contains(node4));
        assertFalse(nodeList.contains(node1));
        assertFalse(nodeList.contains(node5));
        assertFalse(nodeList.contains(node2));
    }

    @Test
    public void testDistance() {
        assertTrue(edge12.getDistance() == 1);
    }


}
