package br.com.tbp


import br.com.tbp.model.semantic.Disciplina
import br.com.tbp.model.Edge
import br.com.tbp.model.semantic.Topico

import br.com.tbp.model.semantic.Graph
import br.com.tbp.model.semantic.OA


class GraphBuilder {

    public static final String ONTOLOGY_PREFIX = 'http://www.semanticweb.org/ontologies/2012/9/objetos.owl#'
    public static final String EH_BASE_DISCIPLINA = ONTOLOGY_PREFIX + "ehBaseDisciplina"
    public static final String EH_BASE_TOPICO = ONTOLOGY_PREFIX + "ehBaseTopico"
    private static final String TEST_ONTOLOGY = '/objetos.owl'
//    private static final String ONTOLOGY = '/web_semantica.owl'
    private static final String ONTOLOGY = '/programacao.owl'
    private boolean testMode = false;

    public Graph buildGraphFromRDF() {
        def xml = getRDFObject()
        Map<String,OA> oaMap = buildOAMap(xml);
        Map<String, Topico> topicoMap = buildTopicoMap(xml, oaMap)
        Map<String, Disciplina> disciplinaMap = buildDisciplinaMap(xml, topicoMap)
        buildDisciplinaDependencies(xml, disciplinaMap)
        buildTopicoDependencies(xml, topicoMap)
        Graph graph = new Graph()
        graph.setMapDisciplina(disciplinaMap)
        graph.setMapTopico(topicoMap)
        return graph
    }

    public void setTestMode(boolean mode) {
        this.testMode = mode;
    }

    def buildOAMap(def xml) {
        def entities = xml.NamedIndividual
        def oas = entities.findAll {it.type.@resource.text().equals(ONTOLOGY_PREFIX + 'OA') }
        Map<String,OA> oaMap = new HashMap<String, OA>();

        oas.eachWithIndex { o, i ->
            def oa = new OA();
            oa.setId(i)
            oa.setUrl(o.url.text())
            oa.setRdfId(o.@about.text())
            oaMap.put(oa.rdfId, oa)
        }
        return oaMap
    }

    def buildTopicoMap(def xml, def oaMap) {
        def entities = xml.NamedIndividual
        def topicos = entities.findAll {it.type.@resource.text().equals(ONTOLOGY_PREFIX + 'Topico') }
        Map<String, Topico> topicoMap = new HashMap<String,Topico>();

        topicos.eachWithIndex { topico, i ->
            def t = new Topico();
            t.setId(i)
            t.setNome(topico.nome.text())
            t.setRdfId(topico.@about.text())
            topicoMap.put(t.rdfId, t)
            if(topico.root != null && topico.root != '') {
                t.root = Boolean.valueOf(topico.root.text())
            }
            if(topico.goal != null && topico.goal != '') {
                t.goal = Boolean.valueOf(topico.goal.text())
            }
            topico.temOA.each { obj ->
                def objeto = oaMap.get(obj.@resource.text())
                t.getOaList().add(objeto)
                objeto.topico = t
            }
        }
        return topicoMap
    }

    def buildDisciplinaMap(def xml, def topicoMap) {
        def entities = xml.NamedIndividual
        def disciplinas = entities.findAll {it.type.@resource.text().equals(ONTOLOGY_PREFIX + 'Disciplina') }

        List<Disciplina> nodeList = new ArrayList<Disciplina>()
        Map<String, Disciplina> disciplinaMap = new HashMap<String, Disciplina>()

        disciplinas.eachWithIndex { disciplina, i ->
            def node = new Disciplina()
            node.setId(i)
            node.setNome(disciplina.nome.text())
            node.setRdfId(disciplina.@about.text())
            nodeList.add(node)
            disciplinaMap.put(node.rdfId, node)
            disciplina.temTopico.each { topico ->
                def t = topicoMap.get(topico.@resource.text())
                node.getTopicoList().add(t)
                t.disciplina = node
            }
        }
        return disciplinaMap
    }

    def buildNodeDistance(def xml, def source, def target, def relationProperty) {
        def entities = xml.Axiom
        def axiom = entities.find {it.annotatedTarget.@resource.text().equals(target) && it.annotatedSource.@resource.text().equals(source) && it.annotatedProperty.@resource.text().equals(relationProperty)}
        return Integer.parseInt(axiom.distancia.text())
    }

    def buildDisciplinaDependencies(def xml, def disciplinaMap) {
        def entities = xml.NamedIndividual
        def disciplinaList = entities.findAll {it.type.@resource.text().equals(ONTOLOGY_PREFIX + 'Disciplina') && it.ehBaseDisciplina != null && it.ehBaseDisciplina.size() > 0 }
        disciplinaList.each{ disciplina ->
            def node_1 = disciplinaMap.get(disciplina.@about.text())
            disciplina.ehBaseDisciplina.each { ehBase ->
                def node_2 = disciplinaMap.get(ehBase.@resource.text())
                Edge edge = new Edge(node_1, node_2, buildNodeDistance(xml, node_1.rdfId, node_2.rdfId, EH_BASE_DISCIPLINA))
                node_1.getEdgeList().add(edge)
                node_2.getEdgeList().add(edge)
            }
        }
    }

    def buildTopicoDependencies(def xml, def topicoMap) {
        def entities = xml.NamedIndividual
        def topicoList = entities.findAll {it.type.@resource.text().equals(ONTOLOGY_PREFIX + 'Topico') && it.ehBaseTopico != null && it.ehBaseTopico.size() > 0 }
        topicoList.each{ topico ->
            def node_1 = topicoMap.get(topico.@about.text())
            topico.ehBaseTopico.each { ehBase ->
                def node_2 = topicoMap.get(ehBase.@resource.text())
                Edge edge = new Edge(node_1, node_2, buildNodeDistance(xml, node_1.rdfId, node_2.rdfId, EH_BASE_TOPICO))
                node_1.getEdgeList().add(edge)
                node_2.getEdgeList().add(edge)
            }
        }
    }

    def getRDFObject() {
        def file = null
        if(testMode) {
            file = new File(getAbsolutePath(TEST_ONTOLOGY))
        } else {
            file = new File(getAbsolutePath(ONTOLOGY))
        }
        def xml = new XmlSlurper().parseText(file.text)
        return xml
    }


    // this code is not supposed to be here..
    def getAbsolutePath(def relativePath) {
        String path = getClass().getResource(relativePath).getPath().toString();
        path = path.replace("file:/", "");
        path = path.replace("%20", " ");
        path = path.replace("/", File.separator);
        return path;
    }

}