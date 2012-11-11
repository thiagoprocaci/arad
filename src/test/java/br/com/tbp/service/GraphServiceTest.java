package br.com.tbp.service;


import br.com.tbp.GraphBuilder;
import br.com.tbp.model.semantic.Disciplina;
import br.com.tbp.model.semantic.Graph;
import br.com.tbp.model.semantic.Topico;
import br.com.tbp.service.core.GraphService;
import br.com.tbp.service.dto.TreeDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GraphServiceTest {
    private GraphService graphService;

    @Before
    public void doBefore() {
        graphService = new GraphService();
        GraphBuilder graphBuilder = new GraphBuilder();
        graphBuilder.setTestMode(true);
        graphService.setGraphBuilder(graphBuilder);
    }


    @Test
    public void getGraphTest() {
        Graph graph = graphService.getGraph();

        assertNotNull(graph);
        assertNotNull(graph.getMapDisciplina());
        assertEquals(4, graph.getMapDisciplina().size());

        List<Integer> idList = new ArrayList<Integer>();
        Collection<Disciplina> disciplinaList = graph.getMapDisciplina().values();

        for (Disciplina disciplina : disciplinaList) {
            assertNotNull(disciplina);
            assertNotNull(disciplina.getId());
            assertFalse(idList.contains(disciplina.getId()));
            idList.add(disciplina.getId());
        }

        idList = new ArrayList<Integer>();
        Collection<Topico> topicoList = graph.getMapTopico().values();

        for (Topico topico : topicoList) {
            assertNotNull(topico);
            assertNotNull(topico.getId());
            assertFalse(idList.contains(topico.getId()));
            idList.add(topico.getId());
        }
    }

    @Test
    public void buildTreeTest() {
        List<TreeDto> treeList = graphService.buildDijkstraTree(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II");
        assertNotNull(treeList);
        assertEquals(3, treeList.size());
        TreeDto treeDto = treeList.get(0);

        assertNotNull(treeDto);
        assertNotNull(treeDto.getDisciplina());
        assertNotNull(treeDto.getDisciplina().getRdfId());
        assertTrue(treeDto.getDisciplina().getRdfId().endsWith("ciencia_computacao"));
        assertNull(treeDto.getTopicoList());


        treeDto = treeList.get(1);

        assertNotNull(treeDto);
        assertNotNull(treeDto.getDisciplina());
        assertNotNull(treeDto.getDisciplina().getRdfId());
        assertTrue(treeDto.getDisciplina().getRdfId().endsWith("calculo_I"));
        assertNotNull(treeDto.getTopicoList());
        assertEquals(3, treeDto.getTopicoList().size());

        treeDto = treeList.get(2);

        assertNotNull(treeDto);
        assertNotNull(treeDto.getDisciplina());
        assertNotNull(treeDto.getDisciplina().getRdfId());
        assertTrue(treeDto.getDisciplina().getRdfId().endsWith("calculo_II"));
        assertNotNull(treeDto.getTopicoList());
        assertEquals(2, treeDto.getTopicoList().size());
    }

}
