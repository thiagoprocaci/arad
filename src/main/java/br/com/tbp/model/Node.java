package br.com.tbp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    // adicionar atributos para ser usado na heuristica (subclasses?)
    private int id;
    private Set<Edge> edgeList = new HashSet<Edge>();

    public Set<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Set<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // fazer cache dos sucessores e antecessores
    public List<Node> getSuccessors() {
        List<Node> nodeList = new ArrayList<Node>();
        for (Edge edge : edgeList) {
            if (this.equals(edge.getSrc())) {
                nodeList.add(edge.getDest());
            }
        }
        return nodeList;
    }

    public List<Node> getAntecessors() {
        List<Node> nodeList = new ArrayList<Node>();
        for (Edge edge : edgeList) {
            if (this.equals(edge.getDest())) {
                nodeList.add(edge.getSrc());
            }
        }
        return nodeList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Node [id=" + id + "]";
    }
}
