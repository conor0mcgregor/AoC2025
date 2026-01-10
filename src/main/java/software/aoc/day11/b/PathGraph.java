package software.aoc.day11.b;

public interface PathGraph {
    long getNumPaths(String nodeOrigin, String nodeDest);
    void addNode(String id);
    void addDestNodeTo(String idOrigin, String idDest);
    void addDestsNodes(String idOrigin, String[] idsDest);
}
