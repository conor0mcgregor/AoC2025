package software.aoc.day8.b;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addConection(Node nodeInside, Node newNode) {
        nodeInside.addNeighbor(newNode);
        newNode.addNeighbor(nodeInside);

        addNode(nodeInside);
        addNode(newNode);

        newNode.getNeighbors().forEach(this::addNode);
        nodeInside.getNeighbors().forEach(this::addNode);
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