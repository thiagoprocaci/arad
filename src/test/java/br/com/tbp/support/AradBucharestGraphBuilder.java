package br.com.tbp.support;

import br.com.tbp.model.Edge;
import br.com.tbp.model.Node;

import java.util.HashMap;
import java.util.Map;


public class AradBucharestGraphBuilder {

    public Map<Integer,Node> buildAradBucharestGraph() {
        Map<Integer, Node> map = new HashMap<Integer, Node>();
        Node arad = new AradBucharestNode(AradBucharestEnum.ARAD);
        map.put(arad.getId(), arad);
        Node bucharest = new AradBucharestNode(AradBucharestEnum.BUCHAREST);
        map.put(bucharest.getId(),bucharest);
        Node craiova = new AradBucharestNode(AradBucharestEnum.CRAIOVA);
        map.put(craiova.getId(),craiova);
        Node dobreta = new AradBucharestNode(AradBucharestEnum.DOBRETA);
        map.put(dobreta.getId(), dobreta);
        Node eforie = new AradBucharestNode(AradBucharestEnum.EFORIE);
        map.put(eforie.getId(), eforie);
        Node fagaras = new AradBucharestNode(AradBucharestEnum.FAGARAS);
        map.put(fagaras.getId(), fagaras);
        Node giurgiu = new AradBucharestNode(AradBucharestEnum.GIURGIU);
        map.put(giurgiu.getId(), giurgiu);
        Node hirsova = new AradBucharestNode(AradBucharestEnum.HIRSOVA);
        map.put(hirsova.getId(), hirsova);
        Node iasi = new AradBucharestNode(AradBucharestEnum.IASI);
        map.put(iasi.getId(), iasi);
        Node lugoj = new AradBucharestNode(AradBucharestEnum.LUGOJ);
        map.put(lugoj.getId(), lugoj);
        Node mehadia = new AradBucharestNode(AradBucharestEnum.MEHADIA);
        map.put(mehadia.getId(), mehadia);
        Node neamt = new AradBucharestNode(AradBucharestEnum.NEAMT);
        map.put(neamt.getId(), neamt);
        Node oradea = new AradBucharestNode(AradBucharestEnum.ORADEA);
        map.put(oradea.getId(), oradea);
        Node pitesti = new AradBucharestNode(AradBucharestEnum.PITESTI);
        map.put(pitesti.getId(), pitesti);
        Node rimnicu_vilcea = new AradBucharestNode(AradBucharestEnum.RIMNICU_VILCEA);
        map.put(rimnicu_vilcea.getId(), rimnicu_vilcea);
        Node sibiu = new AradBucharestNode(AradBucharestEnum.SIBIU);
        map.put(sibiu.getId(), sibiu);
        Node timisoara = new AradBucharestNode(AradBucharestEnum.TIMISOARA);
        map.put(timisoara.getId(), timisoara);
        Node urziceni = new AradBucharestNode(AradBucharestEnum.URZICENI);
        map.put(urziceni.getId(), urziceni);
        Node vaslui = new AradBucharestNode(AradBucharestEnum.VASLUI);
        map.put(vaslui.getId(),vaslui);
        Node zerind = new AradBucharestNode(AradBucharestEnum.ZERIND);
        map.put(zerind.getId(),zerind);

        createEdge(arad, zerind, 75d);
        createEdge(arad, timisoara, 118d);
        createEdge(arad, sibiu, 140d);
        createEdge(zerind, oradea, 71d);
        createEdge(oradea, sibiu, 151d);
        createEdge(timisoara, lugoj, 111d);
        createEdge(lugoj, mehadia, 70d);
        createEdge(mehadia, dobreta, 75d);
        createEdge(dobreta, craiova, 120d);
        createEdge(craiova, rimnicu_vilcea, 146d);
        createEdge(craiova, pitesti, 138d);
        createEdge(sibiu, rimnicu_vilcea, 80d);
        createEdge(sibiu, fagaras, 99d);
        createEdge(rimnicu_vilcea, pitesti, 97d);
        createEdge(fagaras, bucharest, 211d);
        createEdge(pitesti, bucharest, 101d);
        createEdge(bucharest, giurgiu, 90d);
        createEdge(bucharest, urziceni, 85d);
        createEdge(urziceni, hirsova, 98d);
        createEdge(hirsova, eforie, 86d);
        createEdge(urziceni, vaslui, 142d);
        createEdge(vaslui, iasi, 92d);
        createEdge(iasi, neamt, 87d);

        return map;
    }

    private void createEdge(Node node1, Node node2, Double distance) {
        Edge edge = new Edge(node1, node2, distance);
        node1.getEdgeList().add(edge);
        node2.getEdgeList().add(edge);
        Edge edge2 = new Edge(node2, node1, distance);
        node1.getEdgeList().add(edge2);
        node2.getEdgeList().add(edge2);
    }

}
