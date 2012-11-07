package br.com.tbp.model.semantic;


import br.com.tbp.model.Node;

import java.io.Serializable;

public class Topico extends Node implements Serializable {
    private String rdfId;
    private String nome;
    private Disciplina disciplina;
    private boolean root = false;
    private boolean goal = false;

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

    @Override
    public String toString() {
        return nome;
    }
}
