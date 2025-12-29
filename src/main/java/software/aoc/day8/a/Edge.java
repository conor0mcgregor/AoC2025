package software.aoc.day8.a;

import static java.lang.Math.pow;

public class Edge implements Comparable<Edge>{
    private final Node nodeA;
    private final Node nodeB;
    private final long distance;

    public Edge(Node nodeA, Node nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.distance = calculeDistace(nodeA.getPoint(), nodeB.getPoint());
    }

    private long calculeDistace(Node.Point point1, Node.Point point2) {
        return (long) (pow((point1.x() - point2.x()), 2) +
                pow((point1.y() - point2.y()), 2) +
                pow((point1.z() - point2.z()), 2));
    }

    public Node getNodeA() {
        return nodeA;
    }

    public Node getNodeB() {
        return nodeB;
    }

    public long getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.getDistance(), o.getDistance());
    }
}
