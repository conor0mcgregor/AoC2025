package software.aoc.day11.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Digraph implements PathGraph{
    private final List<Node> nodes;
    private final Map<String, Long> memo =  new HashMap<>();

    public Digraph(){
        this.nodes = new ArrayList<>();
    }

    @Override
    public void addDestNodeTo(String idOrigin, String idDest){
        Node nodeOrgin = getNode(idOrigin);
        Node nodeDest = getNode(idDest);

        nodeOrgin.addDestNode(nodeDest);
        addNode(nodeOrgin);
        addNode(nodeDest);
    }

    @Override
    public void addNode(String id){
        Node node = new Node(id);
        if(! nodes.contains(node)) nodes.add(node);
    }

    private void addNode(Node node){
        if(! nodes.contains(node)) nodes.add(node);
    }

    public Node getNode(String id) {
        for(Node node : nodes) {
            if(node.getId().equals(id)) return node;
        }
        return new Node(id);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addDestsNodes(String idOrigin, String[] idsDest) {
        for(String idDest : idsDest){
            addDestNodeTo(idOrigin, idDest);
        }
    }

    @Override
    public long getNumPaths(String nodeOrigin, String nodeDest) {
        memo.clear();
        return calculedNumPaths(nodeOrigin, nodeDest, false, false);
    }

    private Long calculedNumPaths(String nodeOrigin, String nodeDest, boolean passedFFT, boolean passedDAC) {
        String key = nodeOrigin + "|" + passedFFT + "|" + passedDAC;

        if (memo.containsKey(key)) return memo.get(key);

        boolean nowFFT = passedFFT || nodeOrigin.equals("fft");
        boolean nowDAC = passedDAC || nodeOrigin.equals("dac");

        if (nodeOrigin.equals(nodeDest) && nowFFT && nowDAC) return 1L;

        long result = 0;
        Node current = getNode(nodeOrigin);

        if (current == null) return 0L;

        result += getNeighborsPaths(nodeDest, current, nowFFT, nowDAC);

        memo.put(key, result);
        return result;
    }

    private long getNeighborsPaths(String nodeDest, Node current, boolean nowFFT, boolean nowDAC) {
        return current.getDestNodes().stream()
                .mapToLong(child -> calculedNumPaths(child.getId(), nodeDest, nowFFT, nowDAC))
                .sum();
    }
}
