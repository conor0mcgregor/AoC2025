package software.aoc.day9.a;

import java.util.List;

import static java.lang.Math.abs;

public class Board {
    private final List<Point> points;

    public Board(List<Point> points) {
        this.points = points;
    }

    public long getBigerRectangle(){
        long maxArea = 0;

        for (Point point1 : points){
            for (Point point2 : points) {
                long area = getArea(point1, point2);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;

    }

    private long getArea(Point point, Point point1) {
        return (long) (abs(point.x() - point1.x()) +1) * (abs(point.y() - point1.y())+1);
    }
}
