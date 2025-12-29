package software.aoc.day10.b;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {
    private final State state;
    private final Node parent;
    private final Set<Node> childs;

    public Node(Node parent, State state) {
        this.state = state;
        this.parent = parent;
        this.childs = new HashSet<>();
    }

    public State getState() {
        return state;
    }

    public void addChild(State state){
        childs.add(new Node(this, state));
    }

    public Node getParent() {
        return parent;
    }

    public Set<Node> getChilds() {
        return childs;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(state, node.state);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(state);
    }
}
