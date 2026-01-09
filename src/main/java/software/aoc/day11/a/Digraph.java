package software.aoc.day11.a;

import java.util.*;

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
        return calculedNumPaths(nodeOrigin, nodeDest);
    }

    private long calculedNumPaths(String nodeOrigin, String nodeDest) {
        String key = nodeOrigin + "-" + nodeDest;
        if(memo.containsKey(key)) return memo.get(key);

        if(nodeOrigin.equals(nodeDest)) return 1L;

        long result = 0;
        Node current = getNode(nodeOrigin);

        if (current == null) return 0L;

        result += getNeighborsPaths(current, nodeDest);
        memo.put(key, result);
        return result;
    }

    private long getNeighborsPaths(Node nodeOrigin, String nodeDest) {
        return nodeOrigin.getDestNodes().stream()
                .mapToLong(n -> getNumPaths(n.getId(), nodeDest))
                .sum();
    }

}
