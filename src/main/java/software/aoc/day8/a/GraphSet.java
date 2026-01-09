package software.aoc.day8.a;

import java.util.ArrayList;
import java.util.List;

public class GraphSet implements GraphCollection{
    private final List<Graph> graphs;

    public GraphSet() {
        this.graphs = new ArrayList<>();
    }

    @Override
    public void addNewConnection(Node node1, Node node2){
        Graph graph1 = getGraph(node1);
        Graph graph2 = getGraph(node2);
        if(graph1 == graph2) return;

        joinGraphs(graph1, node1, graph2, node2);
    }

    @Override
    public List<Integer> graphsSizes() {
        return graphs.stream()
                .map(Graph::size)
                .toList();
    }

    private void joinGraphs(Graph graph, Node node1, Graph graph2, Node node2) {
        graph.join(graph2);
        graph.addConection(node1, node2);
        graphs.remove(graph2);
    }


    private Graph getGraph(Node node) {
        return graphs.stream()
                .filter(g -> g.contains(node))
                .findFirst()
                .orElseGet(() -> {
                    Graph g = new Graph().addNode(node);
                    graphs.add(g);
                    return g;
                });
    }

    @Override
    public List<Graph> getGraphs() {
        return graphs;
    }

    @Override
    public int lenght() {
        return graphs.size();
    }

    @Override
    public void addSingleNode(Node node) {
        getGraph(node);
    }
}
