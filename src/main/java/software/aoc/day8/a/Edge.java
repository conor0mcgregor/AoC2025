package software.aoc.day8.a;

public class Edge implements Comparable<Edge>{
    private final int a, b;
    private final double distance;

    public Edge(int a, int b, double distance) {
        this.a = a;
        this.b = b;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.distance, other.distance);
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public double getDistance() {
        return distance;
    }
}
