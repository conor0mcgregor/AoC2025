package software.aoc.day8.a;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private final int id;
    private final Point point;
    private final List<Node> neighbors;

    public Node(int id, int x, int y, int z) {
        this.id = id;
        this.point = new Point(x, y, z);
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Node node){
        if(!neighbors.contains(node)) neighbors.add(node);
    }

    public int getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    record Point(int x, int y, int z){}
}
