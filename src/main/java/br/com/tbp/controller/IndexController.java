package br.com.tbp.controller;


import br.com.tbp.GraphBuilder;
import br.com.tbp.controller.dto.TopicoDto;
import br.com.tbp.service.IGraphService;
import br.com.tbp.service.dto.TreeDto;
import br.com.tbp.model.semantic.Graph;
import br.com.tbp.model.semantic.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("algoritmo")
public class IndexController {

    @Autowired
    private IGraphService graphService;


    @RequestMapping(value = "listarConceitos", method = RequestMethod.GET)
    public String setupForm(ModelMap model) {
        Graph graph = graphService.getGraph();
        Map<String, Topico> topicoMap = graph.getMapTopico();
        Map<String, Topico> topicoList = new HashMap<String, Topico>();
       // retira os roots para controle da ontologia
        for (String key: topicoMap.keySet()) {
            if(!topicoMap.get(key).isRoot() && !topicoMap.get(key).isGoal()) {
                topicoList.put(key, topicoMap.get(key));
            }
        }
        model.addAttribute("topicoList", topicoList);
        model.addAttribute("topicoDto", new TopicoDto());
        return "/algoritmo/listarConceitos";
    }

    @RequestMapping(value = "grafoConceitos", method = RequestMethod.GET)
    public String dijkstraSearch(TopicoDto topicoDto, ModelMap model) {
        List<TreeDto> treeList = graphService.buildDijkstraTree(topicoDto.getTopicoID());
        TreeDto lastTreeDto = treeList.get(treeList.size() - 1);
        Topico lastTopico = (Topico) lastTreeDto.getTopicoList().get(lastTreeDto.getTopicoList().size() - 1);
        model.addAttribute("treeList", treeList);
        model.addAttribute("topico", lastTopico);
        return "/algoritmo/grafoConceitos";
    }

    @RequestMapping(value = "aStartSearch", method = RequestMethod.GET)
    public String aStartSearch(@RequestParam String topicoId, HttpServletRequest request, ModelMap model) {
        Map<String, Integer> weightMap = new HashMap<String, Integer>();
        String key = null;
        Integer weight = null;
        String[] array = null;
        for (Object o : request.getParameterMap().keySet()) {
            if ((o instanceof String) && (o != null)) {
                key = (String) o;
                if (key.contains(GraphBuilder.ONTOLOGY_PREFIX)) {
                    try {
                        array = (String[]) request.getParameterMap().get(o);
                        weight = Integer.parseInt(array[0]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        weight = 1;
                    }
                    weightMap.put(key, weight);
                }
            }
        }
        List<TreeDto> treeList = graphService.buildAStarTree(topicoId, weightMap);
        TreeDto lastTreeDto = treeList.get(treeList.size() - 1);
        Topico lastTopico = (Topico) lastTreeDto.getTopicoList().get(lastTreeDto.getTopicoList().size() - 1);
        model.addAttribute("treeList", treeList);
        model.addAttribute("topico", lastTopico);
        return "/algoritmo/grafoConceitosCust";
    }


}
