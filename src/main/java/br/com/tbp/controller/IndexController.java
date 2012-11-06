package br.com.tbp.controller;


import br.com.tbp.GraphBuilder;
import br.com.tbp.controller.dto.GrafoDto;
import br.com.tbp.model.semantic.Graph;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("algoritmo")
@SessionAttributes("grafoDto")
public class IndexController {

    @RequestMapping(value = "selecionarConceito.do", method = RequestMethod.GET)
    public String setupForm(ModelMap model) {
        GraphBuilder graphBuilder = new GraphBuilder();
        Graph graph = graphBuilder.buildGraphFromRDF();
        model.put("topicoList", graph.getMapTopico());
        if(!model.containsKey("grafoDto")) {
            model.put("grafoDto", new GrafoDto());
        }
        return "/algoritmo/selecionarConceito";
    }

    @RequestMapping(value = "selecionarConceito.do", method = RequestMethod.POST)
    public String selectTopic(@ModelAttribute GrafoDto grafoDto, @RequestParam String topicoID, ModelMap model)   {
        GraphBuilder graphBuilder = new GraphBuilder();
        Graph graph = graphBuilder.buildGraphFromRDF();
        grafoDto.setTopico(graph.getMapTopico().get(topicoID));
        model.put("grafoDto", new GrafoDto());
        return "/algoritmo/caminho";
    }





}
