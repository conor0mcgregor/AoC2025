package software.aoc.day9.a;

import java.util.List;

import static java.lang.Math.abs;

public class Board implements Map {
    private final List<Point> points;

    public Board(List<Point> points) {
        this.points = points;
    }

    @Override
    public long getBigerRectangle() {
        return points.stream()
                .flatMap(p1 -> points.stream()
                        .map(p2 -> getArea(p1, p2)))
                .mapToLong(Long::longValue)
                .max()
                .orElse(0);
    }

    private long getArea(Point point, Point point1) {
        return (long) (abs(point.x() - point1.x()) +1) * (abs(point.y() - point1.y())+1);
    }
}
