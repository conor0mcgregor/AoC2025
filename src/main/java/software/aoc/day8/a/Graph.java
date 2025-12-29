package software.aoc.day8.a;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addConection(Node nodeInide, Node newNode) {
        nodeInide.addNeighbor(newNode);
        newNode.addNeighbor(nodeInide);
        if(!contains(nodeInide)) nodes.add(nodeInide);
    }

    public Graph addNode(Node node){
        if(!contains(node)) nodes.add(node);
        return this;
    }

    public boolean contains(Node node){
        return nodes.contains(node);
    }

    public int size(){
        return nodes.size();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void join(Graph graph) {
        nodes.addAll(graph.nodes);
    }
}
