package br.com.tbp.model.semantic;


import java.io.Serializable;

public class OA implements Serializable {


    private Integer id;
    private String rdfId;
    private String url;
    private Topico topico;

    public String getRdfId() {
        return rdfId;
    }

    public void setRdfId(String rdfId) {
        this.rdfId = rdfId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OA oa = (OA) o;

        if (id != null ? !id.equals(oa.id) : oa.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
