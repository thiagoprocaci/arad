package br.com.tbp.model.semantic;


import java.io.Serializable;
import java.util.Map;

public class Graph implements Serializable {
    private Map<String, Disciplina> mapDisciplina;
    private Map<String, Topico> mapTopico;

    public Map<String, Disciplina> getMapDisciplina() {
        return mapDisciplina;
    }

    public void setMapDisciplina(Map<String, Disciplina> mapDisciplina) {
        this.mapDisciplina = mapDisciplina;
    }

    public Map<String, Topico> getMapTopico() {
        return mapTopico;
    }

    public void setMapTopico(Map<String, Topico> mapTopico) {
        this.mapTopico = mapTopico;
    }
}
