package day8.a;

import org.junit.jupiter.api.Test;
import software.aoc.day8.a.Graph;
import software.aoc.day8.a.Node;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphTest {
    @Test
    void should_add_a_connection_correctly() {
        Graph graph = new Graph();
        Node node1 = new Node(1, 10, 20, 10);
        Node node2 = new Node(2, 20, 20, 10);
        graph.addConection(node1, node2);

        assertThat(graph.getNodes()).isEqualTo(List.of(node2, node1));
    }
    @Test
    void should_return_the_neighbors_correctly() {
        Graph graph = new Graph();
        Node node1 = new Node(1, 10, 20, 10);
        Node node2 = new Node(2, 20, 20, 10);
        graph.addConection(node1, node2);

        assertThat(node2.getNeighbors()).isEqualTo(List.of(node1));
    }
    @Test
    void should_add_a_connection_between_two_graphs() {
        Graph graph = new Graph();
        Node node1 = new Node(1, 10, 20, 10);

        Node node2 = new Node(2, 20, 20, 10);
        Node node2a = new Node(21, 1, 20, 10);
        Node node2b = new Node(22, 20, 1, 10);
        node2.addNeighbor(node2a);
        node2.addNeighbor(node2b);

        graph.addConection(node1, node2);

        assertThat(graph.getNodes()).isEqualTo(List.of(node2, node1, node2a, node2b));
    }


}
