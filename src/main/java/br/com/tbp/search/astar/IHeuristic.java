package br.com.tbp.search.astar;

import br.com.tbp.model.Node;

/**
 * Interface que define a heuristica que sera usada no "a start search".
 * A implementancao da heuristica deve ser injetada no algoritmo.
 */
public interface IHeuristic {

    /**
     *
     * @param start no inicial
     * @param goal no destino
     * @return Retorna estimativa de um no inicial ate um no destino
     */
    double estimate(Node start, Node goal);


}
