package pl.bzawadka.pie.graphs;

import java.util.LinkedList;

public class UndirectedGraphCycle {
    private final boolean HAS_CYCLE = true;
    private final boolean NO_CYCLE = false;

    public boolean isCyclic(int numberOfVertices, LinkedList<Integer>[] graphAsAdjacencyList) {
        for (int vertexIndex = 0; vertexIndex < numberOfVertices; vertexIndex++) {
            LinkedList<Integer> adjacentVertices = graphAsAdjacencyList[vertexIndex];
            for (Integer adjacentVertex : adjacentVertices) {
                if (vertexIndex == adjacentVertex)
                    return HAS_CYCLE;
            }
        }
        return NO_CYCLE;
    }
}
