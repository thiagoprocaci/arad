package br.com.tbp.model.semantic;


import br.com.tbp.model.Node;

public class Topico extends Node {
    private String rdfId;
    private String nome;

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

    @Override
    public String toString() {
        return nome;
    }
}
