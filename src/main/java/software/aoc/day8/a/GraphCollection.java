package software.aoc.day8.a;

import java.util.List;

public interface GraphCollection {
    void addNewConnection(Node node1, Node node2);
    List<Integer> graphsSizes();
    List<Graph> getGraphs();
    int lenght();
    void addSingleNode(Node node);
}
