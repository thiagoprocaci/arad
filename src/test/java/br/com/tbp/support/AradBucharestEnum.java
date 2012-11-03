package br.com.tbp.support;


public enum AradBucharestEnum {
    // enum com os nomes das cidades
        ARAD(1),
        BUCHAREST(2),
        CRAIOVA(3),
        DOBRETA(4),
        EFORIE(5),
        FAGARAS(6),
        GIURGIU(7),
        HIRSOVA(8),
        IASI(9),
        LUGOJ(10),
        MEHADIA(11),
        NEAMT(12),
        ORADEA(13),
        PITESTI(14),
        RIMNICU_VILCEA(15),
        SIBIU(16),
        TIMISOARA(17),
        URZICENI(18),
        VASLUI(19),
        ZERIND(20);

    private Integer id;

    AradBucharestEnum(Integer id) {
        this.id = id;
    }

    public Integer id() {
       return id;
    }
}
