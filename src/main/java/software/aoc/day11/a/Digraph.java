package software.aoc.day11.a;

import java.util.*;

public class Digraph {
    private final List<Node> nodes;
    private final Map<String, Long> memo =  new HashMap<>();

    public Digraph(){
        this.nodes = new ArrayList<>();
    }

    public void addDestNodeTo(String idOrigin, String idDest){
        Node nodeOrgin = getNode(idOrigin);
        Node nodeDest = getNode(idDest);

        nodeOrgin.addDestNode(nodeDest);
        addNode(nodeOrgin);
        addNode(nodeDest);
    }

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

    public Long getNumPaths(String nodeOrigin, String nodeDest) {
        memo.clear();
        if(nodeOrigin.equals(nodeDest)) return 1L;

        if(nodeDest == null) return 0L;

        String key = nodeOrigin + "-" + nodeDest;
        if(memo.containsKey(key)) return memo.get(key);

        long result = 0;

        for(Node child : getNode(nodeOrigin).getDestNodes()){
            result += getNumPaths(child.getId(), nodeDest);
        }
        memo.put(key, result);
        return result;
    }
}
