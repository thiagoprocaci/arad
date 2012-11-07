package br.com.tbp.controller.dto;


import br.com.tbp.model.Node;
import br.com.tbp.model.semantic.Disciplina;


import java.io.Serializable;
import java.util.List;

public class TreeDto implements Serializable {
    private Disciplina disciplina;
    private List<Node> topicoList;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public List<Node> getTopicoList() {
        return topicoList;
    }

    public void setTopicoList(List<Node> topicoList) {
        this.topicoList = topicoList;
    }
}
