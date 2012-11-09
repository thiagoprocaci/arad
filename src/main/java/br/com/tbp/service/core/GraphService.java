package br.com.tbp.service.core;

import br.com.tbp.GraphBuilder;
import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;
import br.com.tbp.model.semantic.Disciplina;
import br.com.tbp.model.semantic.Graph;
import br.com.tbp.model.semantic.Topico;
import br.com.tbp.search.Algorithm;
import br.com.tbp.search.astar.AStarSearch;
import br.com.tbp.search.astar.OAHeuristic;
import br.com.tbp.search.dijkstra.DijkstraSearch;
import br.com.tbp.service.IGraphService;
import br.com.tbp.service.dto.TreeDto;
import br.com.tbp.support.GraphUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GraphService implements IGraphService {

    @Override
    public Graph getGraph() {
        GraphBuilder graphBuilder = new GraphBuilder();
        Graph graph = graphBuilder.buildGraphFromRDF();
        return graph;
    }

    @Override
    public List<TreeDto> buildDijkstraTree(String topicoRdfId) {
        Graph graph = getGraph();
        return buildTree(topicoRdfId, new DijkstraSearch(), graph);
    }

    @Override
    public List<TreeDto> buildAStarTree(String topicoRdfId, Map<String, Integer> weightMap) {
        Graph graph = getGraph();
        setupNodeWeight(graph, weightMap);
        AStarSearch algorithm = new AStarSearch();
        algorithm.setHeuristic(new OAHeuristic());
        return buildTree(topicoRdfId, algorithm, graph);
    }

    private void setupNodeWeight(Graph graph, Map<String, Integer> weightMap) {
        for (String key : graph.getMapDisciplina().keySet()) {
            if (weightMap.containsKey(key)) {
                graph.getMapDisciplina().get(key).setNodeWeight(weightMap.get(key));
            }
        }
        for (String key : graph.getMapTopico().keySet()) {
            if (weightMap.containsKey(key)) {
                graph.getMapTopico().get(key).setNodeWeight(weightMap.get(key));
            }
        }
    }

    private List<TreeDto> buildTree(String topicoRdfId, Algorithm algorithm, Graph graph) {
        Topico topico = graph.getMapTopico().get(topicoRdfId);
        Disciplina disciplina = topico.getDisciplina();
        Disciplina disciplinaRoot = GraphUtil.getDisciplinaRoot(graph.getMapDisciplina().values());
        List<Node> disciplinaList = algorithm.run(disciplinaRoot, disciplina);
        List<TreeDto> treeList = new ArrayList<TreeDto>();
        Topico topicoRoot = null;
        Topico topicoGoal = null;
        TreeDto treeDto = null;
        for (Node d : disciplinaList) {
            disciplina = (Disciplina) d;
            treeDto = new TreeDto();
            treeDto.setDisciplina(disciplina);
            topicoRoot = GraphUtil.getTopicoRoot(disciplina.getTopicoList());
            if (disciplina.getTopicoList().contains(topico)) {
                topicoGoal = topico;
            } else {
                topicoGoal = GraphUtil.getTopicoGoal(disciplina.getTopicoList());
            }
            if (topicoRoot != null && topicoGoal != null) {
                treeDto.setTopicoList(algorithm.run(topicoRoot, topicoGoal));
            }
            treeList.add(treeDto);
        }
        return treeList;
    }
}
