package br.com.tbp.controller;


import br.com.tbp.GraphBuilder;
import br.com.tbp.controller.dto.GrafoDto;
import br.com.tbp.controller.dto.TopicoDto;
import br.com.tbp.model.semantic.Graph;
import br.com.tbp.model.semantic.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("algoritmo")
public class IndexController {

    @RequestMapping(value = "listarConceitos", method = RequestMethod.GET)
    public String setupForm(ModelMap model) {
        GraphBuilder graphBuilder = new GraphBuilder();
        Graph graph = graphBuilder.buildGraphFromRDF();
        model.addAttribute("topicoList", graph.getMapTopico());
        model.addAttribute("topicoDto", new TopicoDto());
        return "/algoritmo/listarConceitos";
    }

    @RequestMapping(value = "grafoConceitos", method = RequestMethod.GET)
    public String selectTopic(TopicoDto topicoDto, ModelMap model)   {
        GraphBuilder graphBuilder = new GraphBuilder();
        Graph graph = graphBuilder.buildGraphFromRDF();
        Topico topico = graph.getMapTopico().get(topicoDto.getTopicoID());
        model.addAttribute("topico", topico);
        return "/algoritmo/grafoConceitos";
    }

}
