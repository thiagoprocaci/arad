package br.com.tbp.controller.dto;


import java.io.Serializable;

public class TopicoDto implements Serializable {
    private String topicoID;

    public String getTopicoID() {
        return topicoID;
    }

    public void setTopicoID(String topicoID) {
        this.topicoID = topicoID;
    }
}
