package br.com.tbp.model.semantic;



public interface ISemanticNode {

    String getNome();

    void setNome(String nome);

    String getRdfId();

    void setRdfId(String rdfId);

    Integer getNodeWeight();

    void setNodeWeight(Integer weight);
}
