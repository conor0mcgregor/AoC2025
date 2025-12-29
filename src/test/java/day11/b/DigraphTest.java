package day11.b;

import org.junit.jupiter.api.Test;
import software.aoc.day11.b.Digraph;
import software.aoc.day11.b.Node;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DigraphTest {

    @Test
    void shouldAddNewNodesCorrectly() {
        Digraph digraph = new Digraph();
        digraph.addNode("aaa");
        assertThat(digraph.getNodes())
                .isEqualTo(List.of(new Node("aaa")));
    }

    @Test
    void should_not_duplicate_nodes() {
        Digraph digraph = new Digraph();
        digraph.addNode("aaa");
        digraph.addNode("aaa");
        digraph.addNode("aaa");
        assertThat(digraph.getNodes())
                .isEqualTo(List.of(new Node("aaa")));
    }

    @Test
    void  should_add_nodes_correctly_with_array() {
        Digraph digraph = new Digraph();
        digraph.addDestsNodes("aaa", new String[]{"bbb", "ccc", "ddd"});

        assertThat(digraph.getNodes())
                .isEqualTo(List.of(new Node("aaa"), new Node("bbb"), new Node("ccc"), new Node("ddd")));
        assertThat(digraph.getNode("aaa").getDestNodes())
                .isEqualTo(List.of(new Node("bbb"), new Node("ccc"), new Node("ddd")));
    }

    @Test
    void shouldChainTheNodesCorrectly() {
        Digraph digraph = new Digraph();
        digraph.addNode("aaa");
        digraph.addDestNodeTo("aaa", "bbb");
        digraph.addDestNodeTo("aaa", "ccc");

        assertThat(digraph.getNodes())
                .isEqualTo(List.of(new Node("aaa"), new Node("bbb"), new Node("ccc")));
        assertThat(digraph.getNode("aaa").getDestNodes())
                .isEqualTo(List.of(new Node("bbb"), new Node("ccc")));
    }


}
