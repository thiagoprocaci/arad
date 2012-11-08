package br.com.tbp.search;


import br.com.tbp.model.Node;

import java.util.List;

public interface Algorithm {
    List<Node> run(Node start, Node goal);
}
