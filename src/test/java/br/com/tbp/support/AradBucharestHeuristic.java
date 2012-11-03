package br.com.tbp.support;

import br.com.tbp.model.Node;
import br.com.tbp.search.astar.IHeuristic;

public class AradBucharestHeuristic implements IHeuristic {

    @Override
    public double estimate(Node start, Node goal) {
       // simula o estimativa da busca de caminhos Arad-Bucharest
        // a estimativa eh o valor da distance em linha reta de um no (start) ate bucharest (goal)
        AradBucharestNode node = (AradBucharestNode) start;
        Double h = 0d;
        switch (node.getDesc()) {
        case ARAD:
            h = 366d;
            break;
        case BUCHAREST:
            h = 0d;
            break;
        case CRAIOVA:
            h = 160d;
            break;
        case DOBRETA:
            h = 242d;
            break;
        case EFORIE:
            h = 161d;
            break;
        case FAGARAS:
            h = 178d;
            break;
        case GIURGIU:
            h = 77d;
            break;
        case HIRSOVA:
            h = 151d;
            break;
        case IASI:
            h = 226d;
            break;
        case LUGOJ:
            h = 244d;
            break;
        case MEHADIA:
            h = 241d;
            break;
        case NEAMT:
            h = 234d;
            break;
        case ORADEA:
            h = 380d;
            break;
        case PITESTI:
            h = 98d;
            break;
        case RIMNICU_VILCEA:
            h = 193d;
            break;
        case SIBIU:
            h = 253d;
            break;
        case TIMISOARA:
            h = 329d;
            break;
        case URZICENI:
            h = 80d;
            break;
        case VASLUI:
            h = 199d;
            break;
        case ZERIND:
            h = 374d;
            break;
        default:
            break;
        }
        return h;
    }
}
