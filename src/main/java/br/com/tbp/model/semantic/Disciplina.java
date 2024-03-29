package br.com.tbp.model.semantic;



import br.com.tbp.model.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina extends Node implements Serializable, ISemanticNode {

    private String rdfId;
    private String nome;
    private List<Topico> topicoList = new ArrayList<Topico>();
    private Integer nodeWeight;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRdfId() {
        return rdfId;

    }

    public void setRdfId(String rdfId) {
        this.rdfId = rdfId;
    }

    public List<Topico> getTopicoList() {
        return topicoList;
    }

    public void setTopicoList(List<Topico> topicoList) {
        this.topicoList = topicoList;
    }

    public Integer getNodeWeight() {
        return nodeWeight;
    }

    public void setNodeWeight(Integer nodeWeight) {
        this.nodeWeight = nodeWeight;
    }

    @Override
    public String toString() {
        return nome;
    }
}
