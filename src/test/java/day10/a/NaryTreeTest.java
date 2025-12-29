package day10.a;

import org.junit.jupiter.api.Test;
import software.aoc.day10.a.NaryTree;
import software.aoc.day10.a.Node;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class NaryTreeTest {

    @Test
    void should_create_tree_with_initial_state() {
        NaryTree tree = new NaryTree(4);

        assertThat(tree.getRoot()).isNotNull();
    }

    @Test
    void should_add_single_child_correctly() {
        NaryTree tree = new NaryTree(4);
        tree.getRoot().addChild("child1");

        assertThat(tree.getRoot().getChilds()).hasSize(1);
    }

    @Test
    void should_add_multiple_children_correctly() {

        NaryTree tree = new NaryTree(4);
        tree.getRoot().addChild("child1");
        tree.getRoot().addChild("child2");
        tree.getRoot().addChild("child3");

        assertThat(tree.getRoot().getChilds()).hasSize(3);
        assertThat(getStates(tree.getRoot().getChilds()))
                .containsExactlyInAnyOrder("child1", "child2", "child3");
    }

    @Test
    void should_maintain_parent_child_relationship() {
        NaryTree tree = new NaryTree(4);
        tree.getRoot().addChild("child");

        Node child = tree.getRoot().getChilds().iterator().next();

        assertThat(child.getParent()).isSameAs(tree.getRoot());
        assertThat(tree.getRoot().getParent()).isNull();
    }

    @Test
    void should_create_deep_tree_structure() {
        NaryTree tree = new NaryTree(4);

        tree.getRoot().addChild("level1");
        Node level1 = tree.getRoot().getChilds().iterator().next();
        level1.addChild("level2");
        Node level2 = level1.getChilds().iterator().next();
        level2.addChild("level3");


        assertThat(tree.getRoot().getChilds()).hasSize(1);
        assertThat(level1.getChilds()).hasSize(1);
        assertThat(level2.getChilds()).hasSize(1);
    }

    @Test
    void should_handle_duplicate_child_states() {
        NaryTree tree = new NaryTree(4);
        tree.getRoot().addChild("duplicate");
        tree.getRoot().addChild("duplicate");
        tree.getRoot().addChild("duplicate");

        assertThat(tree.getRoot().getChilds()).hasSize(1);
    }


    @Test
    void should_create_wide_tree_structure() {
        NaryTree tree = new NaryTree(4);
        tree.getRoot().addChild("left");
        tree.getRoot().addChild("right");

        Node left = findNodeByState(tree.getRoot().getChilds());
        left.addChild("left-child-1");
        left.addChild("left-child-2");


        assertThat(tree.getRoot().getChilds()).hasSize(2);
        assertThat(left.getChilds()).hasSize(2);
    }

    @Test
    void should_handle_tree_with_only_root() {
        NaryTree tree = new NaryTree(4);

        assertThat(tree.getRoot().getChilds()).isEmpty();
    }

    @Test
    void should_reflect_modifications_after_creation() {
        NaryTree tree = new NaryTree(4);

        assertThat(tree.getRoot().getChilds()).hasSize(0);

        tree.getRoot().addChild("new-child");

        assertThat(tree.getRoot().getChilds()).hasSize(1);
    }

    @Test
    void should_maintain_grandparent_relationships() {
        NaryTree tree = new NaryTree(4);
        tree.getRoot().addChild("child");
        Node child = tree.getRoot().getChilds().iterator().next();
        child.addChild("grandchild");
        Node grandchild = child.getChilds().iterator().next();


        assertThat(grandchild.getParent()).isSameAs(child);
        assertThat(grandchild.getParent().getParent()).isSameAs(tree.getRoot());
    }

    // Helper methods
    private Set<String> getStates(Set<Node> nodes) {
        return nodes.stream()
                .map(Node::getState)
                .collect(Collectors.toSet());
    }

    private Node findNodeByState(Set<Node> nodes) {
        return nodes.stream()
                .filter(n -> n.getState().equals("left"))
                .findFirst()
                .orElseThrow();
    }
}