package br.com.tbp

import br.com.tbp.model.semantic.Topico
import br.com.tbp.support.GraphUtil

class GraphBuilderTest extends GroovyTestCase  {

    private static final String CALCULO_II = 'calculo_II'
    private static final String CALCULO_I = 'calculo_I'
    private static final String GA = 'GA'
    private static final String GA_RDF_ID = GraphBuilder.ONTOLOGY_PREFIX + GA
    private static final String CALCULO_I_RDF_ID = GraphBuilder.ONTOLOGY_PREFIX + CALCULO_I
    private static final String CALCULO_II_RDF_ID = GraphBuilder.ONTOLOGY_PREFIX + CALCULO_II
    private static final String CIENCIA_COMPUTACAO_RDF_ID = GraphBuilder.ONTOLOGY_PREFIX + CIENCIA_COMPUTACAO
    private static final String CIENCIA_COMPUTACAO = 'ciencia_computacao'



    def assertTopicoGA(def topicoMap) {

        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_GA")
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_GA")
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_GA")

        assertEquals("Topico 1 - GA", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_GA").nome)
        assertEquals("Topico 2 - GA", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_GA").nome)
        assertEquals("Topico 3 - GA", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_GA").nome)

        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_GA").id
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_GA").id
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_GA").id

        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico1_GA", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_GA").rdfId)
        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico2_GA", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_GA").rdfId)
        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico3_GA", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_GA").rdfId)

    }

    def assertTopicoCalculoI(def topicoMap) {

        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_I")
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_I")
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_I")

        assertEquals("Topico 1 - Calculo I", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_I").nome)
        assertEquals("Topico 2 - Calculo I", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_I").nome)
        assertEquals("Topico 3 - Calculo I", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_I").nome)

        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_I").id
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_I").id
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_I").id

        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_I", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_I").rdfId)
        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_I", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_I").rdfId)
        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_I", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_I").rdfId)
    }

    def assertTopicoCalculoII(def topicoMap) {

        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II")
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II")
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II")

        assertEquals("Topico 1 - Calculo II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II").nome)
        assertEquals("Topico 2 - Calculo II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II").nome)
        assertEquals("Topico 3 - Calculo II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II").nome)

        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II").id
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II").id
        assertNotNull topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II").id

        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II").rdfId)
        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II").rdfId)
        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II").rdfId)

    }

    void testGetRDFObject() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def xml = graphBuilder.getRDFObject()
        assertNotNull(xml)
        assertNotNull(xml.NamedIndividual)
    }

    void testBuildTopicoMap() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def xml = graphBuilder.getRDFObject()
        def topicoMap = graphBuilder.buildTopicoMap(xml)

        assert topicoMap != null
        assert 9 == topicoMap.size()

        assertTopicoGA(topicoMap)
        assertTopicoCalculoI(topicoMap)
        assertTopicoCalculoII(topicoMap)

    }

    void testBuildDisciplinaMap() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def xml = graphBuilder.getRDFObject()
        def topicoMap = graphBuilder.buildTopicoMap(xml)
        def disciplinaMap = graphBuilder.buildDisciplinaMap(xml, topicoMap)

        assertNotNull(disciplinaMap)
        assert disciplinaMap.size() == 4

        assertEquals("Geometria Analitica", disciplinaMap.get(GA_RDF_ID).nome)
        assertEquals(GA_RDF_ID, disciplinaMap.get(GA_RDF_ID).rdfId)
        assertNotNull(disciplinaMap.get(GA_RDF_ID).id)

        assertEquals("Calculo I", disciplinaMap.get(CALCULO_I_RDF_ID).nome)
        assertEquals(CALCULO_I_RDF_ID, disciplinaMap.get(CALCULO_I_RDF_ID).rdfId)
        assertNotNull(disciplinaMap.get(CALCULO_I_RDF_ID).id)

        assertEquals("Calculo II", disciplinaMap.get(CALCULO_II_RDF_ID).nome)
        assertEquals(CALCULO_II_RDF_ID, disciplinaMap.get(CALCULO_II_RDF_ID).rdfId)
        assertNotNull(disciplinaMap.get(CALCULO_II_RDF_ID).id)

        assertEquals("Ciencia da Computacao", disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID).nome)
        assertEquals(CIENCIA_COMPUTACAO_RDF_ID, disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID).rdfId)
        assertNotNull(disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID).id)

        assertTopicoGA(buildMapFromList(disciplinaMap.get(GA_RDF_ID).getTopicoList()))
        assertTopicoCalculoI(buildMapFromList(disciplinaMap.get(CALCULO_I_RDF_ID).getTopicoList()))
        assertTopicoCalculoII(buildMapFromList(disciplinaMap.get(CALCULO_II_RDF_ID).getTopicoList()))

        def topicoList = disciplinaMap.get(GA_RDF_ID).getTopicoList()
        topicoList.each { topico ->
             assertNotNull(topico.disciplina)
             assert  topico.disciplina == disciplinaMap.get(GA_RDF_ID)
        }

        topicoList = disciplinaMap.get(CALCULO_I_RDF_ID).getTopicoList()
        topicoList.each { topico ->
            assertNotNull(topico.disciplina)
            assert  topico.disciplina == disciplinaMap.get(CALCULO_I_RDF_ID)
        }

        topicoList = disciplinaMap.get(CALCULO_II_RDF_ID).getTopicoList()
        topicoList.each { topico ->
            assertNotNull(topico.disciplina)
            assert  topico.disciplina == disciplinaMap.get(CALCULO_II_RDF_ID)
        }
    }

    void testBuildNodeDistance() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def xml = graphBuilder.getRDFObject()
        def relation = GraphBuilder.ONTOLOGY_PREFIX + "ehBaseDisciplina"

        def distance = graphBuilder.buildNodeDistance(xml, CIENCIA_COMPUTACAO_RDF_ID, GA_RDF_ID, relation)
        assert distance == 1

        distance = graphBuilder.buildNodeDistance(xml, CIENCIA_COMPUTACAO_RDF_ID, CALCULO_I_RDF_ID, relation)
        assert distance == 1

        distance = graphBuilder.buildNodeDistance(xml, CIENCIA_COMPUTACAO_RDF_ID, CALCULO_II_RDF_ID, relation)
        assert distance == 3

        distance = graphBuilder.buildNodeDistance(xml, CALCULO_I_RDF_ID, CALCULO_II_RDF_ID, relation)
        assert distance == 1
    }

    void testBuildDisciplinaDependencies() {

        GraphBuilder graphBuilder = new GraphBuilder()
        def xml = graphBuilder.getRDFObject()
        def topicoMap = graphBuilder.buildTopicoMap(xml)
        def disciplinaMap = graphBuilder.buildDisciplinaMap(xml, topicoMap)

        graphBuilder.buildDisciplinaDependencies(xml, disciplinaMap)

        def successors = disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID).getSuccessors()
        assertNotNull(successors)
        assert 3 == successors.size()
        assert 3 ==  GraphUtil.distBetween(disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID),disciplinaMap.get(CALCULO_II_RDF_ID))
        assert 1 ==  GraphUtil.distBetween(disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID),disciplinaMap.get(CALCULO_I_RDF_ID))
        assert 1 ==  GraphUtil.distBetween(disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID),disciplinaMap.get(GA_RDF_ID))
        assertTrue(successors.contains(disciplinaMap.get(CALCULO_II_RDF_ID)))
        assertTrue(successors.contains(disciplinaMap.get(CALCULO_I_RDF_ID)))
        assertTrue(successors.contains(disciplinaMap.get(GA_RDF_ID)))

        successors = disciplinaMap.get(CALCULO_I_RDF_ID).getSuccessors()
        assertNotNull(successors)
        assert 1 == successors.size()
        assert 1 ==  GraphUtil.distBetween(disciplinaMap.get(CALCULO_I_RDF_ID),disciplinaMap.get(CALCULO_II_RDF_ID))
        assertTrue(successors.contains(disciplinaMap.get(CALCULO_II_RDF_ID)))

        successors = disciplinaMap.get(CALCULO_II_RDF_ID).getSuccessors()
        assertNotNull(successors)
        assert 0 == successors.size()

        successors = disciplinaMap.get(GA_RDF_ID).getSuccessors()
        assertNotNull(successors)
        assert 0 == successors.size()

        def antecessors = disciplinaMap.get(CALCULO_I_RDF_ID).getAntecessors()
        assertNotNull(antecessors)
        assert 1 == antecessors.size()
        assert disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID) == antecessors.get(0)

        antecessors = disciplinaMap.get(GA_RDF_ID).getAntecessors()
        assertNotNull(antecessors)
        assert 1 == antecessors.size()
        assert disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID) == antecessors.get(0)

        antecessors = disciplinaMap.get(CALCULO_II_RDF_ID).getAntecessors()
        assertNotNull(antecessors)
        assert 2 == antecessors.size()
        assert antecessors.contains(disciplinaMap.get(CALCULO_I_RDF_ID))
        assert antecessors.contains(disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID))

        antecessors = disciplinaMap.get(CIENCIA_COMPUTACAO_RDF_ID).getAntecessors()
        assertNotNull(antecessors)
        assert 0 == antecessors.size()
    }

    void testBuildTopicoDependencies() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def xml = graphBuilder.getRDFObject()
        def topicoMap = graphBuilder.buildTopicoMap(xml)

        graphBuilder.buildTopicoDependencies(xml, topicoMap)

        def topico1_calculo_I = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_I")
        def topico2_calculo_I = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_I")
        def topico3_calculo_I = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_I")

        def successors = topico1_calculo_I.getSuccessors()
        assertNotNull(successors)
        assert 2 == successors.size()
        assertTrue(successors.contains(topico2_calculo_I))
        assertTrue(successors.contains(topico3_calculo_I))
        assert 1 ==  GraphUtil.distBetween(topico1_calculo_I, topico2_calculo_I)
        assert 3 ==  GraphUtil.distBetween(topico1_calculo_I, topico3_calculo_I)

        successors = topico2_calculo_I.getSuccessors()
        assertNotNull(successors)
        assert 1 == successors.size()
        assertTrue(successors.contains(topico3_calculo_I))
        assert 1 ==  GraphUtil.distBetween(topico2_calculo_I, topico3_calculo_I)

        successors = topico3_calculo_I.getSuccessors()
        assertNotNull(successors)
        assert 0 == successors.size()

        def topico1_calculo_II = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II")
        def topico2_calculo_II = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II")
        def topico3_calculo_II = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II")

        successors = topico1_calculo_II.getSuccessors()
        assertNotNull(successors)
        assert 2 == successors.size()
        assertTrue(successors.contains(topico2_calculo_II))
        assertTrue(successors.contains(topico3_calculo_II))
        assert 1 ==  GraphUtil.distBetween(topico1_calculo_II, topico2_calculo_II)
        assert 3 ==  GraphUtil.distBetween(topico1_calculo_II, topico3_calculo_II)

        successors = topico2_calculo_II.getSuccessors()
        assertNotNull(successors)
        assert 1 == successors.size()
        assertTrue(successors.contains(topico3_calculo_II))
        assert 1 ==  GraphUtil.distBetween(topico2_calculo_II, topico3_calculo_II)

        successors = topico3_calculo_II.getSuccessors()
        assertNotNull(successors)
        assert 0 == successors.size()

        def topico1_GA = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_GA")
        def topico2_GA = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_GA")
        def topico3_GA = topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_GA")

        successors = topico1_GA.getSuccessors()
        assertNotNull(successors)
        assert 2 == successors.size()
        assertTrue(successors.contains(topico2_GA))
        assertTrue(successors.contains(topico3_GA))
        assert 1 ==  GraphUtil.distBetween(topico1_GA, topico2_GA)
        assert 3 ==  GraphUtil.distBetween(topico1_GA, topico3_GA)

        successors = topico2_GA.getSuccessors()
        assertNotNull(successors)
        assert 1 == successors.size()
        assertTrue(successors.contains(topico3_GA))
        assert 1 ==  GraphUtil.distBetween(topico2_GA, topico3_GA)

        successors = topico3_GA.getSuccessors()
        assertNotNull(successors)
        assert 0 == successors.size()

        // testar os antecessores dos topicos..

    }

    void testBuildGraphFromRDF() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def graph = graphBuilder.buildGraphFromRDF()

        assertNotNull(graph)
        assertNotNull(graph.mapDisciplina)
        assert 4 == graph.mapDisciplina.size()

        List<Integer> idList = new ArrayList<Integer>()
        def disciplinaList = graph.mapDisciplina.values()

        disciplinaList.each { disciplina ->
            assertNotNull(disciplina)
            assertNotNull(disciplina.id)
            assertFalse(idList.contains(disciplina.id))
            idList.add(disciplina.id)
        }

        idList = new ArrayList<Integer>()
        def topicoList = graph.getMapTopico().values()
        topicoList.each { topico ->
            assertNotNull(topico)
            assertNotNull(topico.id)
            assertFalse(idList.contains(topico.id))
            idList.add(topico.id)
        }

    }

    def buildMapFromList(def topicoList) {
        def map = new HashMap<String, Topico>()
        topicoList.each { topico ->
            map.put(topico.rdfId, topico)
        }
        return map
    }

}
