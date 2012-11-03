package br.com.tbp.support;

import br.com.tbp.model.Node;

public class AradBucharestNode extends Node {
    private AradBucharestEnum desc;

    public AradBucharestNode(AradBucharestEnum enumAradBucharest) {
        super();
        this.desc = enumAradBucharest;
        setId(enumAradBucharest.id());
    }

    public AradBucharestEnum getDesc() {
        return desc;
    }

    public void setDesc(AradBucharestEnum desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc.name();
    }
}
