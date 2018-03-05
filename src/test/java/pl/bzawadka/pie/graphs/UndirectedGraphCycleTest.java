package pl.bzawadka.pie.graphs;

import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;


public class UndirectedGraphCycleTest {

    @Test
    public void testIsCyclic() {
        UndirectedGraphCycle underTest = new UndirectedGraphCycle();

        LinkedList<Integer>[] graph = createGraph(2, 0, 1, 0, 0);
        assertThat(underTest.isCyclic(graph.length, graph)).isTrue();

        graph = createGraph(4, 0, 1, 1, 2, 2, 3);
        assertThat(underTest.isCyclic(graph.length, graph)).isFalse();

        //graph = createGraph(4, 0, 1, 1, 2, 2, 3, 3, 0);
        //assertThat(underTest.isCyclic(graph.length, graph)).isTrue();
    }

    private LinkedList<Integer>[] createGraph(int numberOfVertices, int... edges) {
        LinkedList<Integer>[] graph = createAdjacencyList(numberOfVertices);
        for (int i = 0; i < edges.length; i = i + 2) {
            int vertexFrom = edges[i];
            int vertexTo = edges[i + 1];
            graph[vertexFrom].add(vertexTo);
        }
        return graph;
    }

    private LinkedList<Integer>[] createAdjacencyList(int numberOfVertices) {
        LinkedList<Integer>[] adjacencyList = new LinkedList[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
        return adjacencyList;
    }

}