package br.com.tbp


import br.com.tbp.model.semantic.DisciplinaNode
import br.com.tbp.model.Edge
import br.com.tbp.model.semantic.Topico

class GraphBuilder {

    public static String ONTOLOGY_PREFIX = 'http://www.semanticweb.org/ontologies/2012/9/objetos.owl#'

    public List<br.com.tbp.model.Node> buildGraphFromRDF() {

        Map<String, Topico> topicoMap = buildTopicoMap()

        // reading the rdf graph...
        def file = new File(getAbsolutePath('/objetos.owl'))
        def content = file.text
        // lets parse the rdf graph
        def xml = new XmlSlurper().parseText(content)
        def entities = xml.NamedIndividual
        def disciplinas = entities.findAll {it.type.@resource.text().equals(ONTOLOGY_PREFIX + 'Disciplina') }

        List<Node> nodeList = new ArrayList<Node>()
        Map<String, Node> nodeMap = new HashMap<String, Node>()

        // creating nodes based on the rdf graph so as to use in the application
        disciplinas.eachWithIndex { disciplina, i ->
           def node = new DisciplinaNode()
           // maybe we should think better of this id..
           node.setId(i)
           node.setNome(disciplina.nome.text())
           node.setRdfId(disciplina.@about.text())
           nodeList.add(node)
           nodeMap.put(node.rdfId, node)

           // building topico list...
           disciplina.temTopico.each { topico ->
               def t = topicoMap.get(topico.@resource.text())
               node.getTopicoList().add(t)
           }
        }

        def disciplinasWithEhBase  = disciplinas.findAll {it.ehBase != null && it.ehBase.size() > 0}

        // creating edges based on the rdf graph so as to use in the application
        disciplinasWithEhBase.each{ disciplina ->
            def node_1 = nodeMap.get(disciplina.@about.text())
            disciplina.ehBase.each { ehBase ->
                def node_2 = nodeMap.get(ehBase.@resource.text())
                Edge edge = new Edge(node_1, node_2, 0d)
                node_1.getEdgeList().add(edge)
                node_2.getEdgeList().add(edge)
            }
        }
        return nodeList
    }

    def buildTopicoMap() {
        def file = new File(getAbsolutePath('/objetos.owl'))
        def content = file.text
        def xml = new XmlSlurper().parseText(content)
        def entities = xml.NamedIndividual
        def topicos = entities.findAll {it.type.@resource.text().equals(ONTOLOGY_PREFIX + 'Topico') }
        Map<String, Topico> topicoMap = new HashMap<String,Topico>();

        topicos.eachWithIndex { topico, i ->
            def t = new Topico();
            t.setId(i)
            t.setNome(topico.nome.text())
            t.setOrdem(Integer.parseInt(topico.ordem.text()))
            t.setRdfId(topico.@about.text())
            topicoMap.put(t.rdfId, t)
        }

        return topicoMap
    }


    // this code is not supposed to be here..
    private String getAbsolutePath(String relativePath) {
        String path = getClass().getResource(relativePath).getPath().toString();
        path = path.replace("file:/", "");
        path = path.replace("%20", " ");
        path = path.replace("/", File.separator);
        return path;
    }

}