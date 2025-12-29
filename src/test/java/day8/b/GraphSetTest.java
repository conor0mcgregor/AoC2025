package day8.b;

import org.junit.jupiter.api.Test;
import software.aoc.day8.b.GraphSet;
import software.aoc.day8.b.Node;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphSetTest {
    @Test
    void should_add_a_connection_correctly() {
        GraphSet graphSet = new GraphSet();
        Node node1 = new Node(1, 10, 20, 10);
        Node node2 = new Node(2, 20, 20, 10);
        graphSet.addNewConnection(node1, node2);

        assertThat(graphSet.sizes()).isEqualTo(List.of(2));
        assertThat(graphSet.lenght()).isEqualTo(1);
        assertThat(graphSet.getGraphs().getFirst().getNodes()).isEqualTo(List.of(node1, node2));

    }

    @Test
    void should_be_two_degrees_apart() {
        GraphSet graphSet = new GraphSet();
        Node node1 = new Node(1, 10, 20, 10);
        Node node1a = new Node(12, 10, 10, 10);
        Node node1b = new Node(13, 0, 10, 10);
        graphSet.addNewConnection(node1, node1a);
        graphSet.addNewConnection(node1, node1b);

        Node node2 = new Node(2, 20, 20, 10);
        Node node2a = new Node(22, 0, 20, 0);
        Node node2b = new Node(23, 13, 18, 10);
        graphSet.addNewConnection(node2, node2a);
        graphSet.addNewConnection(node2, node2b);

        assertThat(graphSet.sizes()).isEqualTo(List.of(3, 3));
        assertThat(graphSet.lenght()).isEqualTo(2);
        assertThat(graphSet.getGraphs().getFirst().getNodes()).isEqualTo(List.of(node1, node1a, node1b));
        assertThat(graphSet.getGraphs().getLast().getNodes()).isEqualTo(List.of(node2, node2a, node2b));

    }

    @Test
    void should_connect_two_graphs_correctly() {
        GraphSet graphSet = new GraphSet();
        Node node1 = new Node(1, 10, 20, 10);
        Node node1a = new Node(12, 10, 10, 10);
        Node node1b = new Node(13, 0, 10, 10);
        graphSet.addNewConnection(node1, node1a);
        graphSet.addNewConnection(node1, node1b);

        Node node2 = new Node(2, 20, 20, 10);
        Node node2a = new Node(22, 0, 20, 0);
        Node node2b = new Node(23, 13, 18, 10);
        graphSet.addNewConnection(node2, node2a);
        graphSet.addNewConnection(node2, node2b);

        graphSet.addNewConnection(node1b, node2b);

        assertThat(graphSet.sizes()).isEqualTo(List.of(6));
        assertThat(graphSet.lenght()).isEqualTo(1);
        assertThat(graphSet.getGraphs().getFirst().getNodes()).isEqualTo(List.of(node1, node1a, node1b, node2, node2a, node2b));

    }
}
