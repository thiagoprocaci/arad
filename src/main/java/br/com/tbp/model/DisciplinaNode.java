package br.com.tbp.model;


import br.com.tbp.GraphBuilder;

public class DisciplinaNode extends Node {

    private String rdfId;
    private String nome;

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


    @Override
    public String toString() {
        return nome;
    }
}
