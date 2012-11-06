package br.com.tbp.controller.dto;


import br.com.tbp.model.semantic.Topico;

import java.io.Serializable;

public class GrafoDto implements Serializable {
    private String topicoID;
    private Topico topico;

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public String getTopicoID() {
        return topicoID;
    }

    public void setTopicoID(String topicoID) {
        this.topicoID = topicoID;
    }
}
