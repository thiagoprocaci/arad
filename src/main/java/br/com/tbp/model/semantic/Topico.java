package br.com.tbp.model.semantic;


import br.com.tbp.GraphBuilder;
import br.com.tbp.model.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Topico extends Node implements Serializable, ISemanticNode {
    private String rdfId;
    private String nome;
    private Disciplina disciplina;
    private boolean root = false;
    private boolean goal = false;
    private boolean selecionado = false;
    private Integer nodeWeight;
    private List<OA> oaList = new ArrayList<OA>();

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public Disciplina getDisciplina() {
        return disciplina;

    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getRdfId() {
        return rdfId;
    }

    public void setRdfId(String rdfId) {
        this.rdfId = rdfId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public boolean isGoal() {
        return goal;
    }

    public void setGoal(boolean goal) {
        this.goal = goal;
    }

    public Integer getNodeWeight() {
        return nodeWeight;
    }

    public void setNodeWeight(Integer nodeWeight) {
        this.nodeWeight = nodeWeight;
    }

    public String getShortRdfId() {
        if(rdfId != null) {
           return rdfId.replace(GraphBuilder.ONTOLOGY_PREFIX, "");
        }
        return null;
    }

    public List<OA> getOaList() {
        return oaList;
    }

    public void setOaList(List<OA> oaList) {
        this.oaList = oaList;
    }

    @Override
    public String toString() {
        return nome;
    }
}
