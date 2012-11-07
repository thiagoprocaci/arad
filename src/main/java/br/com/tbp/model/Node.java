package br.com.tbp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    // adicionar atributos para ser usado na heuristica (subclasses?)
    private Integer id;
    private Set<Edge> edgeList = new HashSet<Edge>();

    public Set<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(Set<Edge> edgeList) {
        this.edgeList = edgeList;
    }

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }


    // fazer cache dos sucessores e antecessores
    public List<Node> getSuccessors() {
        List<Node> nodeList = new ArrayList<Node>();
        for (Edge edge : edgeList) {
            if (this.id.equals(edge.getSrc().id)) {
                nodeList.add(edge.getDest());
            }
        }
        return nodeList;
    }

    public List<Node> getAntecessors() {
        List<Node> nodeList = new ArrayList<Node>();
        for (Edge edge : edgeList) {
            if (this.id.equals(edge.getDest().id)) {
                nodeList.add(edge.getSrc());
            }
        }
        return nodeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (id != null ? !id.equals(node.id) : node.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Node [id=" + id + "]";
    }
}
