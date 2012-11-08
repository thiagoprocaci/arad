package br.com.tbp.service;


import br.com.tbp.model.semantic.Graph;
import br.com.tbp.service.dto.TreeDto;

import java.util.List;
import java.util.Map;

public interface IGraphService {

    Graph getGraph();

    List<TreeDto> buildDijkstraTree(String topicoRdfId);

    List<TreeDto> buildAStarTree(String topicoRdfId, Map<String, Integer> weightMap);
}
