package br.com.tbp

import br.com.tbp.model.semantic.Topico

class GraphBuilderTest extends GroovyTestCase  {

    private String GA = GraphBuilder.ONTOLOGY_PREFIX + 'GA'
    private String calculo_I = GraphBuilder.ONTOLOGY_PREFIX + 'calculo_I'
    private String calculo_II = GraphBuilder.ONTOLOGY_PREFIX + 'calculo_II'
    private String ciencia_computacao = GraphBuilder.ONTOLOGY_PREFIX + 'ciencia_computacao'


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

        assertEquals(1, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_GA").ordem)
        assertEquals(2, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_GA").ordem)
        assertEquals(3, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_GA").ordem)

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

        assertEquals(1, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_I").ordem)
        assertEquals(2, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_I").ordem)
        assertEquals(3, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_I").ordem)

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

        assertEquals(1, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II").ordem)
        assertEquals(2, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II").ordem)
        assertEquals(3, topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II").ordem)

        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico1_calculo_II").rdfId)
        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico2_calculo_II").rdfId)
        assertEquals(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II", topicoMap.get(GraphBuilder.ONTOLOGY_PREFIX + "topico3_calculo_II").rdfId)

    }

    void testBuildTopicoList() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def topicoMap = graphBuilder.buildTopicoMap()

        assert topicoMap != null
        assert 9 == topicoMap.size()

        assertTopicoGA(topicoMap)
        assertTopicoCalculoI(topicoMap)
        assertTopicoCalculoII(topicoMap)

    }

    void testBuildGraphFromRDF() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def nodeList = graphBuilder.buildGraphFromRDF()

        assert nodeList != null
        assert 4 == nodeList.size()

        def inside_1 = false
        def inside_2 = false
        def inside_3 = false
        def inside_4 = false
        nodeList.each { node ->

            if(GA.equals(node.rdfId)) {
                assert node.getAntecessors() != null
                assert 1 == node.getAntecessors().size()
                assert ciencia_computacao == node.getAntecessors().get(0).rdfId
                assert node.getSuccessors() != null
                assert 0 == node.getSuccessors().size()
                assertTopicoGA(buildMapFromList(node.getTopicoList()))
                inside_1 = true
            } else if (calculo_I.equals(node.rdfId)) {
                assert node.getAntecessors() != null
                assert 1 == node.getAntecessors().size()
                assert ciencia_computacao == node.getAntecessors().get(0).rdfId
                assert node.getSuccessors() != null
                assert 1 == node.getSuccessors().size()
                assert calculo_II == node.getSuccessors().get(0).rdfId
                assertTopicoCalculoI(buildMapFromList(node.getTopicoList()))
                inside_2 = true
            } else if (calculo_II.equals(node.rdfId)) {
                assert node.getAntecessors() != null
                assert 1 == node.getAntecessors().size()
                assert calculo_I == node.getAntecessors().get(0).rdfId
                assert node.getSuccessors() != null
                assert 0 == node.getSuccessors().size()
                assertTopicoCalculoII(buildMapFromList(node.getTopicoList()))
                inside_3 = true
            } else if (ciencia_computacao.equals(node.rdfId)) {
                assert node.getAntecessors() != null
                assert 0 == node.getAntecessors().size()
                assert node.getSuccessors() != null
                assert 2 == node.getSuccessors().size()
                assert calculo_I == node.getSuccessors().get(0).rdfId
                assert GA == node.getSuccessors().get(1).rdfId
                inside_4 = true
            } else {
                fail("where is the rdf id?")
            }
        }

        assertTrue(inside_1 && inside_2 && inside_3 && inside_4)


    }

    def buildMapFromList(def topicoList) {
        def map = new HashMap<String, Topico>()
        topicoList.each { topico ->
            map.put(topico.rdfId, topico)
        }
        return map
    }

}
