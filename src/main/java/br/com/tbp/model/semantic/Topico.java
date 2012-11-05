package br.com.tbp.model.semantic;


public class Topico {

    private Integer id;
    private String rdfId;
    private String nome;
    private Integer ordem;

    public String getRdfId() {
        return rdfId;
    }

    public void setRdfId(String rdfId) {
        this.rdfId = rdfId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topico topico = (Topico) o;

        if (id != null ? !id.equals(topico.id) : topico.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
