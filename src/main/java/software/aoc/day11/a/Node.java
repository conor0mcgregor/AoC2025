package software.aoc.day11.a;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private final String id;
    private final List<Node> destNodes;

    public Node(String id){
        this.id = id;
        this.destNodes = new ArrayList<>();
    }

    public void addDestNode(Node node){
        if(!destNodes.contains(node)) destNodes.add(node);
    }

    public String getId() {
        return id;
    }

    public List<Node> getDestNodes() {
        return destNodes;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
