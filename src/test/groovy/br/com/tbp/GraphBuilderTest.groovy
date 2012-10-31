package br.com.tbp

class GraphBuilderTest extends GroovyTestCase  {

    private String GA = GraphBuilder.ONTOLOGY_PREFIX + 'GA'
    private String calculo_I = GraphBuilder.ONTOLOGY_PREFIX + 'calculo_I'
    private String calculo_II = GraphBuilder.ONTOLOGY_PREFIX + 'calculo_II'
    private String ciencia_computacao = GraphBuilder.ONTOLOGY_PREFIX + 'ciencia_computacao'

    void testBuildMacroGraph() {
        GraphBuilder graphBuilder = new GraphBuilder()
        def nodeList = graphBuilder.buildMacroGraph()

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
                inside_1 = true
            } else if (calculo_I.equals(node.rdfId)) {
                assert node.getAntecessors() != null
                assert 1 == node.getAntecessors().size()
                assert ciencia_computacao == node.getAntecessors().get(0).rdfId
                assert node.getSuccessors() != null
                assert 1 == node.getSuccessors().size()
                assert calculo_II == node.getSuccessors().get(0).rdfId
                inside_2 = true
            } else if (calculo_II.equals(node.rdfId)) {
                assert node.getAntecessors() != null
                assert 1 == node.getAntecessors().size()
                assert calculo_I == node.getAntecessors().get(0).rdfId
                assert node.getSuccessors() != null
                assert 0 == node.getSuccessors().size()
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
}
