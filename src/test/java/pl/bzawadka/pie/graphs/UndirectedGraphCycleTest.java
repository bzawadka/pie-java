package pl.bzawadka.pie.graphs;

import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;


public class UndirectedGraphCycleTest {

    @Test
    public void testIsCyclic() {
        UndirectedGraphCycle underTest = new UndirectedGraphCycle();

        LinkedList<Integer>[] graph = createAdjacencyList(2);
        //0 1 0 0
        graph[0].add(1);
        graph[0].add(0);
        assertThat(underTest.isCyclic(graph.length, graph)).isTrue();

        graph = createAdjacencyList(4);
        //0 1 1 2 2 3
        graph[0].add(1);
        graph[1].add(2);
        graph[2].add(3);
        assertThat(underTest.isCyclic(graph.length, graph)).isFalse();
    }

    private LinkedList<Integer>[] createAdjacencyList(int numberOfVertices) {
        LinkedList<Integer>[] linkedLists = new LinkedList[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            linkedLists[i] = new LinkedList<>();
        }
        return linkedLists;
    }

}